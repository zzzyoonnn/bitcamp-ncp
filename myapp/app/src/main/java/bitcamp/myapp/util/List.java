package bitcamp.myapp.util;

// 객체 목록을 다루는 기능을 규정한다.
// 규칙은 공개해야하므로 abstract

public interface List {
	// interface의 default값은 public abstract
	// interface의 모든 메서드는 무조건 public이기에 public 생략 가능
	// 추상 메서드이기 때문에 abstract를 생략해도 추상메서드
	void add(Object value);
	Object[] toArray();
	Object get(int index);
	Object set(int index, Object value);
	boolean remove(Object value);
	int indexOf(Object value);
	int size();
}
