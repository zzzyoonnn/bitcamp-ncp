package bitcamp.myapp.controller.student;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.myapp.controller.PageController;

public class StudentFormController implements PageController {

  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response) {
	return "/student/form.jsp";

  }
}
