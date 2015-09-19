package com.mycompany.myapp.dao;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.mycompany.myapp.ApplicationContextLoader;
import com.mycompany.myapp.dto.OrderItem;

public class OrderItemDaoTest extends ApplicationContextLoader {

	@Autowired
	private OrderItemDao oiDao;

	@Test
	public void insertTest() {
		OrderItem oi = new OrderItem();

		oi.setGoodsItemNo(1);
		oi.setOrderAmount(5);
		oi.setOrderNo(4);

		Integer oino = oiDao.insert(oi);
		Assert.assertNotNull(oino);
	}

	@Test
	public void updateTest() {
		OrderItem oi = new OrderItem();

		oi.setGoodsItemNo(1);
		oi.setOrderAmount(5);
		oi.setOrderNo(4);

		Integer oino = oiDao.insert(oi);
		OrderItem oi2 = new OrderItem();
		oi2.setOrderItemNo(oino);
		oi2.setGoodsItemNo(2);
		oi2.setOrderAmount(3);
		oi2.setOrderNo(oi.getOrderNo());;
		
		int row = oiDao.update(oi);
		Assert.assertEquals(1, row);
		Assert.assertEquals(oi.getGoodsItemNo(), oi2.getOrderItemNo());
		Assert.assertEquals(oi.getOrderAmount(), oi2.getOrderItemNo());
	}
}
