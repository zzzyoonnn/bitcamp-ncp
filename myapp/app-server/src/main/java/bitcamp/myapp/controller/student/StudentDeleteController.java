package bitcamp.myapp.controller.student;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.myapp.controller.PageController;
import bitcamp.myapp.service.StudentService;

public class StudentDeleteController implements PageController {

  private StudentService studentService;

  public StudentDeleteController(StudentService studentService) {
    this.studentService = studentService;
  }

  public String execute(HttpServletRequest request, HttpServletResponse response) {
    try {
      studentService.delete(Integer.parseInt(request.getParameter("no")));
    } catch (Exception e) {
      e.printStackTrace();
      request.setAttribute("error", "other");
    }
    return "/student/delete.jsp";
  }
}
