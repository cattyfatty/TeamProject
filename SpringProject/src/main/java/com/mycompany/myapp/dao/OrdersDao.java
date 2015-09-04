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

import com.mycompany.myapp.dto.Orders;

@Component
public class OrdersDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public Integer insert(Orders orders) throws SQLException {
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

	public List<Orders> selectByPage(int pageNo, int rowsPerPage) {

		String sql = "";
		sql += "select order_no, order_date, order_arrival, order_price, member_id ";
		sql += " from ";
		sql += " order by order_no desc ";
		sql += "limit ?,?";

		List<Orders> list = jdbcTemplate.query(sql, new Object[] { (pageNo - 1) * rowsPerPage, rowsPerPage },
				new RowMapper<Orders>() {

					@Override
					public Orders mapRow(ResultSet rs, int rowNum) throws SQLException {
						Orders orders = new Orders();
						orders.setOrderNo(rs.getInt("order_no"));
						orders.setOrderPrice(rs.getInt("order_price"));
						orders.setMemberid(rs.getString("member_id"));
						return orders;
					}

				}

		);
		return list;

	}

	public Orders selectByPk(int orderNo) {

		String sql = "select * from orders where order_no=?";
		Orders orders = jdbcTemplate.queryForObject(sql, new Object[] { orderNo }, new RowMapper<Orders>() {

			@Override
			public Orders mapRow(ResultSet rs, int rowNum) throws SQLException {
				Orders orders = new Orders();
				orders.setMemberid(rs.getString("member_id"));
				orders.setOrderNo(rs.getInt("order_no"));
				orders.setOrderPrice(rs.getInt("order_price"));
				return orders;
			}
		}

		);
		return orders;
	}

	public List<Orders> selectByMemberId(String memberId) {

		String sql = "select * from orders where member_id=?";

		List<Orders> list = jdbcTemplate.query(sql, new Object[] { memberId }, new RowMapper<Orders>() {

			@Override
			public Orders mapRow(ResultSet rs, int rowNum) throws SQLException {
				Orders orders = new Orders();
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
