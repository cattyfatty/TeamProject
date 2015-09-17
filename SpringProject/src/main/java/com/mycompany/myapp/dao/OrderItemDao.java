package com.mycompany.myapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import com.mycompany.myapp.dto.Board;
import com.mycompany.myapp.dto.OrderItem;
@Component
public class OrderItemDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	
	public Integer insert(OrderItem orderitems) {
		Integer pk = null;
		String sql ="insert into orderitems (order_no, goods_no, orderitem_amount) values (?, ?, ?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
				PreparedStatement pstmt = conn.prepareStatement(sql, new String[] { "orderitem_no" });
				pstmt.setInt(1, orderitems.getOrderNo());
				pstmt.setInt(2, orderitems.getGoodsItemNo());
				pstmt.setInt(3, orderitems.getOrderAmount());
				return pstmt;
			}
		}, keyHolder);

		Number keyNumber = keyHolder.getKey();
		pk = keyNumber.intValue();
		return pk;
	
	}
	
	public List<OrderItem> selectByPage(int pageNo, int rowsPerPage)  {
		String sql = "";
		sql += "select orderitem_no, order_no, goods_no, orderitem_amount ";
		sql += "from orderitems ";
		sql += "order by orderitem_no desc ";
		sql += "limit ?,?";

		List<OrderItem> list = jdbcTemplate.query(sql, new Object[] { (pageNo - 1) * rowsPerPage, rowsPerPage },
				new RowMapper<OrderItem>() {
					@Override
					public OrderItem mapRow(ResultSet rs, int rowNum) throws SQLException {
						OrderItem orderItems = new OrderItem();
						orderItems.setOrderItemNo(rs.getInt("orderitem_no"));
						orderItems.setOrderNo(rs.getInt("order_no"));
						orderItems.setGoodsItemNo(rs.getInt("goods_no"));
						orderItems.setOrderAmount(rs.getInt("orderitem_amount"));
						return orderItems;
					}
				});

		return list;
	}
	
	public int update(OrderItem orderItems)  {
		int rows = 0;
		String sql = "update orderitems set order_no=?, goods_no=?, "
				+ "orderitem_amount=? where orderitem_no=?";		
		rows = jdbcTemplate.update(sql,orderItems.getOrderNo(),orderItems.getGoodsItemNo(),
									orderItems.getOrderAmount(),orderItems.getOrderItemNo());
		return rows;
	}
	
	public int delete(int OrderItemNo)  {
		
		String sql = "delete from orderitems where orderitem_no=?";
		int rows = jdbcTemplate.update(sql, OrderItemNo);
		
		return rows;
	}
	
	
	public List<OrderItem> selectByOrderNo(int OrderNo) {
	
		String sql = "select * from orderitems where order_no=?";
		
		List<OrderItem> list = jdbcTemplate.query(sql, new Object[] {OrderNo},
				new RowMapper<OrderItem>() {
					@Override
					public OrderItem mapRow(ResultSet rs, int rowNum) throws SQLException {
						OrderItem orderItems = new OrderItem(); 
						orderItems.setOrderAmount(rs.getInt("orderitem_amount"));
						orderItems.setOrderItemNo(rs.getInt("orderitem_no"));
						orderItems.setOrderNo(rs.getInt("order_no"));
						orderItems.setGoodsItemNo(rs.getInt("goods_no"));
						return orderItems;
					}
				});
		
		return list;
	}
	
	public OrderItem selectByPk(int OrderItemNo) {
		String sql = "select * from orderitems where orderitem_no=?";
		
		OrderItem orderItems = jdbcTemplate.queryForObject(
				sql,
				new Object[] {OrderItemNo},
				new RowMapper<OrderItem> () {
					@Override
					public OrderItem mapRow(ResultSet rs, int rowNum) throws SQLException {
						OrderItem orderItems = new OrderItem();
						orderItems.setOrderAmount(rs.getInt("orderitem_amount"));
						orderItems.setOrderItemNo(rs.getInt("orderitem_no"));
						orderItems.setOrderNo(rs.getInt("order_no"));
						orderItems.setGoodsItemNo(rs.getInt("goods_no"));
						return orderItems;
					}
				});
		
		return orderItems;
	}
	
}
