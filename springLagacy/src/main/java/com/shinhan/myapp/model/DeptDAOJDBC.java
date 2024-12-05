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
	// Ÿ���� ������ �ڵ����� injection �ȴ�. (IOC, DI)
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
	
	
	// 1. 모든 �??�� 조회?���?
	public List<DeptDTO> selectAll() {
		
		List<DeptDTO> deptlist = new ArrayList<DeptDTO>();
		try {
			conn = ds.getConnection(); //DB?���?
			st = conn.prepareStatement(sql_selectAll);
			rs = st.executeQuery();
			while(rs.next()) { //?��?��?�� 결과�? ?��?�� ?�� 까�? add �? ?���?
				DeptDTO dept = new DeptDTO(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4));
				deptlist.add(dept);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbDisconnect(conn, st, rs);
		}
		log.info("JDBC ��� >> " + deptlist.size());
		return deptlist;
	}



	// 2. ?��?��보기
	public DeptDTO selectById(int deptid) {
		
		DeptDTO dept = null;
		try {
			conn = ds.getConnection(); //DB?���?
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
	
	
	// 3. ?��?��
	public int insert(DeptDTO dept) { //?��?��?�� ?���? ?��??�? �??���? 많으?���? ?�� ?��?��리로 �??��?��?���? DTO
		
		PreparedStatement st = null;
		
		try {
			conn = ds.getConnection(); //DB?���?
			st = conn.prepareStatement(sql_insert); //?��?��문장�?�? ?4개니�? 4�?
			st.setInt(1, dept.getDepartment_id());  //dept ?��?�� ?��?��?���?
			st.setString(2, dept.getDepartment_name());
			st.setInt(3, dept.getManager_id());
			st.setInt(4, dept.getLocation_id());
			
			result = st.executeUpdate(); //?��?��?�� ?��?��!
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbDisconnect(conn, st, rs);
		}
		
		return result;
		//?��?��?�� 1 '�?'?�� ?��공이?���? int
	}

	// 4. ?��?��
	public int update(DeptDTO dept) {
		
		PreparedStatement st = null;
		
		try {
			conn = ds.getConnection(); //DB?���?
			st = conn.prepareStatement(sql_update); //??��?�� 주의
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

	// 5. ?��?��
	public int delete(int dept_id) { 
		
		
		try {
			conn = ds.getConnection(); //DB?���?
			conn.setAutoCommit(false); //?��?��커밋 ?��?�� 
			st = conn.prepareStatement(sql_delete); //�??��?��?��?�� ?��?��
			st.setInt(1, dept_id); // �??��?��?��?�� 1�? ?��?��
			result = st.executeUpdate();
			conn.commit(); //  ?�� 반영?��?��. 만약 ?��?��?�� 커밋 ?��?��?��?���? catch ?��?�� 롤백
		} catch (SQLException e) {
			try {
				conn.rollback(); //DB?�� ?��?��?�� ?��?�� 취소
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
