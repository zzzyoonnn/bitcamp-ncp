package bitcamp.myapp.controller.teacher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.myapp.controller.PageController;
import bitcamp.myapp.service.TeacherService;

public class TeacherListController implements PageController {

  private TeacherService teacherService;

  public TeacherListController(TeacherService teacherService) {
    this.teacherService = teacherService;
  }

  public String execute(HttpServletRequest request, HttpServletResponse response) {
    request.setAttribute("teachers", teacherService.list());
    return "/teacher/list.jsp";
  }
}
