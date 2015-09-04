package com.mycompany.myapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mycompany.myapp.dto.Cart;



public class CartDao {
private Connection conn;
	
	//������ ���� ���
	public CartDao(Connection conn) {
		this.conn = conn;
	}
	
	public Integer insert(Cart cart) throws SQLException {
		Integer pk = null;
		String sql = "insert into carts (cart_amount, member_id, product_no) values (?, ?, ?)";	
		PreparedStatement pstmt = conn.prepareStatement(sql, new String[]{"cart_no"});
		pstmt.setInt(1, cart.getcartAmount());
		pstmt.setString(2,cart.getmemberId());
		pstmt.setInt(3, cart.getproductNo());
	
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
		public int update(Cart cart) throws SQLException {
			int rows = 0;
			String sql = "update carts set cart_amount=?, member_id =?, product_no=? where cart_no=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cart.getcartAmount());
			pstmt.setString(2, cart.getmemberId());
			pstmt.setInt(3, cart.getproductNo());
			pstmt.setInt(4, cart.getcartNo());
			rows = pstmt.executeUpdate();
			pstmt.close();
			return rows;  //update�� ����� ��ȯ
		}

	
	public int delete(int CartNo) throws SQLException {
		int rows = 0;
		String sql = "delete from Carts where Cart_no=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, CartNo);  //pstmt�� ?�� ���� ���� ���µ� ����Ѵ�.
		rows = pstmt.executeUpdate();
		pstmt.close();
		return rows;
	}
	
	public Cart selectByPk(int CartNo) throws SQLException {
		Cart Cart = null;
		String sql = "select * from Carts where cart_no=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1,  CartNo);
		ResultSet rs = pstmt.executeQuery();
		if(rs.next()) {
			Cart = new Cart();
			Cart.setcartNo( rs.getInt("cart_no") );
			Cart.setcartAmount(rs.getInt("cart_amount"));
			Cart.setcartDate(rs.getDate("cart_date"));
			Cart.setmemberId(rs.getString("member_id"));
			Cart.setproductNo(rs.getInt("product_no"));
		}
		rs.close();
		pstmt.close();
		return Cart;
	}
	
	public List<Cart> selectByid(String memid) throws SQLException {
		List<Cart> list = new ArrayList<Cart>();
		String sql = "select * from Carts where member_id=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1,  memid);
		
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			Cart cart = new Cart();
			cart.setcartNo( rs.getInt("cart_no") );
			cart.setcartAmount(rs.getInt("cart_amount"));
			cart.setcartDate(rs.getDate("cart_date"));
			cart.setmemberId(rs.getString("member_id"));
			cart.setproductNo(rs.getInt("product_no"));
			list.add(cart);
		}
		rs.close();
		pstmt.close();
		return list;
	}
	
	public List<Cart> selectByPage(int pageNo, int rowsPerPage) throws SQLException {
		List<Cart> list = new ArrayList<Cart>();
		String sql = "";
		sql += "select rn, cart_no, cart_amount, cart_date, product_no, member_id ";
		sql += "from ";
		sql += "( ";
		sql += "select rownum rn, cart_no, cart_amount, cart_date, product_no, member_id ";
		sql += "from ";
		sql += "( ";
		sql += "select cart_no, cart_amount, cart_date, product_no, member_id " ;
		sql += "from Carts ";
		sql += "order by cart_no desc ";
		sql += ") ";
		sql += "where rownum<=? ";
		sql += ") ";
		sql += "where rn>=? ";	
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, pageNo*rowsPerPage);
		pstmt.setInt(2, (pageNo-1)*rowsPerPage+1);
		
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			Cart cart = new Cart();
			cart.setcartNo( rs.getInt("cart_no") );
			cart.setcartAmount(rs.getInt("cart_amount"));
			cart.setcartDate(rs.getDate("cart_date"));
			cart.setmemberId(rs.getString("member_id"));
			cart.setproductNo(rs.getInt("product_no"));
			list.add(cart);
		}
		rs.close();
		pstmt.close();
		return list;
	}

	public void deleteByMemberId(String memberId) throws SQLException{
		String sql = "delete from Carts where member_id=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, memberId);  //pstmt�� ?�� ���� ���� ���µ� ����Ѵ�.
		pstmt.executeUpdate();
		pstmt.close();
	}
}




