package bitcamp.client.dao;

import bitcamp.myapp.vo.Board;

public interface BoardDao {
  public void insert(Board board);
  public Board[] findAll();
  public Board findByNo(int no);
  public void update(Board b);
  public boolean delete(Board b);
}























