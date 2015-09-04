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

import com.mycompany.myapp.dto.Goods;
@Component
public class GoodsDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;


	public Integer insert(Goods goods)  {
		Integer pk = null;
		String sql = "insert into goods (goods_name, goods_price, goods_calory, goods_gift, goods_size,goods_kind) values ( ?, ?, ?, ?, ?,?)";

		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
				PreparedStatement pstmt = conn.prepareStatement(sql, new String[] { "goods_no" });
				pstmt.setString(1, goods.getName());
				pstmt.setInt(2, goods.getPrice());
				pstmt.setInt(3, goods.getCalory());
				pstmt.setString(4, goods.getGift());
				pstmt.setString(5, goods.getSize());
				pstmt.setString(6, goods.getKind());
				return pstmt;
			}
		}, keyHolder);

		Number keyNumber = keyHolder.getKey();
		pk = keyNumber.intValue();
		return pk;
		
	}

	// Update

	public int update(Goods goods)  {
		int rows = 0;
		
		String sql = "update goods set goods_name=?, goods_price=?, "
				+  " goods_calory=?, goods_gift=?, goods_size=? ,goods_kind=? where goods_no=?";
		
		rows = jdbcTemplate.update(sql,goods.getName(),goods.getPrice()
				,goods.getCalory(),goods.getGift(),goods.getSize(),goods.getKind(),goods.getNo());
		
		return rows;
	}

	// Delete

	public int delete(int goodsNo)  {
		int rows = 0;
		String sql = "delete from goods where goods_no=?";
		rows = jdbcTemplate.update(sql, goodsNo);
		return rows;
	}

	public Goods selectByPk(int goodsNo) {
		String sql = "select * from goods where goods_no=?";
		Goods goods = jdbcTemplate.queryForObject(
				sql,
				new Object[] {goodsNo},
				new RowMapper<Goods> () {
					@Override
					public Goods mapRow(ResultSet rs, int rowNum) throws SQLException {
						Goods goods = new Goods();
						goods.setNo(rs.getInt("goods_no"));
						goods.setName(rs.getString("goods_name"));
						goods.setPrice(rs.getInt("goods_price"));
						goods.setCalory(rs.getInt("goods_calory"));
						goods.setGift(rs.getString("goods_gift"));
						goods.setSize(rs.getString("goods_size"));
						goods.setKind(rs.getString("goods_kind"));
						return goods;
					}
				});
		return goods;
	}

	public List<Goods> selectByPage(int pageNo, int rowsPerPage){
		
		String sql = "";
		sql += " select goods_no, goods_name, goods_price, goods_calory, goods_gift, goods_size,goods_kind ";
		sql += "from goods ";
		sql += "order by goods_no desc ";
		sql += "limit ?,?";

		List<Goods> list = jdbcTemplate.query(sql, new Object[] { (pageNo - 1) * rowsPerPage, rowsPerPage },
				new RowMapper<Goods>() {
					@Override
					public Goods mapRow(ResultSet rs, int rowNum) throws SQLException {
						Goods goods = new Goods();
						goods.setNo(rs.getInt("goods_no"));
						goods.setName(rs.getString("goods_name"));
						goods.setPrice(rs.getInt("goods_price"));
						goods.setCalory(rs.getInt("goods_calory"));
						goods.setGift(rs.getString("goods_gift"));
						goods.setSize(rs.getString("goods_size"));
						goods.setKind(rs.getString("goods_kind"));

						return goods;
					}
				});

		return list;

	}
}