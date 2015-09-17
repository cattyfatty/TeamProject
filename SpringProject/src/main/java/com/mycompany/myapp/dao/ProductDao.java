package com.mycompany.myapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import com.mycompany.myapp.dto.Product;

@Component
public class ProductDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public Integer insert(Product product) {
		Integer pk = null;
		String sql = "insert into products(product_name, product_price, product_amount, product_kind, product_content,"
				+ "product_original_file_name, product_filesystem_name, product_content_type) values(?,?,?,?,?,?,?,?)";

		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
				PreparedStatement pstmt = conn.prepareStatement(sql, new String[] { "product_no" });
				pstmt.setString(1, product.getName());
				pstmt.setLong(2, product.getPrice());
				pstmt.setLong(3, product.getAmount());
				pstmt.setString(4, product.getKind());
				pstmt.setString(5, product.getContent());
				pstmt.setString(6, product.getOriginalFileName());
				pstmt.setString(7, product.getFilesystemName());
				pstmt.setString(8, product.getContentType());

				return pstmt;
			}
		}, keyHolder);

		Number keyNumber = keyHolder.getKey();
		pk = keyNumber.intValue();
		return pk;
	}

	public List<Product> selectByPage(int pageNo, int rowsPerPage) {
		String sql = "";
		sql += "select product_no, product_name, product_price, product_amount, product_kind ";
		sql += "from products ";
		sql += "ORDER BY product_no DESC ";
		sql += "limit ?,?";

		List<Product> list = jdbcTemplate.query(sql, new Object[] { (pageNo - 1) * rowsPerPage, rowsPerPage },
				new RowMapper<Product>() {

					@Override
					public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
						Product product = new Product();
						product.setNo(rs.getLong("product_no"));
						product.setName(rs.getString("product_name"));
						product.setPrice(rs.getInt("product_price"));
						product.setAmount(rs.getInt("product_amount"));
						product.setKind(rs.getString("product_kind"));

						return product;
					}
				});

		return list;
	}

	public Product selectByPk(long productNo) {
		String sql = "";
		sql += "select * from products where product_no=?";

		Product product = jdbcTemplate.queryForObject(sql, new Object[] { productNo }, new RowMapper<Product>() {

			@Override
			public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
				Product product = new Product();
				product.setNo(rs.getLong("product_no"));
				product.setName(rs.getString("product_name"));
				product.setPrice(rs.getInt("product_price"));
				product.setAmount(rs.getInt("product_amount"));
				product.setKind(rs.getString("product_kind"));
				product.setContent(rs.getString("product_content"));
				product.setOriginalFileName(rs.getString("product_original_file_name"));
				product.setFilesystemName(rs.getString("product_filesystem_name"));
				product.setContentType(rs.getString("product_content_type"));
				return product;
			}
		});

		return product;
	}

	public int update(Product product) {
		String sql = "update products set product_name=?, product_price=?, product_amount=?, product_content=? where product_no=?";
		int rows = jdbcTemplate.update(sql, product.getName(), product.getPrice(), product.getAmount(), product.getContent());

		return rows;
	}

	public int delete(long productNo) {
		String sql = "delete from products where product_no=?";
		
		int rows = jdbcTemplate.update(sql, productNo);
		
		return rows;
	}
	
	public int selectCount() {
		String sql = "SELECT COUNT(*) FROM products";
		int rows = jdbcTemplate.queryForObject(sql, Integer.class);
		
		return rows;
	}
}
