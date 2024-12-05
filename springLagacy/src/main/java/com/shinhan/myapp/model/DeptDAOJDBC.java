package com.shinhan.myapp.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.shinhan.myapp.vo.DeptDTO;

import lombok.extern.slf4j.Slf4j;
import net.starykids.util.DBUtil;

 
@Slf4j
@Repository("deptDAO3")
public class DeptDAOJDBC implements DeptDAOInterface {
	// Å¸ÀÔÀÌ °°À¸¸é ÀÚµ¿À¸·Î injection µÈ´Ù. (IOC, DI)
	@Autowired
	@Qualifier("dataSource")
	DataSource ds;
	
	String sql_selectAll = "select * from departments";
	String sql_selectById = "select * from departments where department_id = ?";
	String sql_insert = "insert into departments values (?,?,?,?)";
	String sql_update = "update departments set department_name = ?, manager_id = ?, location_id = ? where department_id = ?";
	String sql_delete = "delete from departments where department_id = ?";
	
	Connection conn;
	PreparedStatement st;
	ResultSet rs;
	int result;
	
	
	// 1. ëª¨ë“  ë¶??„œ ì¡°íšŒ?•˜ê¸?
	public List<DeptDTO> selectAll() {
		
		List<DeptDTO> deptlist = new ArrayList<DeptDTO>();
		try {
			conn = ds.getConnection(); //DB?—°ê²?
			st = conn.prepareStatement(sql_selectAll);
			rs = st.executeQuery();
			while(rs.next()) { //?‹¤?–‰?•œ ê²°ê³¼ê°? ?‡?„ ?•Œ ê¹Œì? add ë¥? ?•˜ê¸?
				DeptDTO dept = new DeptDTO(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4));
				deptlist.add(dept);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbDisconnect(conn, st, rs);
		}
		log.info("JDBC »ç¿ë >> " + deptlist.size());
		return deptlist;
	}



	// 2. ?ƒ?„¸ë³´ê¸°
	public DeptDTO selectById(int deptid) {
		
		DeptDTO dept = null;
		try {
			conn = ds.getConnection(); //DB?—°ê²?
			st = conn.prepareStatement(sql_selectById);
			st.setInt(1, deptid);
			rs = st.executeQuery();
			if(rs.next()) {
				dept = new DeptDTO(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbDisconnect(conn, st, rs);
		}
		
		return dept;
	}
	
	
	// 3. ?…? ¥
	public int insert(DeptDTO dept) { //?¸?„œ?Š¸ ?•˜ê³? ?‹¶??ê±? ë³??ˆ˜ê°? ë§ìœ¼?‹ˆê¹? ?•œ ?©?–´ë¦¬ë¡œ ê°?? ¸?˜¬?¼ê³? DTO
		
		PreparedStatement st = null;
		
		try {
			conn = ds.getConnection(); //DB?—°ê²?
			st = conn.prepareStatement(sql_insert); //?…? ¥ë¬¸ì¥ì¤?ë¹? ?4ê°œë‹ˆê¹? 4ê°?
			st.setInt(1, dept.getDepartment_id());  //dept ?—?„œ ?½?–´?˜¤ê¸?
			st.setString(2, dept.getDepartment_name());
			st.setInt(3, dept.getManager_id());
			st.setInt(4, dept.getLocation_id());
			
			result = st.executeUpdate(); //?¸?„œ?Š¸ ?‹¤?–‰!
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbDisconnect(conn, st, rs);
		}
		
		return result;
		//?…? ¥?œ 1 'ê±?'?´ ?„±ê³µì´?‹ˆê¹? int
	}

	// 4. ?ˆ˜? •
	public int update(DeptDTO dept) {
		
		PreparedStatement st = null;
		
		try {
			conn = ds.getConnection(); //DB?—°ê²?
			st = conn.prepareStatement(sql_update); //??ˆœ?„œ ì£¼ì˜
			st.setInt(4, dept.getDepartment_id());
			st.setString(1, dept.getDepartment_name());
			st.setInt(2, dept.getManager_id());
			st.setInt(3, dept.getLocation_id());
			
			result = st.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbDisconnect(conn, st, rs);
		}
		return result;
		
	}

	// 5. ?‚­? œ
	public int delete(int dept_id) { 
		
		
		try {
			conn = ds.getConnection(); //DB?—°ê²?
			conn.setAutoCommit(false); //??™ì»¤ë°‹ ?•ˆ?•¨ 
			st = conn.prepareStatement(sql_delete); //ë¶??„œ?•„?´?”” ?‚­? œ
			st.setInt(1, dept_id); // ë¶??„œ?•„?´?”” 1ë²? ?‚­? œ
			result = st.executeUpdate();
			conn.commit(); //  ?— ë°˜ì˜?•œ?‹¤. ë§Œì•½ ?œ„?—?„œ ì»¤ë°‹ ?‹¤?Œ¨?–ˆ?œ¼ë©? catch ?—?„œ ë¡¤ë°±
		} catch (SQLException e) {
			try {
				conn.rollback(); //DB?— ?‘?—…?•œ ?‚´?š© ì·¨ì†Œ
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			DBUtil.dbDisconnect(conn, st, rs);
		}
		
		return result;
		
	}



}
