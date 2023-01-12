package bitcamp.bootapp.controller;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import bitcamp.bootapp.dao.StudentDao;
import bitcamp.bootapp.vo.Student;

@RestController
public class StudentController {

  StudentDao studentDao;
  
  // Spring IoC 컨테이너가 이 클래스의 인스턴스를 만들기 위해
  // 생성자를 호출할 때,
  // 파라미터로 선언된 StudentDao의 객체를 주입할 것이다.
  public StudentController(StudentDao studentDao) {
	  this.studentDao = studentDao;
  }

  @PostMapping("/students")
  public Object addStudent(Student student) {

    this.studentDao.insert(student);

    Map<String,Object> contentMap = new HashMap<>();
    contentMap.put("status", "success");

    return contentMap;
  }


  @GetMapping("/students")
  public Object getStudents() {

    Student[] students = this.studentDao.findAll();

    Map<String,Object> contentMap = new HashMap<>();
    contentMap.put("status", "success");
    contentMap.put("data", students);

    return contentMap;
  }


  @GetMapping("/students/{no}")
  public Object getStudent(@PathVariable int no) {

    Student b = this.studentDao.findByNo(no);

    Map<String,Object> contentMap = new HashMap<>();

    if (b == null) {
      contentMap.put("status", "failure");
      contentMap.put("data", "해당 번호의 회원이 없습니다.");
    } else {
      contentMap.put("status", "success");
      contentMap.put("data", b);
    }

    return contentMap;
  }

  @PutMapping("/students/{no}")
  public Object updateStudent(
      //@PathVariable int studentNo, // Student 인스턴스로 직접 받을 수 있다.
      Student student) {

    Map<String,Object> contentMap = new HashMap<>();

    Student old = this.studentDao.findByNo(student.getNo());
    if (old == null) {
      contentMap.put("status", "failure");
      contentMap.put("data", "회원이 없습니다.");
      return contentMap;
    }

    student.setCreatedDate(old.getCreatedDate());

    this.studentDao.update(student);

    contentMap.put("status", "success");

    return contentMap;
  }

  @DeleteMapping("/students/{no}")
  public Object deleteStudent(
      @PathVariable int no) {

    Student s = this.studentDao.findByNo(no);

    // 응답 결과를 담을 맵 객체 준비
    Map<String,Object> contentMap = new HashMap<>();

    if (s == null) {
      contentMap.put("status", "failure");
      contentMap.put("data", "회원이 없습니다.");

    } else {
      this.studentDao.delete(s);
      contentMap.put("status", "success");
    }

    return contentMap;
  }
}
