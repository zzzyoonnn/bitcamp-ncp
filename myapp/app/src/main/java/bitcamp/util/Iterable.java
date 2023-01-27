package bitcamp.util;

// Iterator 구현체를 리턴 받기 위해 호출할 메서드 규칙 정의
// iterator()가 리턴하는 객체(Iterator)가 어떤 타입의 데이터를 다룰 것인지 지정한다.
public interface Iterable<T> {
  Iterator<T> iterator();
}
