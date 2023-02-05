package bitcamp.server;

import java.util.ArrayList;
import java.util.LinkedList;

import bitcamp.client.dao.BoardDao;
import bitcamp.client.dao.StudentDao;
import bitcamp.client.dao.TeacherDao;
import bitcamp.client.vo.Board;
import bitcamp.client.vo.Student;
import bitcamp.client.vo.Teacher;

public class ServerApp {
  BoardDao boardDao = new BoardDao(new LinkedList<Board>());
  StudentDao studentDao = new StudentDao(new ArrayList<Student>());
  TeacherDao teacherDao = new TeacherDao(new ArrayList<Teacher>());
  
  StudentServlet 
}
