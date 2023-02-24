package bitcamp.myapp.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bitcamp.myapp.service.StudentService;
import bitcamp.myapp.service.TeacherService;
import bitcamp.myapp.vo.Member;

public class LoginController implements PageController {
  private static final long serialVersionUID = 1L;

  private StudentService studentService;
  private TeacherService teacherService;

  public LoginController(StudentService studentService, TeacherService teacherService) {
    this.studentService = studentService;
    this.teacherService = teacherService;
  }

  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response) {

    String usertype = request.getParameter("usertype");
    String email = request.getParameter("email");
    String password = request.getParameter("password");

    if (request.getParameter("saveEmail") != null) {
      Cookie cookie = new Cookie("email", email);
      cookie.setMaxAge(60 * 60 * 24 * 30); // 30일 동안 유지
      response.addCookie(cookie);

    } else {
      Cookie cookie = new Cookie("email", "");
      cookie.setMaxAge(0);
      // response.addCookie(cookie);
      response.addCookie(cookie);
    }

    Member member = null;
    switch (usertype) {
      case "student":
        member = studentService.get(email, password);
        break;
      case "teacher":
        member = teacherService.get(email, password);
        break;
    }

    if (member != null) {
      HttpSession session = request.getSession();
      session.setAttribute("loginUser", member);
      return "redirect:../"; // ===> http://localhost:8080/web/
    } else {
      request.setAttribute("error", "loginfail");
      return "/auth/form.jsp";
    }

  }

}









