package bitcamp.client.dao;

import java.io.DataInputStream;
import java.io.DataOutputStream;

import com.google.gson.Gson;

import bitcamp.myapp.vo.Student;

public class NetworkStudentDao implements StudentDao {

  DataInputStream in;
  DataOutputStream out;

  public NetworkStudentDao(DataInputStream in, DataOutputStream out) {
    this.in = in;
    this.out = out;
  }

  @Override
  public void insert(Student student) {
	fetch("student", "insert", student);
  }

  @Override
  public Student[] findAll() {
	return new Gson().fromJson(fetch("student", "findAll"), Student[].class);
  }

  @Override
  public Student findByNo(int no) {
	return new Gson().fromJson(fetch("student", "findByNo"),Student.class);
  }

  @Override
  public void update(Student student) {
	fetch("student", "update", student);
  }
  
  @Override
  public boolean delete(Student student) {
	fetch("student", "delete", student);
    return true;
  }
  
  public String fetch(String dataName, String action, Object... data) throws DaoException {
	try {
	  out.writeUTF(dataName);
	  out.writeUTF(action);
	  
	  if (data.length > 0) {
		out.writeUTF(new Gson().toJson(data[0]));
	  }
	  
	  // 응답
	  String status = in.readUTF();
	  if (status.equals("400")) {
		throw new DaoException("클라이언트 요청 오류!");
	  } else if(status.equals("500")) {
		throw new DaoException("서버 실행 오류!");
	  }
	  return in.readUTF();
	} catch (DaoException e) {
	  throw e;
	} catch (Exception e) {
	  throw new DaoException("오류 발생!", e);
	}
  }
}







