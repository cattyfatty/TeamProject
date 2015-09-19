package com.mycompany.myapp.dao;

import java.util.*;

import org.junit.*;
import org.springframework.beans.factory.annotation.*;

import com.mycompany.myapp.*;
import com.mycompany.myapp.dto.*;

public class CartDaoTest extends ApplicationContextLoader {

	@Autowired
	private CartDao cartDao;
	
	@Test
	public void testInsert(){
		Cart cart = new Cart();
		cart.setcartAmount(111);
		cart.setmemberId("aa");
		cart.setGoods_no(1);
		Integer cno = cartDao.insert(cart);
		Assert.assertNotNull(cno); 

	}
	@Test
	public void testUpdate(){
		Cart cart = new Cart();
		cart.setGoods_no(1);
		cart.setcartAmount(111);
		cart.setmemberId("aa");
		Integer cno = cartDao.insert(cart);
		
		cart.setcartNo(cno);
		cart.setGoods_no(2);
		cart.setcartAmount(3333);
		cart.setmemberId("aa");
		int row = cartDao.update(cart);
		Assert.assertEquals(1, row);
		
		Cart cart2 = cartDao.selectByPk(cno);
		Assert.assertEquals(cart.getcartAmount(),cart2.getcartAmount());
		Assert.assertEquals(cart.getmemberId(),cart2.getmemberId() );
		Assert.assertEquals(cart.getGoods_no(),cart2.getGoods_no());
		
	}
	
	@Test
	public void testDelete(){
		Cart cart = new Cart();
		cart.setcartAmount(111);
		cart.setmemberId("aa");
		cart.setGoods_no(1);
		Integer cno = cartDao.insert(cart);
		int row = cartDao.delete(cart.getmemberId());
		Assert.assertEquals(1, row);
	}
	
	@Test
	public void testSelectByPk(){
		Cart cart = new Cart();
		cart.setcartAmount(111);
		cart.setmemberId("aa");
		cart.setGoods_no(1);
		Integer cno = cartDao.insert(cart);
		
		Assert.assertNotNull(cart);
		Cart cart2 = cartDao.selectByPk(cno);
		Assert.assertEquals(cart.getcartAmount(),cart2.getcartAmount());
		Assert.assertEquals(cart.getmemberId(),cart2.getmemberId() );
		Assert.assertEquals(cart.getGoods_no(),cart2.getGoods_no());
		
		
	}
	

	
}
