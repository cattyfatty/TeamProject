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

import com.mycompany.myapp.dto.Order;

@Component
public class OrderDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public Integer insert(Order orders) {
		Integer pk = null;
		String sql = "insert into orders (order_price, member_id) values (?,?)";

		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
				PreparedStatement pstmt = conn.prepareStatement(sql, new String[] { "order_no" });
				pstmt.setInt(1, orders.getOrderPrice());
				pstmt.setString(2, orders.getMemberid());
				return pstmt;
			}
		}, keyHolder);
		Number keyNumber = keyHolder.getKey();
		// getKey()로 orderNo를 얻을 수 있음
		pk = keyNumber.intValue();
		return pk;

	}

	public int delete(int orderNo) {
		String sql = "delete from orders where orders_no=?";
		int rows = jdbcTemplate.update(sql, orderNo);
		return rows;
	}

	public List<Order> selectByPage(int pageNo, int rowsPerPage) {

		String sql = "";
		sql += "select order_no, order_price, member_id ";
		sql += " from orders ";
		sql += " order by order_no desc ";
		sql += "limit ?,?";

		List<Order> list = jdbcTemplate.query(sql, new Object[] { (pageNo - 1) * rowsPerPage, rowsPerPage },
				new RowMapper<Order>() {

					@Override
					public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
						Order orders = new Order();
						orders.setOrderNo(rs.getInt("order_no"));
						orders.setOrderPrice(rs.getInt("order_price"));
						orders.setMemberid(rs.getString("member_id"));
						return orders;
					}

				}

		);
		return list;

	}

	public Order selectByPk(int orderNo) {

		String sql = "select * from orders where order_no=?";
		Order orders = jdbcTemplate.queryForObject(sql, new Object[] { orderNo }, new RowMapper<Order>() {

			@Override
			public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
				Order orders = new Order();
				orders.setMemberid(rs.getString("member_id"));
				orders.setOrderNo(rs.getInt("order_no"));
				orders.setOrderPrice(rs.getInt("order_price"));
				return orders;
			}
		}

		);
		return orders;
	}

	public List<Order> selectByMemberId(String memberId) {

		String sql = "select * from orders where member_id=?";

		List<Order> list = jdbcTemplate.query(sql, new Object[] { memberId }, new RowMapper<Order>() {

			@Override
			public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
				Order orders = new Order();
				orders.setOrderNo(rs.getInt("order_no"));
				orders.setOrderPrice(rs.getInt("order_price"));
				orders.setMemberid(rs.getString("member_id"));
				return orders;
			}

		}

		);
		return list;
	}

}
