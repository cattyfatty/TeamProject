package com.mycompany.myapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mycompany.myapp.dao.GoodsDao;

@Component
public class GoodsService {

	@Autowired
	private GoodsDao goodsDao;
}
