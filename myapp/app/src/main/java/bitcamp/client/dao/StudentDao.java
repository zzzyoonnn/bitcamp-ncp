package bitcamp.client.dao;

import bitcamp.myapp.vo.Student;

public interface StudentDao {
  public void insert(Student s);
  public Student[] findAll();
  public Student findByNo(int no);
  public void update(Student s);
  public boolean delete(Student s);
}