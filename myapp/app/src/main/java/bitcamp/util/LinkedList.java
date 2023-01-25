package bitcamp.util;

import bitcamp.myapp.dao.DaoException;

public class LinkedList implements List {

  private Node head;
  private Node tail;
  private int size;

  @Override
  public void add(Object value) {
	  
	// 노드(상자)를 만들어 값을 저장한다.
    Node node = new Node(value);
    
    // 리스트의 마지막 노드와 연결한다.
    // => 만약 리스트에 노드가 없으면 현재 노드를 시작 노드 겸 마지막 노드로 설정한다.
    if (this.tail == null) { // size == 0, head == null
      this.head = this.tail = node;

    } else {
      // => 마지막 노드의 next 필드에 새 노드 주소를 담는다.
      this.tail.next = node;
      // => 새 노드가 마지막 노드가 되도록 tail 의 주소를 바꾼다.
      this.tail = node;
    }
    // 값을 저장할 때 마다 개수를 카운트 한다.
    this.size++;
  }

  @Override
  public Object[] toArray() {
    Object[] values = new Object[this.size];
    int index = 0;
    Node cursor = this.head;

    while (cursor != null) {
      values[index++] = cursor.value;
      cursor = cursor.next;
    }
    return values;
  }

  @Override
  public Object set(int index, Object value) {
	  //index가 0보다 작거나 Object의 크기보다 크다면 유효하지 않은 인덱스
    if (index < 0 || index >= this.size) {
      throw new IndexOutOfBoundsException("인덱스가 유효하지 않습니다.");
    }
    
    // 시작 노드의 주소를 가져온다.
    Node cursor = head;
    int i = 0;

    while (cursor != null) {
    	// 해당 인덱스의 노드를 찾는다.
      if (i == index) {
        Object old = cursor.value;
     // 커서가 가리키는 노드의 값을 파라미터로 받은 값으로 바꾼다.
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
      if (this.head == null) {
        this.tail = null;
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
    this.size--;
    return true;
  }

  // 객체의 위치를 찾는 것은
  // 객체의 타입에 따라 다를 수 있기 때문에
  // 이 클래스에서 정의하지 말고,
  // 서브 클래스에서 정의할 수 있도록
  // 그 구현의 책임을 위임해야 한다.
  @Override
  public int indexOf(Object b) {
    Node cursor = head;
    int i = 0;

    while (cursor != null) {
      if (cursor.value.equals(b)) {
        return i;
      }
      cursor = cursor.next;
      i++;
    }
 // 삭제할 값이 보관된 위치 발견 X
    return -1;
  }

  @Override	// size를 어디서 사용했는지..
  public int size() {
    return this.size;
  }

  @Override
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
 // 현재 커서가 가리키는 노드에서 value 필드의 값을 꺼내 리턴한다.
    return cursor.value;
  }
  
  @Override
  public Iterator iterator() {
	  // 이 LinkedList 객체에서 데이터를 거내주는 일을 할
	  // Iterator 구현체를 만들어 리턴한다.
	  return new ListIterator(this);
  }
}







