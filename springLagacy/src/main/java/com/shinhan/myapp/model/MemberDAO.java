package com.shinhan.myapp.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.shinhan.myapp.vo.MemberDTO;

@Repository  //@component + DAO 기능 → Bean객체
public class MemberDAO {

	@Autowired
	@Qualifier("dataSource")
	DataSource ds;

	Connection conn;
	PreparedStatement st;
	ResultSet rs;
	String select_login = "select * from members where member_id=?";

	public MemberDTO login(String mid, String mpw) {
		MemberDTO member = null;
		try {
			conn = ds.getConnection();
			st = conn.prepareStatement(select_login);
			st.setString(1, mid);
			rs = st.executeQuery();
			if (rs.next()) {
				String getPw = rs.getString("member_pw");
				if (mpw.equals(getPw)) {
					
					member = new MemberDTO(mid, mpw, rs.getString("member_name"), rs.getString("member_email"));
				} else {
					
					member = MemberDTO.builder().member_id("-1").build();
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return member;
	}
}
