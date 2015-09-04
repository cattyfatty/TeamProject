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
		String sql = "insert into carts (cart_amount, member_id, goods_no) values (?, ?, ?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
				PreparedStatement pstmt = conn.prepareStatement(sql, new String[]{"cart_no"});
				pstmt.setInt(1, cart.getcartAmount());
				pstmt.setString(2,cart.getmemberId());
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
						cart.getcartAmount(),
						cart.getmemberId(),
						cart.getGoods_no(),
						cart.getcartNo()
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
						cart.setcartNo( rs.getInt("cart_no") );
						cart.setcartAmount(rs.getInt("cart_amount"));
						cart.setcartDate(rs.getDate("cart_date"));
						cart.setmemberId(rs.getString("member_id"));
						cart.setGoods_no(rs.getInt("goods_no"));
						return cart;
					}
			
		});
	
		return cart;
	}
	
	public List<Cart> selectByid(String memid){
		String sql = "select * from carts where member_id=?";
		List<Cart> list = jdbcTemplate.query(sql
						,new Object[]{memid},
						new RowMapper<Cart>(){
			@Override
			public Cart mapRow(ResultSet rs, int rowNum) throws SQLException {
				Cart cart= new Cart();
				cart.setcartNo( rs.getInt("cart_no") );
				cart.setcartAmount(rs.getInt("cart_amount"));
				cart.setcartDate(rs.getDate("cart_date"));
				cart.setmemberId(rs.getString("member_id"));
				cart.setGoods_no(rs.getInt("goods_no"));
				return cart;
			}
			
						
		
		});
		
		return list;
	}
	
	public List<Cart> selectByPage(int pageNo, int rowsPerPage){
		String sql = "";
		sql += " select cart_no, cart_amount, cart_date, goods_no, member_id  ";
		sql += "from carts ";
		sql += "order by cart_no desc ";
		sql += "limit ?,?";
		List<Cart> list = jdbcTemplate.query(
				sql,
				new Object[]{(pageNo-1)*rowsPerPage, rowsPerPage},
				new RowMapper<Cart>(){

					@Override
					public Cart mapRow(ResultSet rs, int rowNum) throws SQLException {
						
						Cart cart = new Cart();
						cart.setcartNo( rs.getInt("cart_no") );
						cart.setcartAmount(rs.getInt("cart_amount"));
						cart.setcartDate(rs.getDate("cart_date"));
						cart.setmemberId(rs.getString("member_id"));
						cart.setGoods_no(rs.getInt("goods_no"));
						return cart;
					}
					
				});
		
		return list;
	}

	

}




