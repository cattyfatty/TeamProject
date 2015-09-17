package com.mycompany.myapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mycompany.myapp.dao.CartDao;
import com.mycompany.myapp.dao.GoodsDao;
import com.mycompany.myapp.dao.MemberDao;
import com.mycompany.myapp.dao.OrderItemsDao;
import com.mycompany.myapp.dao.OrdersDao;
import com.mycompany.myapp.dto.Cart;
import com.mycompany.myapp.dto.Goods;
import com.mycompany.myapp.dto.Members;
import com.mycompany.myapp.dto.OrderItems;
import com.mycompany.myapp.dto.Orders;

@Component
public class GoodsService {

	@Autowired
	private GoodsDao goodsDao;
	@Autowired
	private CartDao cartDao;
	@Autowired
	private OrderItemsDao orderItemsDao;
	@Autowired
	private OrdersDao ordersDao;
	@Autowired
	private MemberDao memberDao;

	// -----------------------------------------------------------------------------------------
	// Members ���� ����
	public void joinMember(Members member) {
		memberDao.insert(member);
	}

	public Members loginMember(Members member) {
		Members isExist = memberDao.selectByPk(member.getId());
		if (isExist.getPassword().equals(member.getPassword())) {
			return isExist;
		} else
			return null;
	}
	// -----------------------------------------------------------------------------------------

	// -----------------------------------------------------------------------------------------
	// Cart ���� ����
<<<<<<< HEAD
	public void addCart(String memberId, int amount, int productNo) {
		Cart cart = new Cart();
		cart.setmemberId(memberId);
		cart.setcartAmount(amount);
		cart.setproductNo(productNo);
=======
	public void addCart(Cart cart) {
>>>>>>> branch 'master' of https://github.com/cattyfatty/TeamProject.git
		cartDao.insert(cart);
	}
	
	public List<Cart> getCart(String memberId) {
		List<Cart> list = cartDao.selectByid(memberId);
		
		return list;		
	}
	
	public void deleteCart(String memberId) {
		cartDao.delete(memberId);
	}
	public List<Cart> getCartPage(int pageNo, int rowsPerPage) {
		
		List<Cart> list = cartDao.selectByPage(pageNo, rowsPerPage);
		
		return list;
	}
	// -----------------------------------------------------------------------------------------

	// -----------------------------------------------------------------------------------------
<<<<<<< HEAD
	// 주문관련
	public void order(String memberId) {
		List<Cart> cartList = getCart(memberId);
		deleteCart(memberId);
		for (Cart cart : cartList) {
			
		}
		
	}
	// -----------------------------------------------------------------------------------------
	
	// -----------------------------------------------------------------------------------------
=======
>>>>>>> branch 'master' of https://github.com/cattyfatty/TeamProject.git
	// OrderItems ���� ����
	public void addOrderItems(OrderItems orderItmes) {
		orderItemsDao.insert(orderItmes);
	}
	
	public List<OrderItems> getOrderItems(int orderNo) {
		List<OrderItems> list = orderItemsDao.selectByOrderNo(orderNo);
		
		return list;
	}
	// -----------------------------------------------------------------------------------------

	// -----------------------------------------------------------------------------------------
	// Orders ���� ����
	public void addOrder(Orders orders) {
		ordersDao.insert(orders);
	}
	
	public List<Orders> getOrders(String memberId) {
		List<Orders> list = ordersDao.selectByMemberId(memberId);
		
		return list;
	}	
	// -----------------------------------------------------------------------------------------

	// -----------------------------------------------------------------------------------------
	// Goods ���� ����
<<<<<<< HEAD
	public List<Goods> getGoods(int pageNo, int rowsPerPage) {
=======
	public List<Goods> showGoods(int pageNo, int rowsPerPage) {
>>>>>>> branch 'master' of https://github.com/cattyfatty/TeamProject.git
		List<Goods> list = goodsDao.selectByPage(pageNo, rowsPerPage);
		
		return list;
	}
	
<<<<<<< HEAD
	public Goods getGoodsDetail(int goodsNo) {
=======
	public Goods getGoods(int goodsNo) {
>>>>>>> branch 'master' of https://github.com/cattyfatty/TeamProject.git
		Goods goods = goodsDao.selectByPk(goodsNo);
		
		return goods;
	}

	public int getTotalBoardNo() {
	int rows = goodsDao.selectCount();
		
		return rows;

	}

	public List<Goods> getPage(int pageNo, int rowsPerPage) {
		
		List<Goods> list = goodsDao.selectByPage(pageNo, rowsPerPage);
		
		return list;
	}
	
	

	// -----------------------------------------------------------------------------------------
}
