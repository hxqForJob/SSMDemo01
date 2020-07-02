package com.ssm.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssm.mapper.ItemsMapper;
import com.ssm.pojo.Items;
import com.ssm.pojo.ItemsExample;
import com.ssm.pojo.ItemsExample.Criteria;
import com.ssm.pojo.QueryItem;
import com.ssm.service.ItermService;

@Service
public class ItermServiceImpl implements ItermService {

	@Autowired
	private ItemsMapper itemsMapper;
	
	@Override
	public List<Items> findAll() {
		
		return itemsMapper.selectByExample(new ItemsExample());
	}

	@Override
	public Items findOne(int id) {
		
		return itemsMapper.selectByPrimaryKey(id);
	}

	@Override
	public void modify(Items items) {
		// TODO Auto-generated method stub
		itemsMapper.updateByPrimaryKeySelective(items);
	}

	@Override
	public List<Items> findByCondition(QueryItem queryItem) {
		// TODO Auto-generated method stub
		ItemsExample example = new ItemsExample(); 
		if(queryItem.getItems().getName()==null)
		{
			queryItem.getItems().setName("");
		}
		Criteria criteria = example.createCriteria().andNameLike("%"+queryItem.getItems().getName()+"%");
		if(queryItem.getItems().getPrice()!=null)
		{
			criteria.andPriceBetween(0f, queryItem.getItems().getPrice());
		}
		return itemsMapper.selectByExample(example);
	}

	@Override
	public void remove(int[] ids) {
		// TODO Auto-generated method stub
		for (int i : ids) {
			itemsMapper.deleteByPrimaryKey(i);
		}
	}

}
