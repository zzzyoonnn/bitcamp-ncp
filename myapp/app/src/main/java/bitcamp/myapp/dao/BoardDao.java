package bitcamp.myapp.dao;

import java.sql.Date;
import java.util.LinkedList;

import bitcamp.myapp.util.List;
import bitcamp.myapp.vo.Board;

public class BoardDao {
	
	// 목록을 관리하기 위해 ObjectDao를 상속 받는 대신에
	// 그와 동일한 기능을 수행하는 LinkedList를 사용한다.
	// 특정 클래스를 지정하기 보다는
	// 인터페이스를 통해 관계를 느슨하게 만든다.
	List list;
	
	public BoardDao(List list) {
		// List 규칙에 따라서 만든 객체를 외부에서 주입받는다.
		// 이렇게 하면 이 클래스는 ArrayList 또는 LinkedList와 같은
		// 특정 클래스와의 관계가 없어지낟.
		
		this.list = list;
	}
	
	// 가장 최근 게시글의 글 번호를 저장하는 필드
	// 가장 최근 게시글이 삭제되더라도 그 값은 그대로 유지할 것이다.
	int lastNo;
	
	// 수퍼 클래스의 insert()는 객체를 등록할 때 번호를 자동 증가시키는 기능이 없다.
	// 그러나 BoardDao는 그런 기능이 필요하다.
	// => 수퍼 클래스의 메서드를 서브 클래스의 역할이나 목적에 맞게 재정의한다.
	// => 이것을 '오버라이딩(Overriding)'이라 부른다.
	
	public void insert(Board board) {
		// 객체를 배열에 담기 전에 그 객체의 번호를 설정한다.
		board.setNo(++lastNo);
		
		// 인스턴스를 생성할 때의 날짜와 시각을 설정한다.
		board.setCreatedDate(new Date(System.currentTimeMillis()).toString());
		
		// 그런 후에 수퍼 클래스에서 상속 받은 insert()를 사용하여 객체를 배열에 보관한다.
		list.add(board);
		
		// super.insert() ?
		// => 현재 클래스에서 insert()를 찾지 말고, 수퍼 클래스에서 찾아 올라 가라!
	}
	
	public Board[] findAll() {
		Board[] boards = new Board[list.size()];
		Object[] arr = list.toArray();
		for (int i = 0; i < boards.length; i++) {
			boards[i] = (Board) arr[i];
		}
		return boards;
	}
	
	// Board 객체를 게시글 번호를 찾는 메서드
	  public Board findByNo(int no) {
	    Board b = new Board();
	    b.setNo(no);
	    
	    int index = list.indexOf(b);
	    if (index == -1) {
	    	return null;
	    }

	    return (Board) list.get(index);
	  }
	  
	public void update(Board b) {
		int index = list.indexOf(b);
		list.set(index, b);
	}

	public boolean delete(Board b) {
		return list.remove(b);
	}
}














