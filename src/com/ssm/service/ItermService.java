package com.ssm.service;

import java.util.List;

import com.ssm.pojo.Items;
import com.ssm.pojo.QueryItem;

public interface ItermService {

	 List<Items> findAll();

	Items findOne(int id);

	void modify(Items items);

	List<Items> findByCondition(QueryItem queryItem);

	void remove(int[] ids);

}
