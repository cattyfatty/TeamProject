package com.mycompany.myapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mycompany.myapp.dto.OrderItems;

public class OrderItemsDao {

	private Connection conn = null;
	
	public OrderItemsDao(Connection conn){
	this.conn = conn;
	}
	
	public Integer insert(OrderItems orderitems) throws SQLException{
		Integer pk = null;
		String sql ="insert into orderitems (ORDER_NO, PRODUCT_NO, ORDERITEM_AMOUNT) values (?, ?, ?)";	;
		PreparedStatement pstmt = conn.prepareStatement(sql,new String[]{"ORDERITEM_NO"} );
		
		pstmt.setInt(1, orderitems.getOrderNo());
		pstmt.setInt(2, orderitems.getProductItemNo());
		pstmt.setInt(3, orderitems.getOrderAmount());

		int row = pstmt.executeUpdate();
		if(row == 1) {
			ResultSet rs = pstmt.getGeneratedKeys();
			if(rs.next()) {
				pk = rs.getInt(1);  
			}
			rs.close();
		}
		pstmt.close();
		return pk;
	
	}
	
	public List<OrderItems> selectByPage(int pageNo, int rowsPerPage) throws SQLException {
		List<OrderItems> list = new ArrayList<OrderItems>();
		String sql = "";
		sql += "select rn, orderitem_no, order_no, product_no, orderitem_amount ";
		sql += "from ";
		sql += "( ";
		sql += "select rownum rn, orderitem_no, order_no, product_no, orderitem_amount ";
		sql += "from ";
		sql += "( ";
		sql += "select orderitem_no, order_no, product_no, orderitem_amount " ;
		sql += "from orderitems ";
		sql += "order by orderitem_no desc ";
		sql += ") ";
		sql += "where rownum<=? ";
		sql += ") ";
		sql += "where rn>=? ";	
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, pageNo*rowsPerPage);
		pstmt.setInt(2, (pageNo-1)*rowsPerPage+1);
		
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			OrderItems orderItems = new OrderItems();
			orderItems.setOrderItemNo(rs.getInt("orderitem_no"));
			orderItems.setOrderNo(rs.getInt("order_no"));
			orderItems.setProductItemNo(rs.getInt("product_no"));
			orderItems.setOrderAmount(rs.getInt("orderitem_amount"));
			list.add(orderItems);
		}
		
		rs.close();
		pstmt.close();
		return list;
	}
	
	public int update(OrderItems orderItems) throws SQLException {
		int rows = 0;
		String sql = "update OrderItems set Order_no=?, product_no=?, orderItem_amount=? where orderItem_no=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, orderItems.getOrderNo());
		pstmt.setInt(2, orderItems.getProductItemNo());
		pstmt.setInt(3, orderItems.getOrderAmount());
		pstmt.setInt(4, orderItems.getOrderItemNo());
		rows = pstmt.executeUpdate();
		pstmt.close();
		return rows;
	}
	
	public int delete(int OrderItemNo) throws SQLException {
		int rows = 0;
		String sql = "delete from OrderItems where OrderItem_no=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, OrderItemNo);
		rows = pstmt.executeUpdate();
		pstmt.close();
		return rows;
	}
	
	public List<OrderItems> selectByOrderNo(int OrderNo) throws SQLException {
		List<OrderItems> list= new ArrayList();
		
		String sql = "select * from OrderItems where order_no=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, OrderNo);
		
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			OrderItems orderItems = new OrderItems(); 
			orderItems.setOrderAmount(rs.getInt("orderitem_amount"));
			orderItems.setOrderItemNo(rs.getInt("orderitem_no"));
			orderItems.setOrderNo(rs.getInt("order_no"));
			orderItems.setProductItemNo(rs.getInt("product_no"));
			list.add(orderItems);
		}
		rs.close();
		pstmt.close();
		return list;
	}
	
	public OrderItems selectByPk(int boardNo) throws SQLException {
		OrderItems orderItems = null;
		String sql = "select * from OrderItems where orderitem_no=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1,  boardNo);
		ResultSet rs = pstmt.executeQuery();
		if(rs.next()) {
			orderItems = new OrderItems();
			orderItems.setOrderAmount(rs.getInt("orderitem_amount"));
			orderItems.setOrderItemNo(rs.getInt("orderitem_no"));
			orderItems.setOrderNo(rs.getInt("order_no"));
			orderItems.setProductItemNo(rs.getInt("product_no"));
		}
		rs.close();
		pstmt.close();
		return orderItems;
	}
	
}
