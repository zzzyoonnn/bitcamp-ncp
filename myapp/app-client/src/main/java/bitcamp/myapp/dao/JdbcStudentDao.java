package bitcamp.myapp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;

import com.google.gson.Gson;

import bitcamp.myapp.vo.Student;

public class JdbcStudentDao implements StudentDao {

  @Override
  public void insert(Student s) {
    try (Connection con = DriverManager.getConnection(
        "jdbc:mariadb://localhost:3306/studydb", "study", "1111");
        Statement stmt = con.createStatement()) {
    	
      String sql = String.format(
        "insert into app_student(name, tel, pst_no, bas_addr, det_addr, work, gender, level) values('%s', '%s', '%s', '%s', '%s', '%d', '%d', '%d')",
        s.getName(), s.getTel(), s.getPostNo(), s.getBasicAddress(), s.getDetailAddress(), s.isWorking(), s.getGender(), s.getLevel());

      stmt.executeUpdate(sql);
    
    } catch (Exception e) {
      throw new DaoException(e);
    }
  }

  @Override
  public Student[] findAll() {
    try (Connection con = DriverManager.getConnection(
        "jdbc:mariadb://localhost:3306/studydb", "study", "1111");
        Statement stmt = con.createStatement();
    	ResultSet rs = stmt.executeQuery(
    	  "selete student_id, name, tel, pst_no, bas_addr, det_addr, work, gender, level from app_student order by student_id desc")) {
       	
      LinkedList<Student> list = new LinkedList<>();
      while (rs.next()) {
        Student s = new Student();
        s.setNo(rs.getInt("student_id"));
        s.setName(rs.getString("name"));
        s.setTel(rs.getString("tel"));
        s.setPostNo(rs.getString("pst_no"));
        s.setBasicAddress(rs.getString("bas_addr"));
        s.setDetailAddress(rs.getString("det_addr"));
        s.setWorking(rs.getInt("0. 미취업\n1. 재직중\n재직자? ") == 1);
        s.setGender(rs.getInt("0. 남자\n1. 여자\n성별? ") == 0 ? 'M' : 'W');
        s.setLevel((byte)rs.getInt("0. 비전공자\n1. 준전공자\n2. 전공자\n전공? "));
        
        list.add(s);
      }
      
      return list.toArray(new Student[] {});
       
    } catch (Exception e) {
      throw new DaoException(e);
    }
  }

  @Override
  public Student findByNo(int no) {
    try (Connection con = DriverManager.getConnection(
        "jdbc:mariadb://localhost:3306/studydb", "study", "1111");
        Statement stmt = con.createStatement();
    	ResultSet rs = stmt.executeQuery(
    	  "select student_id, name, tel, pst_no, bas_addr, det_addr, work, gender, level from app_student where student_id =" + no)) {
       	
      if (rs.next()) {
        Student s = new Student();
        
        s.setNo(rs.getInt("student_id"));
        s.setName(rs.getString("name"));
        s.setTel(rs.getString("tel"));
        s.setPostNo(rs.getString("pst_no"));
        s.setBasicAddress(rs.getString("bas_addr"));
        s.setDetailAddress(rs.getString("det_addr"));
        s.setWorking(rs.getInt("0. 미취업\n1. 재직중\n재직자? ") == 1);
        s.setGender(rs.getInt("0. 남자\n1. 여자\n성별? ") == 0 ? 'M' : 'W');
        s.setLevel((byte)rs.getInt("0. 비전공자\n1. 준전공자\n2. 전공자\n전공? "));
          
        return s;
      }
      return null;
    } catch (Exception e) {
      throw new DaoException(e);
    }
  }

  @Override
  public void update(Student s) {
    try (Connection con = DriverManager.getConnection(
		"jdbc:mariadb://localhost:3306/studydb", "study", "1111");
		Statement stmt = con.createStatement()) {
	
	  String sql = String.format(
	    "update app_student set name='%s', tel='%s', pst_no='%s', bas_addr='%s', det_addr='%s', work='%d', gender='%d', level='%d'",
	    s.getName(), s.getTel(), s.getPostNo(), s.getBasicAddress(), s.getDetailAddress(), s.isWorking(), s.getGender(), s.getLevel());
    
      stmt.executeUpdate(sql);

    } catch (Exception e) {
	  throw new DaoException(e);
    }
  }

  @Override
  public boolean delete(Student s) {
    try (Connection con = DriverManager.getConnection(
        "jdbc:mariadb://localhost:3306/studydb", "study", "1111");
		Statement stmt = con.createStatement()) {
		
	  String sql = String.format(
	    "delete from app_student where student_id=%d", s.getNo());
	    
	  return stmt.executeUpdate(sql) == 1;

	} catch (Exception e) {
	  throw new DaoException(e);
    }
  }
}























