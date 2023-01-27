package bitcamp.util;

// 객체 목록을 다루는 기능을 규정한다.
// => List 인터페이스는 Iteratable 규칙을 상속 받기 때문에
//    List를 구현하는 클래스는 Iterable 규칙도 함께 구현해야 한다.
// List 인터페이스를 구현하는시점에서 다룰 데이터의 타입을 지정한다.
// 	  예) List<Board>
public interface List<E> extends Iterable<E> {
  void add(E value);
  Object[] toArray();	// 
  E get(int index);
  E set(int index, E value);
  boolean remove(E value);
  int indexOf(E value);
  int size();
}
