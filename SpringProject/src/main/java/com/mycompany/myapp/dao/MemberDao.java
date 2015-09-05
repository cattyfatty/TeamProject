package com.mycompany.myapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.mycompany.myapp.dto.Members;

@Component
public class MemberDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public void insert(Members member){
		
		String sql = "insert into members"
				+ "(member_id, member_password, member_name, member_address, member_tel, member_email)"
				+ "values(?,?,?,?,?,?)";
		
		jdbcTemplate.update(new PreparedStatementCreator() {
		
		@Override
		public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
			PreparedStatement pstmt = conn.prepareStatement(sql, new String[] {"member_id"});
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getPassword());
			pstmt.setString(3, member.getName());
			pstmt.setString(4, member.getAddress());
			pstmt.setString(5, member.getTel());
			pstmt.setString(6, member.getEmail());
			return pstmt;
			}
		});
	}
	
	public int update(Members member) throws SQLException{
		String sql = "update members set member_password=?, member_tel=? member_email=? where member_id=?";
			
	int rows = jdbcTemplate.update(sql, member.getPassword(), member.getTel(), member.getEmail(), member.getId());
		
		return rows;
	}
		
	public int delete(String memberId){
		String sql = "delete from members where member_id=?";
		int rows = jdbcTemplate.update(sql, memberId);
		return rows;
	}
	
	public Members selectByPk(String memberId){
		String sql = "select * from members where member_id = ?";
		
		Members member = jdbcTemplate.queryForObject(
				sql,
				new Object[] {memberId},
				new RowMapper<Members>(){
					@Override
					public Members mapRow(ResultSet rs, int rowNum) throws SQLException {
					Members member = new Members();
					member.setId(rs.getString("member_id"));
					member.setPassword(rs.getString("member_password"));
					member.setName(rs.getString("member_name"));
					member.setAddress(rs.getString("member_address"));
					member.setTel(rs.getString("member_tel"));
					member.setEmail(rs.getString("member_email"));
					return member;	
					
				}
			});

		return member;
	}
	
}
