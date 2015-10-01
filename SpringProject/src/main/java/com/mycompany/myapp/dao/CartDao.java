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

import com.mycompany.myapp.dto.Cart;
import com.sun.org.apache.xalan.internal.xsltc.compiler.Template;


@Component
public class CartDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public Integer insert(Cart cart){
		Integer pk = null;
		String sql = "insert into carts (cart_amount, member_id, goods_no,cart_date) values (?, ?, ?,now())";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
				PreparedStatement pstmt = conn.prepareStatement(sql, new String[]{"cart_no"});
				pstmt.setInt(1, cart.getCart_amount());
				pstmt.setString(2,cart.getMember_id());
				pstmt.setInt(3, cart.getGoods_no());
				return pstmt;
			}
		},keyHolder);
		Number keyNumber = keyHolder.getKey();
		pk = keyNumber.intValue();
		return pk;
	}
		public int update(Cart cart){
			String sql = "update carts set cart_amount=?, member_id =?, goods_no=? where cart_no=?";
			int rows = jdbcTemplate.update(
						sql,
						cart.getCart_amount(),
						cart.getMember_id(),
						cart.getGoods_no(),
						cart.getCart_no()
					);
		
			return rows;
		}

	
	public int delete(String MemberId){
		String sql = "delete from carts where member_id=?";
		int rows = jdbcTemplate.update(
					sql,
					MemberId
				);
		
		return rows;
	}
	
	public Cart selectByPk(int CartNo){
		String sql = "select * from carts where cart_no=?";
		Cart cart = jdbcTemplate.queryForObject(sql,
				new Object[]{CartNo},
				new RowMapper<Cart>(){

					@Override
					public Cart mapRow(ResultSet rs, int rowNum) throws SQLException {
						Cart cart= new Cart();
						cart.setCart_no( rs.getInt("cart_no") );
						cart.setCart_amount(rs.getInt("cart_amount"));
						cart.setCart_date(rs.getDate("cart_date"));
						cart.setMember_id(rs.getString("member_id"));
						cart.setGoods_no(rs.getInt("goods_no"));
						return cart;
					}
			
		});
	
		return cart;
	}
	
	public List<Cart> selectById(String memid){
		String sql = "select * from carts where member_id=?";
		List<Cart> list = jdbcTemplate.query(sql
						,new Object[]{memid},
						new RowMapper<Cart>(){
			@Override
			public Cart mapRow(ResultSet rs, int rowNum) throws SQLException {
				Cart cart= new Cart();
				cart.setCart_no( rs.getInt("cart_no") );
				cart.setCart_amount(rs.getInt("cart_amount"));
				cart.setCart_date(rs.getDate("cart_date"));
				cart.setMember_id(rs.getString("member_id"));
				cart.setGoods_no(rs.getInt("goods_no"));
				return cart;
			}
			
						
		
		});
		
		return list;
	}
	
	public List<Cart> selectByPage(int pageNo, int rowsPerPage, String memberId){
		String sql = "";
		sql += "select cart_no, cart_amount, cart_date, goods_no, member_id ";
		sql += "from carts ";
		sql += "where member_id=? ";
		sql += "order by cart_no desc ";
		sql += "limit ?,?";
		List<Cart> list = jdbcTemplate.query(
				sql,
				new Object[]{memberId, (pageNo-1)*rowsPerPage, rowsPerPage},
				new RowMapper<Cart>(){

					@Override
					public Cart mapRow(ResultSet rs, int rowNum) throws SQLException {
						
						Cart cart = new Cart();
						cart.setCart_no( rs.getInt("cart_no") );
						cart.setCart_amount(rs.getInt("cart_amount"));
						cart.setCart_date(rs.getDate("cart_date"));
						cart.setMember_id(rs.getString("member_id"));
						cart.setGoods_no(rs.getInt("goods_no"));
						return cart;
					}
				});
		
		return list;
	}

	

}




