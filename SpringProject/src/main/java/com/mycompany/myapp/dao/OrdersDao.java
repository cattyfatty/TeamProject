package com.mycompany.myapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mycompany.myapp.dto.Orders;

public class OrdersDao {

	private Connection conn;
	public OrdersDao(Connection conn){
		this.conn = conn;
	}

	public Integer insert(Orders orders) throws SQLException {
		Integer pk = null;
		String sql = "insert into orders ( order_price,member_id) values (?,?)";
		PreparedStatement pstmt = conn.prepareStatement(sql, new String[]{"order_no"});


		pstmt.setInt(1, orders.getOrderPrice());
		pstmt.setString(2, orders.getMemberid());
		int row = pstmt.executeUpdate();
		if(row==1){
			ResultSet rs = pstmt.getGeneratedKeys();
			if(rs.next()){
				pk = rs.getInt(1);
			}
			rs.close();
		}
		pstmt.close();
		return pk;

	}

	public int delete(int orderNo) throws SQLException{
		int rows = 0;
		String sql = "delete from orderss where orders_no=?";


		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1,orderNo);
		rows = pstmt.executeUpdate();//������ insert�� ���� ��. insert���� update, delete � ����.

		System.out.println("�ֹ���ȣ"+ orderNo +"���� ������");

		return rows;
	}


	public List<Orders> selectByPage(int pageNo,int rowsPerPage) throws SQLException{
		List<Orders> list = new ArrayList<Orders>();
		String sql="";
		sql += "select rn, order_no, order_date, order_arrival, order_price, member_id ";
		sql += " from ";
		sql += " ( ";
		sql += " select rownum rn, order_no, order_date, order_arrival, order_price, member_id ";
		sql += " from ";
		sql += " ( ";
		sql += " select order_no, order_date, order_arrival, order_price, member_id " ;
		sql += " from orders ";
		sql += " order by order_no desc ";
		sql += " ) ";
		sql += " where rownum<=? ";
		sql += " ) ";
		sql += " where rn>=? ";	

		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, pageNo*rowsPerPage);
		pstmt.setInt(2, (pageNo-1)*rowsPerPage+1);

		ResultSet rs = pstmt.executeQuery();//select�� executeQuery!

		while(rs.next()){

			Orders orders= new Orders();
			orders.setOrderNo(rs.getInt("order_no"));
			orders.setOrderPrice(rs.getInt("order_price"));
			orders.setMemberid(rs.getString("member_id"));
			list.add(orders);
		}

		rs.close();

		pstmt.close();
		return list;

	}


	public Orders selectByPk(int orderNo) throws SQLException {
		Orders orders = null;
		String sql = "select * from orders where order_no=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1,  orderNo);
		ResultSet rs = pstmt.executeQuery();
		if(rs.next()) {
			orders = new Orders();
			orders.setMemberid(rs.getString("member_id"));
			orders.setOrderNo(rs.getInt("order_no"));
			orders.setOrderPrice(rs.getInt("order_price"));

		}
		rs.close();
		pstmt.close();
		return orders;
	}


	public List<Orders> selectByMemberId(String memberId) throws SQLException {
		List<Orders> list = new ArrayList<Orders>();
		String sql = "select * from orders where member_id=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1,  memberId);
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()){

			Orders orders= new Orders();
			orders.setOrderNo(rs.getInt("order_no"));
			orders.setOrderPrice(rs.getInt("order_price"));
			orders.setMemberid(rs.getString("member_id"));
			list.add(orders);
		}
		rs.close();
		pstmt.close();
		return list;
	}


}

