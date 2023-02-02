package bitcamp.client.dao;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.Date;
import java.util.Iterator;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import bitcamp.client.vo.Student;

public class NetworkStudentDao implements StudentDao {

  List<Student> list;
  int lastNo;
  DataInputStream in;
  DataOutputStream out;

  public NetworkStudentDao(DataInputStream in, DataOutputStream out) {
    this.in = in;
    this.out = out;
  }

  @Override
  public void insert(Student s) {
    s.setNo(++lastNo);
    s.setCreatedDate(new Date(System.currentTimeMillis()).toString());
    list.add(s);
  }

  @Override
  public Student[] findAll() {
    Student[] students = new Student[list.size()];
    Iterator<Student> i = list.iterator();
    int index = 0;
    while (i.hasNext()) {
      students[index++] = i.next();
    }
    return students;
  }

  @Override
  public Student findByNo(int no) {
    Student s = new Student();
    s.setNo(no);

    int index = list.indexOf(s);
    if (index == -1) {
      return null;
    }
    return list.get(index);
  }

  @Override
  public void update(Student s) {
    int index = list.indexOf(s);
    list.set(index, s);
  }

  @Override
  public boolean delete(Student s) {
    return list.remove(s);
  }

  
  public void save(String filename) {
    try (FileWriter out = new FileWriter(filename)) {
      Gson gson = new Gson();
      String json = gson.toJson(list);
      out.write(json);

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void load(String filename) {
    if (list.size() > 0) { // 중복 로딩 방지!
      return;
    }

    try (BufferedReader in = new BufferedReader(new FileReader(filename))) {
      list = new Gson().fromJson(in, new TypeToken<List<Student>>() {});
                
      if (list.size() > 0) {
        lastNo = list.get(list.size() - 1).getNo();
      }

    } catch (Exception e) {
      e.printStackTrace();
    }
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
	  } else if (status.equals("500")) {
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







