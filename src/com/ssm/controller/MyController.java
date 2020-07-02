package com.ssm.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ssm.pojo.Items;
import com.ssm.pojo.QueryItem;
import com.ssm.service.ItermService;

@Controller
public class MyController {

	@Autowired
	private ItermService itemService;
	
	@RequestMapping("/list")
	public String getList(Model model)
	{
		//int s=1/0;
		List<Items> list=	itemService.findAll();
		model.addAttribute("itemList", list);
		return "itemList";
	}
	
	@RequestMapping("/itemEdit/{id}")
	public String itemEdit(@PathVariable("id")int id,Model model)
	{
		Items item= itemService.findOne(id);
		model.addAttribute("item", item);
		return "editItem";
	}
	
	@RequestMapping("/updateitem")
	public String updateitem(Items items,MultipartFile pictureFile)
	{
		String newFileName=null;
		if(pictureFile!=null&&!pictureFile.getOriginalFilename().equals(""))
		{
			newFileName=UUID.randomUUID()+pictureFile.getOriginalFilename().substring(pictureFile.getOriginalFilename().lastIndexOf("."));
			try {
				File file=new File("d://uploadfile//"+newFileName);
				if(!file.getParentFile().exists())
					{
						file.mkdirs();
					}
				
				pictureFile.transferTo(file);
			} catch (IllegalStateException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			items.setPic(newFileName);
		}
		itemService.modify(items);
		return "redirect:list.action";
	}
	
	@RequestMapping("/queryitem")
	public String queryitem(QueryItem queryItem,int[] ids,Model model)
	{
		if(ids!=null&&ids.length>0)
		{
			//批量删除
			itemService.remove(ids);
			return "forward:list.action";
		}
		if(queryItem.getItemlist()!=null&&queryItem.getItemlist().size()>0)
		{
			//批量更新
			for (Items i : queryItem.getItemlist()) {
				itemService.modify(i);
			}
			
			return "forward:list.action";
		}
		List<Items> list=	itemService.findByCondition(queryItem);
		model.addAttribute("itemList", list);
		model.addAttribute("oldData", queryItem);
		return "itemList";
	}
	
	/**
	 * 登录验证456
	 * @param username
	 * @param password
	 * @param session
	 * @return
	 */
	@RequestMapping("/login")
	@ResponseBody
	public  Map login(String username,String password,HttpSession session)
	{
		Map<String, Object> map = new HashMap<String, Object>();
		session.setAttribute("username", username);
		map.put("success", true);
		map.put("message", "登录成功!");
		return map;
	
	}
	
}
