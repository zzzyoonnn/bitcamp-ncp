package bitcamp.client.dao;

import bitcamp.client.vo.Teacher;

public interface TeacherDao {
  void insert(Teacher t);
  Teacher[] findAll();
  Teacher findByNo(int no);
  void update(Teacher t);
  boolean delete(Teacher t);
}
