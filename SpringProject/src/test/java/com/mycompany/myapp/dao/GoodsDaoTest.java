package com.mycompany.myapp.dao;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.mycompany.myapp.ApplicationContextLoader;
import com.mycompany.myapp.dto.Goods;


public class GoodsDaoTest extends ApplicationContextLoader {
	@Autowired 
	private GoodsDao goodsDao;
	
	/*goods_name, goods_price, goods_calory, goods_gift, goods_size,goods_kind*/
	
	@Test
	public void testInsert(){
		Goods goods = new Goods();
		goods.setCalory(130);
		goods.setGift("����");
		goods.setKind("desert");
		goods.setName("icecream");
		goods.setPrice(1200);
		goods.setSize("large");
		Integer gno = goodsDao.insert(goods);
		Assert.assertNotNull(gno);
		
	}
	 /*"update goods set goods_name=?, goods_price=?, "
		+  " goods_calory=?, goods_gift=?, goods_size=? ,goods_kind=? where goods_no=?";
	*/
	
	@Test
	public void testUpdate(){
		Goods goods = new Goods();
		goods.setCalory(130);
		goods.setGift("����");
		goods.setKind("desert");
		goods.setName("icecream");
		goods.setPrice(1200);
		goods.setSize("large");
		Integer gno = goodsDao.insert(goods);
		
		goods.setNo(gno);
		goods.setCalory(1300);
		goods.setGift("fart");
		goods.setKind("desert");
		goods.setName("farticecream");
		goods.setPrice(1200);
		goods.setSize("Xlarge");
		int row = goodsDao.update(goods);
		Assert.assertEquals(1, row);
		
		Goods goods2 = goodsDao.selectByPk(gno);
		Assert.assertEquals(goods2.getGift(),goods.getGift());
		Assert.assertEquals(goods2.getKind(),goods.getKind());
		Assert.assertEquals(goods2.getName(),goods.getName());
		Assert.assertEquals(goods2.getSize(),goods.getSize());
		Assert.assertEquals(goods2.getCalory(),goods.getCalory());
		Assert.assertEquals(goods2.getPrice(),goods.getPrice());
	}
	
	@Test
	public void testDelete(){
		Goods goods = new Goods();
		goods.setCalory(1300);
		goods.setGift("����");
		goods.setKind("desert");
		goods.setName("cream");
		goods.setPrice(1200);
		goods.setSize("large");
		Integer gno = goodsDao.insert(goods);
		
		int row = goodsDao.delete(gno);
		
		Assert.assertEquals(1, row);
		
	}
	
	@Test
	public void testSelectByPk(){
		Goods goods = new Goods();
		goods.setCalory(1800);
		goods.setGift("����");
		goods.setKind("dsert");
		goods.setName("cream12");
		goods.setPrice(1230);
		goods.setSize("Mlarge");
		Integer gno = goodsDao.insert(goods);
		
		Assert.assertNotNull(goods);
		Goods goods2 = goodsDao.selectByPk(gno);
		
		
		
		Assert.assertEquals(goods2.getGift(),goods.getGift());
		Assert.assertEquals(goods2.getKind(),goods.getKind());
		Assert.assertEquals(goods2.getName(),goods.getName());
		Assert.assertEquals(goods2.getSize(),goods.getSize());
		Assert.assertEquals(goods2.getCalory(),goods.getCalory());
		Assert.assertEquals(goods2.getPrice(),goods.getPrice());
		
		
	}
	
	
}
