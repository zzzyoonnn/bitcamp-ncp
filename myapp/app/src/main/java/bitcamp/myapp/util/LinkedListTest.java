package bitcamp.myapp.util;

import java.util.LinkedList;
import java.util.Objects;

public class LinkedListTest {

	static class Member {
		int no;
		String name;
		String tel;
		
		public Member(int no, String name, String tel) {
			this.no = no;
			this.name = name;
			this.tel = tel;
		}

		@Override
		public String toString() {
			return "Member [no=" + no + ", name=" + name + ", tel=" + tel + "]";
		}

		@Override
		public int hashCode() {
			return Objects.hash(no);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Member other = (Member) obj;
			return no == other.no;
		}
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LinkedList list = new LinkedList();
		
		list.add(new Member(1, "aaa", "1111"));	// no는 입력해주지만 index는 자연히 생성됨
		list.add(new Member(2, "bbb", "2222"));
		list.add(new Member(3, "ccc", "3333"));
		list.add(new Member(4, "ddd", "4444"));
		
		print(list);
		
		System.out.println(list.remove(new Member(3, null, null)));
		print(list);
		System.out.println(list.remove(new Member(4, null, null)));
		print(list);
		System.out.println(list.remove(new Member(1, null, null)));
		print(list);
		System.out.println(list.remove(new Member(2, null, null)));
		print(list);
		list.add(new Member(4, "ddd", "4444"));
		print(list);
		
//		list.set(2, new Member(3, "cccx", "3333x"));	// add에서는 no를 사용하였지만 
//		print(list);
//		list.set(0, new Member(1, "aaax", "1111x"));	// set에서는 index를 사용하여서
//		print(list);
//		list.set(3, new Member(4, "ddddx", "4444x"));	// 0, 1, 2 ~ 순으로 입력됨
//		print(list);
//		list.set(4, new Member(4, "ddddx", "4444x"));	// 유효하지 않은 인덱스 지정
//		print(list);
	}


	static void print(LinkedList list) {
		System.out.println("----------------------------------");
		for (Object obj : list.toArray()) {
			System.out.println(obj);
		}
	}
	

}
