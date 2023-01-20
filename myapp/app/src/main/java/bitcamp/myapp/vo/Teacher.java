package bitcamp.myapp.vo;

// Member를 상속받아서 equals 필요 X
public class Teacher extends Member {
  // Member 클래스의 코드를 사용하겠다고 선언한다.
  private String email;
  private int degree;
  private String school;
  private String major;
  private int wage;
  
  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
  }
  public int getDegree() {
    return degree;
  }
  public void setDegree(int string) {
    this.degree = string;
  }
  public String getSchool() {
    return school;
  }
  public void setSchool(String school) {
    this.school = school;
  }
  public String getMajor() {
    return major;
  }
  public void setMajor(String major) {
    this.major = major;
  }
  public int getWage() {
    return wage;
  }
  public void setWage(int wage) {
    this.wage = wage;
  }
public void setDegree(String inputString) {
	// TODO Auto-generated method stub
	
}
  }

