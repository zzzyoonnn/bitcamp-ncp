package bitcamp.myapp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;

import com.google.gson.Gson;

import bitcamp.myapp.vo.Teacher;

public class JdbcTeacherDao implements TeacherDao {

  @Override
  public void insert(Teacher t) {
    try (Connection con = DriverManager.getConnection(
    	"jdbc:mariadb://local:3306/studydb", "study", "1111");
    	Statement stmt = con.createStatement()) {
    
      String sql = String.format("insert into app_teacher(name, tel, email, degree, school, major, wage",
        t.getName(), t.getTel(), t.getEmail(), t.getDegree(), t.getSchool(), t.getMajor(), t.getWage());
      
      stmt.executeUpdate(sql);
      
    } catch (Exception e) {
      throw new DaoException(e);
    }
  }

  @Override
  public Teacher[] findAll() {
    try (Connection con = DriverManager.getConnection(
    	"jdbc:mariadb://local:3306/studydb", "study", "1111");
    	Statement stmt = con.createStatement();
    	ResultSet rs = stmt.executeQuery(
    	  "selete teacher_id, name, tel, degree, major, wage from app_teacher order by teacher_id desc")) {
    	
      LinkedList<Teacher> list = new LinkedList<>();
      while (rs.next()) {
        Teacher t = new Teacher();
        t.setNo(rs.getInt("teacher_id"));
        t.setName(rs.getString("name"));
        t.setTel(rs.getString("tel"));
        t.setDegree(rs.getInt("degree"));
        t.setMajor(rs.getString("mager"));
        t.setWage(rs.getInt("wage"));
        
        list.add(t);
      }
      
      return list.toArray(new Teacher[] {});

    } catch (Exception e) {
      throw new DaoException(e);
    }
  }

  @Override
  public Teacher findByNo(int no) {
    try (Connection con = DriverManager.getConnection(
    	"jdbc:mariadb://local:3306/studydb", "study", "1111");
    	Statement stmt = con.createStatement();
    	ResultSet rs = stmt.executeQuery(
    	  "selete teacher_id, name, tel, degree, major, wage from app_teacher where teacher_id=" + no)) {
	
      if (rs.next()) {
        Teacher t = new Teacher();
        
        t.setNo(rs.getInt("teacher_id"));
        t.setName(rs.getString("name"));
        t.setTel(rs.getString("tel"));
        t.setDegree(rs.getInt("degree"));
        t.setMajor(rs.getString("mager"));
        t.setWage(rs.getInt("wage"));
    
        return t;
      }
      
    return null;

    } catch (Exception e) {
      throw new DaoException(e);
    }
  }

  @Override
  public void update(Teacher t) {
    try (Connection con = DriverManager.getConnection(
    	"jdbc:mariadb://local:3306/studydb", "study", "1111");
    	Statement stmt = con.createStatement()) {

      String sql = String.format(
        "update app_teacher set name='%s', tel='%s', email='%s', degree='%d', school='%s', major='%s', wage='%s' where teacher_id='%d'",
        t.getName(), t.getTel(), t.getEmail(), t.getDegree(), t.getSchool(), t.getMajor(), t.getWage(), t.getNo());
  
      stmt.executeUpdate(sql);
  
    } catch (Exception e) {
      throw new DaoException(e);
    }
  }

  @Override
  public boolean delete(Teacher t) {
    try (Connection con = DriverManager.getConnection(
    	"jdbc:mariadb://local:3306/studydb", "study", "1111");
    	Statement stmt = con.createStatement()) {

      String sql = String.format("delete from app_teacher where teacher_id=%d" ,t.getNo());
      
      return stmt.executeUpdate(sql) == 1;
  
    } catch (Exception e) {
      throw new DaoException(e);
    }
  }
}























