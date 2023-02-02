package bitcamp.myapp.dao;

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

import bitcamp.myapp.vo.Teacher;

public class NetworkTeacherDao implements TeacherDao {

  List<Teacher> list;
  int lastNo;
  DataInputStream in;
  DataOutputStream out;
   
  public NetworkTeacherDao(DataInputStream in, DataOutputStream out) {
    this.in = in;
    this.out = out;
  }

  @Override
  public void insert(Teacher t) {
    t.setNo(++lastNo);
    t.setCreatedDate(new Date(System.currentTimeMillis()).toString());
    list.add(t);
  }

    public Teacher[] findAll() {
        Teacher[] teachers = new Teacher[list.size()];
        Iterator<Teacher> i = list.iterator();
        int index = 0;
        while (i.hasNext()) {
            teachers[index++] = i.next();
        }
        return teachers;
    }

    public Teacher findByNo(int no) {
        Teacher t = new Teacher();
        t.setNo(no);

        int index = list.indexOf(t);
        if (index == -1) {
            return null;
        }

        return list.get(index);
    }

    public void update(Teacher t) {
        int index = list.indexOf(t);
        list.set(index, t);
    }

    public boolean delete(Teacher t) {
        return list.remove(t);
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

        	// 1) JSON 데이터를 어떤 타입의 객체로 변환할 것인지 그 타입의 정보를 준비한다.
        	TypeToken<List<Teacher>> collectionType = new TypeToken<>() {};
        	
        	// 2) 입력 스트림에서 JSON 데이터를 읽고, 지정한 타입의 객체로 변환하여 리턴한다.
        	list = new Gson().fromJson(in, collectionType);
        	
            if (list.size() > 0) {
                lastNo = list.get(list.size() - 1).getNo();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}







