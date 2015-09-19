package com.mycompany.myapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mycompany.myapp.dao.CartDao;
import com.mycompany.myapp.dao.GoodsDao;
import com.mycompany.myapp.dao.MemberDao;
import com.mycompany.myapp.dao.OrderItemDao;
import com.mycompany.myapp.dao.OrderDao;
import com.mycompany.myapp.dto.Cart;
import com.mycompany.myapp.dto.Goods;
import com.mycompany.myapp.dto.Members;
import com.mycompany.myapp.dto.OrderItem;
import com.mycompany.myapp.dto.Order;

@Component
public class GoodsService {

	@Autowired
	private GoodsDao goodsDao;
	@Autowired
	private CartDao cartDao;
	@Autowired
	private OrderItemDao orderItemsDao;
	@Autowired
	private OrderDao ordersDao;
	@Autowired
	private MemberDao memberDao;

	// -----------------------------------------------------------------------------------------
	// Members ���� ����
	public void joinMember(Members member) {
		memberDao.insert(member);
	}

	public Members loginMember(Members member) {
		Members isExist = null;
		try{
			isExist = memberDao.selectByPk(member.getId());
		} catch(Exception e) {
			System.out.println("check id or pw");
		}
		if (isExist != null && isExist.getPassword().equals(member.getPassword())) {
			return isExist;
		} else{
			System.out.println("로그인실패");
			return null;
		}
	}

	public void addCart(Cart cart) {
		cartDao.insert(cart);
	}
	
	public List<Cart> getCart(String memberId) {
		List<Cart> list = cartDao.selectById(memberId);
		
		return list;		
	}
	
	public void deleteCart(String memberId) {
		cartDao.delete(memberId);
	}
	public List<Cart> getCartPage(int pageNo, int rowsPerPage, String memberId) {
		
		List<Cart> list = cartDao.selectByPage(pageNo, rowsPerPage, memberId);
		
		return list;
	}
	// -----------------------------------------------------------------------------------------

	// -----------------------------------------------------------------------------------------
	// 주문관련
	public void addOrder(String memberId) {
		List<Cart> cartList = getCart(memberId);
		deleteCart(memberId);
		int sumPrice= 0;
		Order order = new Order();
		for (Cart cart : cartList) {
			sumPrice += cart.getCart_amount()*getGoods(cart.getGoods_no()).getPrice();
		}
		order.setMemberid(memberId);
		order.setOrderPrice(sumPrice);
		int orderNo = ordersDao.insert(order);
		
		for(Cart cart : cartList) {
			OrderItem item = new OrderItem();
			item.setGoodsItemNo(cart.getGoods_no());
			item.setOrderAmount(cart.getCart_amount());
			item.setOrderNo(orderNo);
			
			addOrderItems(item);
		}
		
	}
	// -----------------------------------------------------------------------------------------
	
	// -----------------------------------------------------------------------------------------
	// OrderItems ���� ����
	public void addOrderItems(OrderItem orderItmes) {
		orderItemsDao.insert(orderItmes);
	}
	
	public List<OrderItem> getOrderItems(int orderNo) {
		List<OrderItem> list = orderItemsDao.selectByOrderNo(orderNo);
		
		return list;
	}
	// -----------------------------------------------------------------------------------------

	// -----------------------------------------------------------------------------------------
	
	
	public List<Order> getOrders(String memberId) {
		List<Order> list = ordersDao.selectByMemberId(memberId);
		
		return list;
	}	
	// -----------------------------------------------------------------------------------------

	// -----------------------------------------------------------------------------------------
	// Goods ���� ����
	public List<Goods> showGoods(int pageNo, int rowsPerPage) {
		List<Goods> list = goodsDao.selectByPage(pageNo, rowsPerPage);
		
		return list;
	}
	
	public Goods getGoods(int goodsNo) {
		Goods goods = goodsDao.selectByPk(goodsNo);
		
		return goods;
	}

	
	public Members getMembers(String memberId) {
		Members member = memberDao.selectByPk(memberId);
		
		return member;
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
