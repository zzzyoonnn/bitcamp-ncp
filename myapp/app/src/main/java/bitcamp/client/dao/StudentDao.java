package bitcamp.client.dao;

import bitcamp.client.vo.Student;

public interface StudentDao {
  public void insert(Student s);
  Student[] findAll();
  Student findByNo(int no);
  void update(Student s);
  boolean delete(Student s);
}







