package bitcamp.myapp.dao;

import java.sql.Date;
import bitcamp.myapp.util.ArrayList;
import bitcamp.myapp.util.List;
import bitcamp.myapp.vo.Teacher;

// @Component
public class TeacherDao {
	
	List list = new ArrayList();

	int lastNo;
	
	public TeacherDao(List list) {
		this.list = list;
	}
	
	public void append(Teacher teacher) {
		teacher.setNo(++lastNo);
		teacher.setCreatedDate(new Date(System.currentTimeMillis()).toString());
		list.add(teacher);
	}
	
	public Teacher[] findAll() {
		Teacher[] teachers = new Teacher[list.size()];
		Object[] arr = list.toArray();
		for (int i = 0; i < teachers.length; i++) {
			teachers[i] = (Teacher) arr[i];
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
		return (Teacher) list.get(index);
	}
	
	public void modify(Teacher t) {
		int index = list.indexOf(t);
		list.set(index, t);
	}
	
	public boolean delete(Teacher t) {
		return list.remove(t);
	}
}







