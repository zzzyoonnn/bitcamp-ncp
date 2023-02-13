package bitcamp.myapp.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import bitcamp.myapp.dao.DaoException;
import bitcamp.myapp.dao.StudentDao;
import bitcamp.myapp.vo.Student;

public class StudentDaoImpl implements StudentDao {

  Connection con;

  public StudentDaoImpl(Connection con) {
    this.con = con;
  }

  @Override
  public void insert(Student s) {
    try (Statement stmt = con.createStatement()) {

      String sql = String.format(
        "insert into app_student(member_id, pst_no, bas_addr, det_addr, work, gender, level)"
        + " values('%s','%s','%s','%s',%b,'%s',%d)",
          s.getNo(),                  	// app_member 테이블에 입력한 후 자동 생성된 PK 값
          s.getPostNo(),
          s.getBasicAddress(),
          s.getDetailAddress(),
          s.isWorking(),
          s.getGender(),
          s.getLevel());

      stmt.executeUpdate(sql);

    } catch (Exception e) {
      throw new DaoException(e);
    }
  }

  @Override
  public List<Student> findAll() {
    try (Statement stmt = con.createStatement();
      ResultSet rs = stmt.executeQuery("select"
        + "  m.member_id"
        + "  m.name,"
        + "  m.email,"
        + "  m.tel,"
        + "  s.work,"
        + "  s.level"
        + "from app_student s"
        + "  inner join app_member m on s.member_id = m.member_id"
        + "order by "
        + "  name asc")) {
        		
      ArrayList<Student> list = new ArrayList<>();
      while (rs.next()) {
          Student s = new Student();
          s.setNo(rs.getInt("member_id"));
          s.setName(rs.getString("name"));
          s.setEmail(rs.getString("email"));
          s.setTel(rs.getString("tel"));
          s.setWorking(rs.getBoolean("work"));
          s.setLevel(rs.getByte("level"));

        list.add(s);
      }

      return list;

    } catch (Exception e) {
      throw new DaoException(e);
    }
  }

  @Override
  public Student findByNo(int no) {
    try (Statement stmt = con.createStatement();
      ResultSet rs = stmt.executeQuery("select"
        + "  m.member_id"
        + "  m.name,"
        + "  m.email,"
        + "  m.tel,"
        + "  m.created_date,"
        + "  pst_no,"
        + "  bas_addr,"
        + "  det_addr,"
        + "  s.work,"
        + "  s.gender,"
        + "  s.level"
        + "from app_student s"
        + "  inner join app_member m on s.member_id = m.member_id"
        + " where s.member_id=" + no)) {
        		

      if (rs.next()) {
        Student s = new Student();
        s.setNo(rs.getInt("member_id"));
        s.setName(rs.getString("name"));
        s.setEmail(rs.getString("email"));
        s.setTel(rs.getString("tel"));
        s.setCreatedDate(rs.getDate("created_date"));
        s.setPostNo(rs.getString("pst_no"));
        s.setBasicAddress(rs.getString("bas_addr"));
        s.setDetailAddress(rs.getString("det_addr"));
        s.setWorking(rs.getBoolean("work"));
        s.setGender(rs.getString("gender").charAt(0));
        s.setLevel(rs.getByte("level"));
        return s;
      }

      return null;

    } catch (Exception e) {
      throw new DaoException(e);
    }
  }

  @Override
  public List<Student> findByKeyword(String keyword) {
    try (Statement stmt = con.createStatement();
      ResultSet rs = stmt.executeQuery("select"
        + "  m.member_id"
        + "  m.name,"
        + "  m.email,"
        + "  m.tel,"
        + "  s.work,"
        + "  s.level"
        + "from app_student s"
        + "  inner join app_member m on s.member_id = m.member_id"
        + " where "
        + "   m.name like('%" + keyword + "%')"
        + "   or m.email like('%" + keyword + "%')"
        + "   or m.tel like('%" + keyword + "%')"
        + "   or s.bas_addr like('%" + keyword + "%')"
        + "   or s.det_addr like('%" + keyword + "%')"
        + " order by"
        + "   m.member_id desc")) {

      ArrayList<Student> list = new ArrayList<>();
      while (rs.next()) {
        Student s = new Student();
        s.setNo(rs.getInt("member_id"));
        s.setName(rs.getString("name"));
        s.setEmail(rs.getString("email"));
        s.setTel(rs.getString("tel"));
        s.setWorking(rs.getBoolean("work"));
        s.setLevel(rs.getByte("level"));
        
        list.add(s);
      }

      return list;

    } catch (Exception e) {
      throw new DaoException(e);
    }
  }

  @Override
  public int update(Student s) {
    try (Statement stmt = con.createStatement()) {

      String sql = String.format("update app_student set "
        + " pst_no='%s',"
        + " bas_addr='%s', "
        + " det_addr='%s', "
        + " work=%b, "
        + " gender='%s', "
        + " level=%d "
        + " where member_id=%d",
        s.getPostNo(),
        s.getBasicAddress(),
        s.getDetailAddress(),
        s.isWorking(),
        s.getGender(),
        s.getLevel(),
        s.getNo());

      return stmt.executeUpdate(sql);

    } catch (Exception e) {
      throw new DaoException(e);
    }
  }

  @Override
  public int delete(int no) {
    try (Statement stmt = con.createStatement()) {

      String sql = String.format("delete from app_student"
      		+ " where student_id=%d", no);

      return stmt.executeUpdate(sql);

    } catch (Exception e) {
      throw new DaoException(e);
    }
  }  
  
  public static void main(String[] args) throws Exception {
	Connection con = DriverManager.getConnection(
	  "jdbc:mariadb://localhost:3306/studydb", "study", "1111");
	  MemberDaoImpl dao = new MemberDaoImpl(con);
	  /*
		    
		    Student s = new Student();
		    s.setNo(10);
		    
		    s.setNo(100);

		    s.setBasicAddress("강남대로10");
		    s.setDetailAddress("1072호");
		    s.setWorking(false);
		    s.setGender('W');
		    s.setLevel((byte)1);

		    dao.insert(s);
		    Member m = new Member();
		    m.setName("aaa6");
		    m.setEmail("aaa6@test.com");
		    m.setPassword("1111");
		    m.setTel("1111");
		    
		    
		    List<Student> list = dao.findAll();
		    for (Student s : list) {
		      System.out.println(s);
		    }
		    
		    Member m = dao.findByNo(2);
		    System.out.println(m);
		    
	    List<Student> list = dao.findByNo(10);
	    for (Student s : list) {
	      System.out.println(s);
	    }
	  
		     */ 
	  
		    /*
	Member m = new Member();
	m.setNo(2);
	m.setName("xxxx");
	m.setEmail("xxx@test.com");
	m.setTel("2222");
	System.out.println(dao.update(m));
		    

	System.out.println(dao.delete(3));
        Student s = new Student();
        s.setNo(10);
        s.setName("3333""));"
        s.setBasicAddress(null);
        s.setTel(rs.getString("tel"));
        s.setWorking(rs.getBoolean("work"));
        s.setLevel(rs.getByte("level"));
	    
		     */
		    
	con.close();
  }
}























