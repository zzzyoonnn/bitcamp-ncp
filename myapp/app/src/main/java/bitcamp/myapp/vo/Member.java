package bitcamp.myapp.vo;

import java.util.Objects;

public class Member {
  private int no;
  private String name;
  private String tel;
  private String createdDate;


  @Override
public String toString() {
	return "Member [no=" + no + ", name=" + name + ", tel=" + tel + ", createdDate=" + createdDate + "]";
}
@Override
public int hashCode() {
	return Objects.hash(createdDate, name, no, tel);
}
@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Member other = (Member) obj;
	return Objects.equals(createdDate, other.createdDate) && Objects.equals(name, other.name) && no == other.no
			&& Objects.equals(tel, other.tel);
}
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


}
