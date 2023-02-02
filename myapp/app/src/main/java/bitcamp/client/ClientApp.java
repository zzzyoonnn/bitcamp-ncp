package bitcamp.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import bitcamp.client.dao.LocalStudentDao;
import bitcamp.client.dao.LocalTeacherDao;
import bitcamp.client.dao.NetworkBoardDao;
import bitcamp.client.handler.BoardHandler;
import bitcamp.client.handler.StudentHandler;
import bitcamp.client.handler.TeacherHandler;
import bitcamp.client.vo.Student;
import bitcamp.client.vo.Teacher;
import bitcamp.util.Prompt;

public class ClientApp {

  public static void main(String[] args) {
	new ClientApp().execute("localhost", 8888);
  }

  void execute(String ip, int port) {
	try (Socket socket = new Socket(ip, port);
		  DataOutputStream out = new DataOutputStream(socket.getOutputStream());
		  DataInputStream in = new DataInputStream(socket.getInputStream())) {
		
		NetworkBoardDao boardDao = new NetworkBoardDao(in, out);
		
		
		LocalStudentDao studentDao = new LocalStudentDao(new ArrayList<Student>());
		studentDao.load("student.json");
		
		LocalTeacherDao teacherDao = new LocalTeacherDao(new ArrayList<Teacher>());
		teacherDao.load("teacher.json");
		
		StudentHandler studentHandler = new StudentHandler("학생", studentDao);
		TeacherHandler teacherHandler = new TeacherHandler("강사", teacherDao);
		BoardHandler boardHandler = new BoardHandler("게시판", boardDao);
		
		
		loop: while (true) {
			System.out.println("1. 학생관리");
			System.out.println("2. 강사관리");
			System.out.println("3. 게시판");
			System.out.println("9. 종료");
			
			int menuNo;
			try {
				menuNo = Prompt.inputInt("메뉴> ");
			} catch (Exception e) {
				System.out.println("메뉴 번호가 옳지 않습니다!");
				continue;
			}
			
			try {
				switch (menuNo) {
				case 1: studentHandler.service(); break;
				case 2: teacherHandler.service(); break;
				case 3: boardHandler.service(); break;
				case 9: break loop; // loop 라벨이 붙은 while 문을 나간다.
				default:
					System.out.println("잘못된 메뉴 번호 입니다.");
				}
			} catch (Exception e) {
				System.out.printf("명령 실행 중 오류 발생! - %s : %s\n",
						e.getMessage(),
						e.getClass().getSimpleName());
	}
	  
	  
      }
    }
  }

} // class App









