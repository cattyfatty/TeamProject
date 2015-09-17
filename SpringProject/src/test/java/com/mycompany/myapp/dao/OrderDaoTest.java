package com.mycompany.myapp.dao;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.mycompany.myapp.ApplicationContextLoader;
import com.mycompany.myapp.dto.Order;

public class OrderDaoTest extends ApplicationContextLoader{
	
	@Autowired
	private OrderDao orderDao;
	
	@Test
	public void testInsert(){
		Order order = new Order();
		order.setMemberid("aa");
		order.setOrderPrice(1000);
		Integer bno = orderDao.insert(order);
		Assert.assertNotNull(bno);
		
	}
	
	
	@Test
	public void testSelectByPage() {
		List<Order> list = orderDao.selectByPage(1, 10);
		//Assert.assertEquals(10, list.size());
		
	}
	
	
	

}
