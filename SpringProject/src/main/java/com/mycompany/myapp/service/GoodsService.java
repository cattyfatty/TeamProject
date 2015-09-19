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
		Members isExist = memberDao.selectByPk(member.getId());
		if (isExist.getPassword().equals(member.getPassword())) {
			return isExist;
		} else
			return null;
	}
	// -----------------------------------------------------------------------------------------

	// -----------------------------------------------------------------------------------------
	// Cart ���� ����
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
		for (Cart cart : cartList) {
			
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
	// Orders ���� ����
	public void addOrder(Order orders) {
		ordersDao.insert(orders);
	}
	
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
	
	
	public int getTotalOrderNo() {
		int rows = ordersDao.selectCount();
			
			return rows;

		}

		public List<Order> getOrderPage(int pageNo, int rowsPerPage, String memberId) {
			
			List<Order> list = ordersDao.selectByPage(pageNo, rowsPerPage, memberId);
			
			return list;
		}
	
		public int getTotalOrderItemNo() {
			int rows = ordersDao.selectCount();
				
				return rows;

			}

		public List<OrderItem> getOrderItemPage(int orderNo,int pageNo, int rowsPerPage) {
			
				List<OrderItem> list = orderItemsDao.selectByPage(orderNo, pageNo, rowsPerPage);
				
				return list;
			}

	// -----------------------------------------------------------------------------------------
}
