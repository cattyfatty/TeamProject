package com.mycompany.myapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mycompany.myapp.dto.Goods;
import com.mycompany.myapp.dto.Product;

public class GoodsDao {
	private Connection conn;

	// ������ ���� ���
	public GoodsDao(Connection conn) {
		this.conn = conn;
	}

	// ������ �۾� �޼ҵ�
	public Integer insert(Goods product) throws SQLException {
		Integer pk = null;
		String sql = "insert into products (product_name, product_price, product_calory, product_gift, product_size,product_kind) values ( ?, ?, ?, ?, ?,?)";
		PreparedStatement pstmt = null;
		pstmt = conn.prepareStatement(sql, new String[] { "product_no" });
		pstmt.setString(1, product.getName());
		pstmt.setInt(2, product.getPrice());
		pstmt.setInt(3, product.getCalory());
		pstmt.setString(4, product.getGift());
		pstmt.setString(5, product.getSize());
		pstmt.setString(6, product.getKind());
		int row = pstmt.executeUpdate();
		if (row == 1) {
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				pk = rs.getInt(1);
			}
			rs.close();
		}
		pstmt.close();
		return pk;
	}

	// Update

	public int update(Goods product) throws SQLException {
		int rows = 0;
		String sql = "update products set product_name=?, product_price=?, product_calory=?, product_gift=?, product_size=? ,product_kind=? where product_no=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);

		pstmt.setString(1, product.getName());
		pstmt.setInt(2, product.getPrice());
		pstmt.setInt(3, product.getCalory());
		pstmt.setString(4, product.getGift());
		pstmt.setString(5, product.getSize());
		pstmt.setString(6, product.getKind());
		pstmt.setInt(7, product.getNo());
		rows = pstmt.executeUpdate();
		pstmt.close();
		return rows;
	}

	// Delete

	public int delete(int productNo) throws SQLException {
		int rows = 0;
		String sql = "delete from prducts where product_no=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);

		pstmt.setInt(1, productNo);

		rows = pstmt.executeUpdate();
		pstmt.close();
		return rows;
	}

	// �� ���� ���� �������� Select

	public Goods selectByPk(int productNo) throws SQLException {

		Goods product = null;

		String sql = "select * from products where product_no=?";

		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, productNo);
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			product = new Goods();
			product.setNo(rs.getInt("product_no"));
			product.setName(rs.getString("product_name"));
			product.setPrice(rs.getInt("product_price"));
			product.setCalory(rs.getInt("product_calory"));
			product.setGift(rs.getString("product_gift"));
			product.setSize(rs.getString("product_size"));
			product.setKind(rs.getString("product_kind"));
		}
		rs.close();
		pstmt.close();
		return product;
	}

	public List<Goods> selectByPage(int pageNo, int rowsPerPage) throws SQLException {
		List<Goods> list = new ArrayList<Goods>();
		String sql = "";
		sql += "select rn, product_no, product_name, product_price, product_calory, product_gift, product_size,product_kind ";
		sql += "from ";
		sql += "( ";
		sql += "select rownum rn, product_no, product_name, product_price, product_calory, product_gift, product_size ,product_kind ";
		sql += "from ";
		sql += "( ";
		sql += "select product_no, product_name, product_price, product_calory, product_gift, product_size,product_kind ";
		sql += "from products ";
		sql += "order by product_no desc ";
		sql += ") ";
		sql += "where rownum<=? ";
		sql += ") ";
		sql += "where rn>=? ";

		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, pageNo * rowsPerPage);
		pstmt.setInt(2, (pageNo - 1) * rowsPerPage + 1);

		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {

			Goods product = new Goods();
			product.setNo(rs.getInt("product_no"));
			product.setName(rs.getString("product_name"));
			product.setPrice(rs.getInt("product_price"));
			product.setCalory(rs.getInt("product_calory"));
			product.setGift(rs.getString("product_gift"));
			product.setSize(rs.getString("product_size"));
			product.setKind(rs.getString("product_kind"));
			list.add(product);

		}
		rs.close();
		pstmt.close();
		return list;

	}
}