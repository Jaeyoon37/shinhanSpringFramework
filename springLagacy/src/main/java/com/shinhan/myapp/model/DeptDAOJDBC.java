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
	// 타입이 같으면 자동으로 injection 된다. (IOC, DI)
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
	
	
	// 1. 紐⑤뱺 遺??꽌 議고쉶?븯湲?
	public List<DeptDTO> selectAll() {
		
		List<DeptDTO> deptlist = new ArrayList<DeptDTO>();
		try {
			conn = ds.getConnection(); //DB?뿰寃?
			st = conn.prepareStatement(sql_selectAll);
			rs = st.executeQuery();
			while(rs.next()) { //?떎?뻾?븳 寃곌낵媛? ?엲?쓣 ?븣 源뚯? add 瑜? ?븯湲?
				DeptDTO dept = new DeptDTO(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4));
				deptlist.add(dept);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbDisconnect(conn, st, rs);
		}
		log.info("JDBC 사용 >> " + deptlist.size());
		return deptlist;
	}



	// 2. ?긽?꽭蹂닿린
	public DeptDTO selectById(int deptid) {
		
		DeptDTO dept = null;
		try {
			conn = ds.getConnection(); //DB?뿰寃?
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
	
	
	// 3. ?엯?젰
	public int insert(DeptDTO dept) { //?씤?꽌?듃 ?븯怨? ?떢??嫄? 蹂??닔媛? 留롮쑝?땲源? ?븳 ?뜦?뼱由щ줈 媛??졇?삱?씪怨? DTO
		
		PreparedStatement st = null;
		
		try {
			conn = ds.getConnection(); //DB?뿰寃?
			st = conn.prepareStatement(sql_insert); //?엯?젰臾몄옣以?鍮? ?4媛쒕땲源? 4媛?
			st.setInt(1, dept.getDepartment_id());  //dept ?뿉?꽌 ?씫?뼱?삤湲?
			st.setString(2, dept.getDepartment_name());
			st.setInt(3, dept.getManager_id());
			st.setInt(4, dept.getLocation_id());
			
			result = st.executeUpdate(); //?씤?꽌?듃 ?떎?뻾!
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbDisconnect(conn, st, rs);
		}
		
		return result;
		//?엯?젰?맂 1 '嫄?'?씠 ?꽦怨듭씠?땲源? int
	}

	// 4. ?닔?젙
	public int update(DeptDTO dept) {
		
		PreparedStatement st = null;
		
		try {
			conn = ds.getConnection(); //DB?뿰寃?
			st = conn.prepareStatement(sql_update); //??닚?꽌 二쇱쓽
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

	// 5. ?궘?젣
	public int delete(int dept_id) { 
		
		
		try {
			conn = ds.getConnection(); //DB?뿰寃?
			conn.setAutoCommit(false); //?옄?룞而ㅻ컠 ?븞?븿 
			st = conn.prepareStatement(sql_delete); //遺??꽌?븘?씠?뵒 ?궘?젣
			st.setInt(1, dept_id); // 遺??꽌?븘?씠?뵒 1踰? ?궘?젣
			result = st.executeUpdate();
			conn.commit(); //  ?뿉 諛섏쁺?븳?떎. 留뚯빟 ?쐞?뿉?꽌 而ㅻ컠 ?떎?뙣?뻽?쑝硫? catch ?뿉?꽌 濡ㅻ갚
		} catch (SQLException e) {
			try {
				conn.rollback(); //DB?뿉 ?옉?뾽?븳 ?궡?슜 痍⑥냼
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
