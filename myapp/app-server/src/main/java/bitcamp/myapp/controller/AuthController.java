package bitcamp.myapp.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import bitcamp.myapp.service.StudentService;
import bitcamp.myapp.service.TeacherService;
import bitcamp.myapp.vo.Member;

@Controller
// Model 객체에 보관된 값들 중에서 세션에 따로 중복해서 보관할 값을 지정한다.
// => 물론 Model 객체에 보관된 값은 뷰 컴포넌트를 실행하기 전에 ServletRequest 보관소로 100% 복사된다.
// => 그리고 다음 애노테이션으로 지정한 이름의 값은 Session 보관소에 중복 저장된다.
public class AuthController {

  {
    System.out.println("AuthController 생성됨!");
  }

  @Autowired private StudentService studentService;
  @Autowired private TeacherService teacherService;

  @RequestMapping("/auth/form")
  public void form(@CookieValue(required = false) String email, 
	  Model model, 
	  HttpSession session) {
	model.addAttribute("email", email);
	if (session.getAttribute("error") != null) {
	  model.addAttribute("error", session.getAttribute("error"));
	}
  }

  @RequestMapping("/auth/login")
  public String login(
      @RequestParam("usertype") String usertype,
      @RequestParam("email") String email,
      @RequestParam("password") String password,
      @RequestParam("saveEmail") String saveEmail,
      HttpServletResponse response,
      HttpSession session,
      Model model) {

    if (saveEmail != null) {
      Cookie cookie = new Cookie("email", email);
      cookie.setMaxAge(60 * 60 * 24 * 30); // 30일 동안 유지
      response.addCookie(cookie);

    } else {
      Cookie cookie = new Cookie("email", "");
      cookie.setMaxAge(0);
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
      session.setAttribute("loginUser", member);
      session.removeAttribute("error");
      return "redirect:../../";
    } else {
      session.setAttribute("error", "loginfail");
      // error 값은 ServletRequest 보관소에 저장되고 또한 Session 보관소에도 저장된다.
      return "redirect:form";
    }

  }

  @RequestMapping("/auth/logout")
  public String logout(HttpSession session) {
    session.invalidate();
    return "redirect:../../";
  }

  @RequestMapping("/auth/fail")
  public void fail() {
  }

}









