package bitcamp.client.dao;

import bitcamp.myapp.vo.Teacher;

public interface TeacherDao {
  public void insert(Teacher t);
  public Teacher[] findAll();
  public Teacher findByNo(int no);
  public void update(Teacher t);
  public boolean delete(Teacher t);
}