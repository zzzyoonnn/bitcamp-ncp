package bitcamp.myapp.util;

import java.util.Arrays;

public class ArrayList implements List {
	private static final int SIZE = 3;

	  private int count;
	  protected Object[] objects = new Object[SIZE];

	  public void add(Object object) {
	    if (count == objects.length) {
	      objects = Arrays.copyOf(objects, objects.length + (objects.length >> 1));
	    }
	    this.objects[this.count++] = object;
	  }

	  public Object[] toArray() {
	    return Arrays.copyOf(objects, count);
	  }
	  
	  @Override
	  public Object get(int index) {
		  if (index < 0 || index >= this.count) {
			  throw new IndexOutOfBoundsException("인덱스가 무효합니다.");
		  }
		  return this.objects[index];
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
	  public int indexOf(Object object) {
		  for (int i = 0; i < this.count; i++) {
			  if (objects[i].equals(object)) {
				  return i;
			  }
		  }
		  return -1;
	  }

		@Override
		public boolean remove(Object value) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public int size() {
			// TODO Auto-generated method stub
			return 0;
		}

}
