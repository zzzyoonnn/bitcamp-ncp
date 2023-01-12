package bitcamp.bootapp.dao;

import java.util.Arrays;

import org.springframework.stereotype.Repository;

import bitcamp.bootapp.vo.Board;

@Repository
public class BoardDao {
	  private static final int SIZE = 100;

	  private int no;	// board 식별 번호
	  private int count;
	  private Board[] boards = new Board[SIZE];
	  private String title;
	  
	  public void insert(Board board) {
		  board.setNo(++no);
		  this.boards[this.count++] = board;
	  }
	  
	  public Board[] findAll() {
		  return Arrays.copyOf(boards, count);
	  }
	  
	  public Board findByNo(int no) {
	    for (int i = 0; i < this.count; i++) {
	      if (this.boards[i].getNo() == no) {
	        return this.boards[i];
	      }
	    }
	    return null;
	  }
	  
	  public void update(Board board) {
		  this.boards[this.indexOf(board)] = board;
	  }
	  
	  public void delete(Board board) {
		  for (int i = this.indexOf(board) + 1; i < this.count; i++) {
		      this.boards[i - 1] = this.boards[i];
		    }
		    this.boards[--this.count] = null; // 레퍼런스 카운트를 줄인다.
	  }
	  
	  private int indexOf(Board b) {
	    for (int i = 0; i < this.count; i++) {
	      if (this.boards[i].getNo() == b.getNo()) {
	        return i;
	      }
	    }
	    return -1;
	  }
}
