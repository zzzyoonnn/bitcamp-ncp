package bitcamp.client.dao;

import bitcamp.client.vo.Board;

public interface BoardDao {
  void insert(Board board);
  Board[] findAll();
  Board findByNo(int no);
  void update(Board b);
  boolean delete(Board b);
}























