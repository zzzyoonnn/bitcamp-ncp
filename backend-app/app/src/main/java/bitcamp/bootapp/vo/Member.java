package bitcamp.bootapp.vo;

// 회원 데이터를 담을 메모리를 설계한다.
public class Member {

  public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	
public int no; // 필드
  public String name;
  public String tel;
  public String createdDate;
}
