package com.shinhan.myapp.emp;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.shinhan.myapp.vo.DeptDTO;

import lombok.extern.slf4j.Slf4j;
import net.starykids.util.DBUtil;
import net.starykids.util.DateUtil;

@Slf4j
@Repository
public class EmpDAOJDBC implements EmpDAOInterface{
    
    @Autowired
    @Qualifier("dataSource")
    DataSource ds;
    Connection conn;
    
    public Map<String, Object> selectJoin2(String jobid) {
        String sql = 
                " select employee_id, first_name, salary, department_name, city, country_name"+
                " from employees join departments using(department_id)"+
                "                      join locations using(location_id)"+
                "                      join countries USING (country_id)"+
                " where  job_id = ? ";
        Map<String, Object> map = new HashMap<>();
        List<EmpDTO> emplist = new ArrayList<>();
        List<DeptDTO> deptlist = new ArrayList<>();
        PreparedStatement st = null;
        ResultSet rs = null;
        
        try {
            conn = ds.getConnection();
            st = conn.prepareStatement(sql);
            st.setString(1, jobid);
            rs = st.executeQuery();
            while(rs.next()) {
                EmpDTO emp = new EmpDTO();
                emp.setEmployee_id(rs.getInt("Employee_id"));
                emp.setFirst_name(rs.getString("First_name"));
                emp.setSalary(rs.getDouble("salary"));
                emplist.add(emp);
                
                DeptDTO dept = new DeptDTO();
                dept.setDepartment_name(rs.getString("Department_name"));
                deptlist.add(dept);
            }
            map.put("emp", emplist);
            map.put("dept", deptlist);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            DBUtil.dbDisconnect(conn, st, rs);
        }
        return map;
        
    }
    
