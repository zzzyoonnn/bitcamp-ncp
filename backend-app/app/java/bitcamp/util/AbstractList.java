package bitcamp.util;

import java.util.Arrays;

public abstract class AbstractList implements List {

  protected int size;

  @Override
  public Object get(int index) {
    if (index < 0 || index >= this.size) {
      throw new IndexOutOfBoundsException("인덱스가 무효합니다.");
    }
    return null;
  }

  @Override
  public int size() {
    return this.size;
  }
  
  @Override
  public Iterator iterator() {
	  
		// iterator() 메서드 안에서만 사용하는 클래스라면
		// 이 메서드 안에 두는 것이 유지보수에 좋다.
	  	// - 인스턴스를 한 개만 만들어 사용하고 클래스의 크기도 작다면,
	  	//   익명 클래스로 만드는 것이 코드를 간결하게 만든다.
		//
		// => anonymous class = 클래스 정의 + 객체 생성 코드
		//
	  		// => return 문 + anonymus class
	  		//Iterator obj = new Iterator() {
	  			//List list;
	  			
	//	}
	  		// 로컬 클래스의 생성자를 호출할 때
	  		// 바깥 클래스의 인스턴스 주소를 넘길 필요 ㅄ다.
	  		// 컴파일러가 대신 처리한다
	  // 이 LinkedList 객체에서 데이터를 거내주는 일을 할
	  // Iterator 구현체를 만들어 리턴한다.
	  return new Iterator() {
		  int cursor;
			
	  		// 	바깥 크래스의 인스턴스를 사용하기 위해 생성자에서 그 주소를 받을 필요   ㅌ
	  			// - 컴파일러가 바깥 클래스의 객체를 주소로 보관할 필드를 자동으로 생성하고
	  			// - 바깥 클래스의 객체 주소를 받을 수 있게 기존 생선ㅇ자를 자동으로 변경한다.
	  			// - 따라서 다음과 같이 개발자가 직접 필드와 생성자를 추가할 필요가 없다.
	  			// - 와우~!!! 편리해라!
	  			// - 대신 바깥 클래스의 인스턴스를 사용하려면 다음과 같이 객체를 지정해야 한다ㅣ.
	  			
	  			// 바깥 클래스명.this.클래스 명
//			public ListIterator(List list) {
//				this.list = list;
//			}
//			
			@Override
			 public boolean hasNext() {
				// cursor가 유효한 인덱스를 가리키고 있는지 검사한다.
				return cursor >= 0 && cursor < AbstractList.this.size();
			}
			
			
			@Override
			public Object next() {
				// cursor가 가리키는 인덱스의 값을 꺼낸다.
				// => 작업 수행 후 cursor는 다음 인덱스를 가리킨다.
				return AbstractList.this.get(cursor++);
			}
			
	  };
  }
  
}






