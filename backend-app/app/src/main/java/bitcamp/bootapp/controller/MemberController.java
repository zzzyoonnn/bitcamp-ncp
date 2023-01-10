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

import bitcamp.bootapp.dao.MemberDao;
import bitcamp.bootapp.vo.Member;

@CrossOrigin(origins = {"http://127.0.0.1:5500", "http://localhost:5500"})
@RestController
public class MemberController {

  private MemberDao memberDao = new MemberDao();
  
  @PostMapping("/members")	// POST 요청
  public Object addMember(
		  
		  // @RequestParam
		  // HTML Form 태그에서 method 가 POST 전송일 때 @RequestParam으로 값 받음
		  @RequestParam(required = false) String name,
		  @RequestParam(required = false) String tel,
		  @RequestParam(required = false) String postNo,
		  @RequestParam(required = false) String basicAddress,
		  @RequestParam(required = false) String detailAddress,
		  /* contentMap을 사용하여 이렇게 사용 가능할 것 같은데 개별로 어떻게..?
		   * 이미 제이슨 문자열로 배열의 형태로 가져오는데 이걸 다시 decoding해서 개별로 설정을 줘야하는지 아니면
		   * if문/switch문 써서 getWorling, getGender, getLevel 하실건지
		  if (m == true) {
			    contentMap.put("status", "true");
			    contentMap.put("data", "재직");
			  } else {
			    contentMap.put("status", "false");
			    contentMap.put("data", "실업");
			  }
			  return contentMap; */
		  @RequestParam(required = false) boolean isWorking,
		  @RequestParam(required = false) char gender,
		  @RequestParam(required = false) byte level,
		  @RequestParam(required = false) String password) {
	  
	  Member m = new Member();
	  m.setName(name);
	  m.setTel(tel);
	  m.setPostNo(postNo);
	  m.setBasicAddress(basicAddress);
	  m.setDetailAddress(detailAddress);
	  m.setWorking((boolean)isWorking);
	  m.setGender((char)gender);
	  m.setLevel((byte)level);
	  m.setCreatedDate(new Date(System.currentTimeMillis()).toString());
	  
	  this.memberDao.insert(m);
	  
	  // 응답 결과를 담을 맵 객체준비
	  Map<String, Object> contentMap = new HashMap<>();
	  contentMap.put("status", "success");
	  
	  return contentMap;
  }

  @GetMapping("/members")
  public Object getMembers() {
		  
	  Member[] members = this.memberDao.findAll();
		
	  Map<String, Object> contentMap = new HashMap<>();
	  contentMap.put("status", "success");
	  contentMap.put("data", members);
	  
	  return contentMap;
  }
  
  @GetMapping("/members/{memberNo}")
  
  // @PathVariable
  // URI에 사용될 변수 명 입력
  public Object getMember(@PathVariable int memberNo) {
	
	  Member m = this.memberDao.findByNo(memberNo);
	  
	  Map<String, Object> contentMap = new HashMap<>();
	  
	  if (m == null) {
	    contentMap.put("status", "failure");
	    contentMap.put("data", "해당 번호의 멤버가 없습니다.");
	  } else {
	    contentMap.put("status", "success");
	    contentMap.put("data", m);
	  }
	  return contentMap;
  }
  
  @PutMapping("/members/{memberNo}")
  public Object updateMember(
		  @PathVariable int memberNo,
		  @RequestParam(required = false) String name,
		  @RequestParam(required = false) String tel,
		  @RequestParam(required = false) String postNo,
		  @RequestParam(required = false) String basicAddress,
		  @RequestParam(required = false) String detailAddress,
		  @RequestParam(required = false) boolean isWorking,
		  @RequestParam(required = false) char gender,
		  @RequestParam(required = false) byte level,
		  @RequestParam(required = false) String password) {
	  
	  Map<String, Object> contentMap = new HashMap<>();
	  
	  Member old = this.memberDao.findByNo(memberNo);
	  if (old == null || !old.getPassword().equals(password)) {
		  contentMap.put("status", "failure");
		  contentMap.put("data", "게시글이 없거나 암호가 맞지 않습니다.");
		  return contentMap;
	  }
	  
	  Member m = new Member();
	  m.setNo(memberNo);
	  m.setName(name);
	  m.setTel(tel);
	  m.setPostNo(postNo);
	  m.setBasicAddress(basicAddress);
	  m.setDetailAddress(detailAddress);
	  m.setWorking(isWorking);
	  m.setGender(gender);
	  m.setLevel(level);
	  m.setPassword(password);
	  m.setCreatedDate(old.getCreatedDate());
	  
	  this.memberDao.update(m);
	  
	  contentMap.put("status", "success");
	  
	  return contentMap;
  }
  
  @DeleteMapping("/members/{memberNo}")
  public Object deleteMember(
		  @PathVariable int memberNo,
		  @RequestParam String password) {
	 
	  Member m = this.memberDao.findByNo(memberNo);
	  // 응답 결과를 담을 맵 객체준비
	  Map<String, Object> contentMap = new HashMap<>();
	
	if (m == null || !m.getPassword().equals(password)) {
	  contentMap.put("status", "failure");
	  contentMap.put("data", "게시글이 없거나 암호가 맞지 않습니다.");
	} else {
	  this.memberDao.delete(m);
	  contentMap.put("status", "success");
	}
  return contentMap;
  }
 
}
