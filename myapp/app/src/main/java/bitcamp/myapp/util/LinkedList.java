package bitcamp.myapp.util;

import bitcamp.myapp.dao.DaoException;

public class LinkedList implements List {

	private Node head;
	private Node tail;
	private int size;

	@Override
	public void add(Object value) {
		  
		  // 노드(상자)를 만들어 값을 저장한다.
		  Node node = new Node(value);
		  
		  // => 만약 리스트에 노드가 없으면 현재 노드를 시작 노드 겸 마지막 노드로 설정한다.
		  if (this.tail == null) {	// size == 0, head == null
			  this.head = this.tail = node;
		  
		  } else {
			  // => 마지막 노드의 next 필드에 새 노드 주소를 담는다.
			  this.tail.next = node;
			  
			  // => 새 노드가 마지막 노드가 되도록 tail의 주소를 바꾼다.
			  this.tail = node;
		  }
		  // 값을 저장할 때마다 크기 개수를 카운트 한다.
		  this.size ++;
	}
		
	@Override
	public Object[] toArray() {
		  
		  // 리스트의 각 노드에 보관된 값을 꺼내서 담을 배열을 준비한다.
		  Object[] values = new Object[this.size];
		  
		  // 리스트의 각 노드를 따라 가면서, 노드에서 값을 꺼내 배열에 담는다.
		  int index = 0;
		  
		  // 시작 노드의 주소를 가져온다.
		  Node cursor = this.head;
		  
		  while (cursor != null) {
			  
			  // 커서가 가리키는 노드의 value 필드의 값을 꺼내 배열에 담는다.
			  values[index++] = cursor.value;
			  
			  // 커서가 가리키는 노드의 next 필드에는 다음 노드의 주소가 들어 있다.
			  // 이것을 커서에 저장한다.
			  cursor = cursor.next;
		  }
		  
		  // 배열에 값을 담았으면 리턴한다.
		  return values;
	}
	
	@Override
	public Object set(int index, Object value) {
		  
		if (index < 0 || index >= this.size) {
			throw new IndexOutOfBoundsException("인덱스가 유효하지 않습니다.");
		}
		
		// 시작 노드의 주소를 가져온다.
		Node cursor = head;
		int i = 0;
		
		// 해당 인덱스의 노드를 찾는다.
		while (cursor != null) {
			if (i == index) {
				Object old = cursor.value;
				cursor.value = value;
				return old;
			}
			// 커서를 다음 노드로 이동한다.
			cursor = cursor.next;
			
			// index를 증가시킨다.
			i++;
		}
		return null;
	  }
	
	@Override
	public boolean remove(Object value) {

		    // 이전 노드의 주소를 저장할 레퍼런스를 준비한다.
		    Node prevNode = null;
		    
		    Node deletedNode = null;

		    // 시작 노드를 가져온다.
		    Node cursor = this.head;

		    // 해당 인덱스의 노드를 찾는다.
		    while (cursor != null) {
		      if (cursor.value.equals(value)) {
		    	  deletedNode = cursor;
		    	  break;
		      }

		      // 커서를 다음 노드를 이동시키기 전에 노드의 주소를 보관한다.
		      prevNode = cursor;

		      // 커서를 다음 노드로 이동한다.
		      cursor = cursor.next;

		  }
		    
		  if (deletedNode == null) {
			  return false;
		  }

		  // 삭제할 노드가 시작 노드라면,
		  if (prevNode == null) {

		      // 현재 head가 가리키는 다음 노드를 시작 노드로 설정한다.
			  this.head = this.head.next;

		      // 이전의 시작 노드의 next 필드 값을 지운다.
		      deletedNode.next = null;

		      // 리스트의 개수가 0이라면 tail의 주소를 지운다.
		      // 마지막 노드란 의미
		      if (this.head == null) {
		        tail = null;
		      }

		  } else {
		      // 이전 노드가 커서의 다음 노드를 가리키도록 한다.
		      prevNode.next = deletedNode.next;

		      // 삭제할 노드의 다음 노드 주소를 지운다.
		      deletedNode.next = null;

		      // 삭제한 노드가 마지막 노드인 경우
		      if (prevNode.next == null) {
		        // 마지막 노드의 주소를 바꾼다.
		        this.tail = prevNode;
		      }
		    }

		    // 목록의 개수를 하나 줄인다.
		    this.size--;
		    return true;
		  }

	  		
	@Override
	public int indexOf(Object value) {
		Node cursor = head;
		int i = 0;
		
		while (cursor != null) {
			if (cursor.value.equals(value)) {
				return i;
			}
			cursor = cursor.next;
			i++;
		}
		return -1;
	}
	
	@Override
	public int size() {
		return this.size;
	}
	  
	// 개발하는 중에 서브 클래스들이 공통으로 필요로 하는 기능을 발견하게 된다.
	// 그런 상황이면 이렇게 수퍼 클래스에 해당 메서드를 정의하면 된다.
	public Object get(int index) {
		if (index < 0 || index >= this.size) {
			throw new DaoException("인덱스가 무효합니다!");
		}

		// 시작 노드의 주소를 가져온다.
		Node cursor = head;
		int i = 0;
		
		while (i < index) {
			// 커서를 다음 노드로 이동한다.
			cursor = cursor.next;
			
			// index를 증가시킨다.
			i++;
		}
		
		// 현재 커서가 가리키는 노드에서 value 필드의 값을 꺼내 리턴하낟.
		return cursor.value;
	}
}

















