package bitcamp.myapp.vo;

// 회원 데이터를 담을 메모리를 설계한다.
public class Student extends Teacher{
	public int no; // 필드
	public String postNo;
	public String basicAddress;
	public String detailAddress;
	public boolean working;
	public char gender;
	public byte level;
	
  public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	
	public String getPostNo() {
		return postNo;
	}
	public void setPostNo(String postNo) {
		this.postNo = postNo;
	}
	public String getBasicAddress() {
		return basicAddress;
	}
	public void setBasicAddress(String basicAddress) {
		this.basicAddress = basicAddress;
	}
	public String getDetailAddress() {
		return detailAddress;
	}
	public void setDetailAddress(String detailAddress) {
		this.detailAddress = detailAddress;
	}
	public boolean isWorking() {
		return working;
	}
	public void setWorking(boolean working) {
		this.working = working;
	}
	public char getGender() {
		return gender;
	}
	public void setGender(char gender) {
		this.gender = gender;
	}
	public byte getLevel() {
		return level;
	}
	public void setLevel(byte level) {
		this.level = level;
	}

}
