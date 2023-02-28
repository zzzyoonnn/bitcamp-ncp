package bitcamp.myapp.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bitcamp.myapp.dao.MemberDao;
import bitcamp.myapp.dao.StudentDao;
import bitcamp.myapp.service.StudentService;
import bitcamp.myapp.vo.Student;
import bitcamp.util.TransactionManager;

@Service
public class DefaultStudentService implements StudentService {

  @Autowired private TransactionManager txManager;
  @Autowired private MemberDao memberDao;
  @Autowired private StudentDao studentDao;


  public void add(Student student) {
    txManager.startTransaction();
    try {
      memberDao.insert(student);
      studentDao.insert(student);
      txManager.commit();

    } catch (Exception e) {
      txManager.rollback();
      throw e;
    }
  }

  public List<Student> list(String keyword) {
    return studentDao.findAll(keyword);
  }

  public Student get(int no) {
    return studentDao.findByNo(no);
  }

  public Student get(String email, String password) {
    Map<String,Object> paramMap = new HashMap<>();
    paramMap.put("email", email);
    paramMap.put("password", password);

    return studentDao.findByEmailAndPassword(paramMap);
  }

  public void update(Student student) {
    try {
      txManager.startTransaction();
      if (memberDao.update(student) == 1 &&
          studentDao.update(student) == 1) {
        txManager.commit();
      } else {
        throw new RuntimeException("회원이 존재하지 않습니다.");
      }
    } catch (Exception e) {
      txManager.rollback();
      throw e;
    }
  }

  public void delete(int no) {
    try {
      txManager.startTransaction();
      if (studentDao.delete(no) == 1 &&
          memberDao.delete(no) == 1) {
        txManager.commit();
      } else {
        throw new RuntimeException("회원이 존재하지 않습니다.");
      }
    } catch (Exception e) {
      txManager.rollback();
      throw e;
    }
  }
}