    //Jobs?뀒?씠釉붿쓽 紐⑤뱺 data媛??졇?삤湲? 
    public List<JobDTO> selectAllJob() {
        //1.DTO留뚮뱺?떎 2.MAP?궗?슜?븳?떎. 
        String sql = "select * from jobs";
        List<JobDTO> joblist = new ArrayList<>();
        
        PreparedStatement st = null;
        ResultSet rs = null;
        
        try {
            conn = ds.getConnection();
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();
            while(rs.next()) {
                JobDTO emp = JobDTO.builder()
                        .job_id(rs.getString("job_id"))
                        .job_title(rs.getString("job_title"))
                        .min_salary(rs.getInt("min_salary"))
                        .max_salary(rs.getInt("max_salary"))
                        .build();
                joblist.add(emp);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            DBUtil.dbDisconnect(conn, st, rs);
        }
        return joblist;
        
    }
    
    public List<EmpJoinDTO> selectJoin(String jobid) {
        //1.DTO留뚮뱺?떎 2.MAP?궗?슜?븳?떎. 
        String sql = 
                " select employee_id, first_name, salary, department_name, city, country_name"+
                " from employees join departments using(department_id)"+
                "                       join locations using(location_id)"+
                "                       join countries USING (country_id)"+
                " where  job_id = ? "
                ;
        List<EmpJoinDTO> emplist = new ArrayList<EmpJoinDTO>();
        PreparedStatement st = null;
        ResultSet rs = null;
        
        try {
            conn = ds.getConnection();
            st = conn.prepareStatement(sql);
            st.setString(1, jobid);
            rs = st.executeQuery();
            while(rs.next()) {
                EmpJoinDTO emp = EmpJoinDTO.builder()
                        .city(rs.getString("city"))
                        .employee_id(rs.getInt("employee_id"))
                        .first_name(rs.getString("first_name"))
                        .country_name(rs.getString("country_name"))
                        .department_name(rs.getString("department_name"))
                        .build();
                emplist.add(emp);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            DBUtil.dbDisconnect(conn, st, rs);
        }
        return emplist;
        
    }
    
    
    
    
    //1.?듅?젙遺??꽌?쓽 吏곸썝議고쉶      where department_id = ? 
    public List<EmpDTO> selectByDept(int dept_id) {
        // 紐⑤뱺 吏곸썝?쓣 議고쉶?븯湲?
        String sql = "select * from employees where department_id = ?" ;
        PreparedStatement st = null;
        ResultSet rs = null;
        List<EmpDTO> emplist = new ArrayList<EmpDTO>();
        try {
            conn = ds.getConnection();
            st = conn.prepareStatement(sql); //SQL臾? 以?鍮? 
            st.setInt(1, dept_id);  //??뿉 媛믪쓣 梨꾩슦湲? 
            rs = st.executeQuery(); //DB?뿉 媛??꽌 ?떎?뻾?븯怨? 寃곌낵瑜? 媛??졇?삩?떎. 
            while (rs.next()) {
                EmpDTO emp = makeEmp2(rs);
                emplist.add(emp);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            DBUtil.dbDisconnect(conn, st, rs);
        }
        return emplist;
    }
    
    //2.?듅?젙job_id?씤 吏곸썝議고쉶   where job_id = ? 
    public List<EmpDTO> selectByJob(String job_id) {
        // 紐⑤뱺 吏곸썝?쓣 議고쉶?븯湲?
        String sql = "select * from employees where job_id = ?" ;
        PreparedStatement st = null;
        ResultSet rs = null;
        List<EmpDTO> emplist = new ArrayList<EmpDTO>();
        try {
            conn = ds.getConnection();
            st = conn.prepareStatement(sql); //SQL臾? 以?鍮? 
            st.setString(1, job_id);  //??뿉 媛믪쓣 梨꾩슦湲? 
            rs = st.executeQuery(); //DB?뿉 媛??꽌 ?떎?뻾?븯怨? 寃곌낵瑜? 媛??졇?삩?떎. 
            while (rs.next()) { //?떎?쓬data媛? ?엳?뒗吏??
                EmpDTO emp = makeEmp2(rs); //?븳嫄댁쓣 DTO留뚮뱺?떎. 
                emplist.add(emp);          //?뿬?윭嫄댁씠誘?濡? Collection?뿉 ?떞湲?  
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            DBUtil.dbDisconnect(conn, st, rs);
        }
        return emplist;
    }
    
    //3.湲됱뿬媛? ??씠?긽?씤 吏곸썝議고쉶   where salary >= ? 
    public List<EmpDTO> selectBySalary(double salary) {
        // 紐⑤뱺 吏곸썝?쓣 議고쉶?븯湲?
        String sql = "select * from employees where salary >= ?" ;
        PreparedStatement st = null;
        ResultSet rs = null;
        List<EmpDTO> emplist = new ArrayList<EmpDTO>();
        try {
            conn = ds.getConnection();
            st = conn.prepareStatement(sql); //SQL臾? 以?鍮? 
            st.setDouble(1, salary);  //??뿉 媛믪쓣 梨꾩슦湲? 
            rs = st.executeQuery(); //DB?뿉 媛??꽌 ?떎?뻾?븯怨? 寃곌낵瑜? 媛??졇?삩?떎. 
            while (rs.next()) { //?떎?쓬data媛? ?엳?뒗吏??
                EmpDTO emp = makeEmp2(rs); //?븳嫄댁쓣 DTO留뚮뱺?떎. 
                emplist.add(emp);          //?뿬?윭嫄댁씠誘?濡? Collection?뿉 ?떞湲?  
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            DBUtil.dbDisconnect(conn, st, rs);
        }
        return emplist;
    }
    
    //4.遺??꽌, 吏곸콉, 湲됱뿬, ?엯?궗?씪 議곌굔?쑝濡? 議고쉶 
    public List<EmpDTO> selectByCondition(Map<String,Object> map) {
        
        String sql = " select * from employees where (-1 =? or department_id = ?) and ('-1' =? or job_id = ?) and salary >= ? and  hire_date >= ? ";
        PreparedStatement st = null;
        ResultSet rs = null;
        List<EmpDTO> emplist = new ArrayList<EmpDTO>();
        try {
            conn = ds.getConnection();
            
            String str_deptid =(String) map.get("deptid");
            int deptid = Integer.parseInt(str_deptid);
            String str_sal =(String)map.get("salary");
            String str_hdate =(String) map.get("hdate");
            Date hdate = DateUtil.convertSqlDate(DateUtil.convertDate(str_hdate));
            
            st = conn.prepareStatement(sql); //SQL臾? 以?鍮? 
            st.setInt(1, deptid);  //??뿉 媛믪쓣 梨꾩슦湲? 
            st.setInt(2, deptid);  //??뿉 媛믪쓣 梨꾩슦湲? 
            st.setString(3, (String)map.get("jobid"));  //??뿉 媛믪쓣 梨꾩슦湲? 
            st.setString(4, (String)map.get("jobid"));  //??뿉 媛믪쓣 梨꾩슦湲? 
            st.setDouble(5, Double.parseDouble(str_sal));
            st.setDate(6, hdate);  //??뿉 媛믪쓣 梨꾩슦湲? 
            
            rs = st.executeQuery(); //DB?뿉 媛??꽌 ?떎?뻾?븯怨? 寃곌낵瑜? 媛??졇?삩?떎. 
            while (rs.next()) { //?떎?쓬data媛? ?엳?뒗吏??
                EmpDTO emp = makeEmp2(rs); //?븳嫄댁쓣 DTO留뚮뱺?떎. 
                emplist.add(emp);          //?뿬?윭嫄댁씠誘?濡? Collection?뿉 ?떞湲?  
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            DBUtil.dbDisconnect(conn, st, rs);
        }
        return emplist;
    }   
    
    public List<EmpDTO> selectAll() {
        // 紐⑤뱺 吏곸썝?쓣 議고쉶?븯湲?
        String sql = "select * from employees order by 1";
        Statement st = null;
        ResultSet rs = null;
        List<EmpDTO> emplist = new ArrayList<EmpDTO>();
        try {
            conn = ds.getConnection();
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                EmpDTO emp = makeEmp(rs);
                emplist.add(emp);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            DBUtil.dbDisconnect(conn, st, rs);
        }
        return emplist;
    }
    public EmpDTO selectById(int empid) {
        // ?듅?젙 吏곸썝?쓣 議고쉶?븯湲?
        String sql = "select  *  from employees where employee_id = " + empid;
        Statement st = null;
        ResultSet rs = null;
        EmpDTO emp = null;
        try {
            conn = ds.getConnection();
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            if (rs.next()) {
                emp = makeEmp(rs);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            DBUtil.dbDisconnect(conn, st, rs);
        }
        return emp;
    }
    // DB?뿉 ?엯?젰
    public int insert(EmpDTO emp) {
        int result = 0;
        String sql = "insert into employees values (?,?,?,?,?,?,?,?,?,?,?)";
        // Statement?뒗 ?(binding蹂??닔 吏??썝?븞?븿) <------PreparedStatement?뒗 吏??썝
        PreparedStatement st = null;
        try {
            conn = ds.getConnection();
            st = conn.prepareStatement(sql);
            st.setInt(1, emp.getEmployee_id());
            st.setString(2, emp.getFirst_name());
            st.setString(3, emp.getLast_name());
            st.setString(4, emp.getEmail());
            st.setString(5, emp.getPhone_number());
            st.setDate(6, emp.getHire_date());
            st.setString(7, emp.getJob_id());
            st.setDouble(8, emp.getSalary());
            st.setDouble(9, emp.getCommission_pct());
            st.setObject(10, emp.getManager_id()==-1?null:emp.getManager_id());
            st.setObject(11, emp.getDepartment_id()==-1?null:emp.getDepartment_id());
            result = st.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            DBUtil.dbDisconnect(conn, st, null);
        }
        return result;
    }
    // ?닔?젙
    public int update(EmpDTO emp) {
        int result = 0;
        String sql = 
                " update employees set FIRST_NAME=?, LAST_NAME=?, EMAIL=?, PHONE_NUMBER=?, HIRE_DATE=?, JOB_ID=?, SALARY=?, COMMISSION_PCT=?, MANAGER_ID=?, DEPARTMENT_ID=? where EMPLOYEE_ID=? ";
        // Statement?뒗 ?(binding蹂??닔 吏??썝?븞?븿) <------PreparedStatement?뒗 吏??썝
        PreparedStatement st = null;
        try {
            conn = ds.getConnection();
            st = conn.prepareStatement(sql);
            st.setInt(11, emp.getEmployee_id());
            st.setString(1, emp.getFirst_name());
            st.setString(2, emp.getLast_name());
            st.setString(3, emp.getEmail());
            st.setString(4, emp.getPhone_number());
            st.setDate(5, emp.getHire_date());
            st.setString(6, emp.getJob_id());
            st.setDouble(7, emp.getSalary());
            st.setDouble(8, emp.getCommission_pct());
            st.setInt(9, emp.getManager_id()==-1?null:emp.getManager_id());
            st.setInt(10, emp.getDepartment_id()==-1?null:emp.getDepartment_id());
            result = st.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            DBUtil.dbDisconnect(conn, st, null);
        }
        return result;
    }
    // ?궘?젣
    public int delete(int empid) {
        int result = 0;
        String sql = "delete from employees"+  
                " where EMPLOYEE_ID=?";
        PreparedStatement st = null;
        try {
            conn = ds.getConnection();
            st = conn.prepareStatement(sql);
            st.setInt(1, empid);
            result = st.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            DBUtil.dbDisconnect(conn, st, null);
        }
        return result;
    }
    private static EmpDTO makeEmp2(ResultSet rs) throws SQLException {
        EmpDTO emp = EmpDTO.builder()
                .commission_pct(rs.getDouble("Commission_pct"))
                .department_id(rs.getInt("Department_id"))
                .email(rs.getString("email"))
                .employee_id(rs.getInt("Employee_id"))
                .first_name(rs.getString("first_name"))
                .last_name(rs.getString("Last_name"))
                .hire_date(rs.getDate("Hire_date"))
                .job_id(rs.getString("job_id"))
                .manager_id(rs.getInt("Manager_id"))
                .phone_number(rs.getString("Phone_number"))
                .salary(rs.getDouble("salary"))
                .build();
        return emp;
    }
    
    private static EmpDTO makeEmp(ResultSet rs) throws SQLException {
        EmpDTO emp = new EmpDTO();
        emp.setCommission_pct(rs.getDouble("Commission_pct"));
        emp.setDepartment_id(rs.getInt("Department_id"));
        emp.setEmail(rs.getString("email"));
        emp.setEmployee_id(rs.getInt("Employee_id"));
        emp.setFirst_name(rs.getString("First_name"));
        emp.setLast_name(rs.getString("Last_name"));
        emp.setHire_date(rs.getDate("Hire_date"));
        emp.setJob_id(rs.getString("job_id"));
        emp.setManager_id(rs.getInt("Manager_id"));
        emp.setPhone_number(rs.getString("Phone_number"));
        emp.setSalary(rs.getDouble("salary"));
         
        return emp;
    }
}
