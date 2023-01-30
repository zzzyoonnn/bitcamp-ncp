package com.eomcs.basic;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.function.Consumer;

public class Test2 {
	
	static class Member {
		int no;
		String name;
		
		public Member(int no, String name) {
			this.no = no;
			this.name = name;
		}

		@Override
		public String toString() {
			return "Member [no=" + no + ", name=" + name + "]";
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
		HashSet<Member> list = new HashSet<>();
		list.add(new Member(1, "홍길동"));
		list.add(new Member(2, "임꺽정"));
		list.add(new Member(3, "유관순"));
		list.add(new Member(4, "안중근"));
		list.add(new Member(5, "홍길동"));
		
		list.remove(new Member(2, null));
		
		print(list);
	}

	// 순서가 뒤죽박죽인 이유는 줄을 나눠서 남은 나머지의 크기를 확인하여 출력하기 때문에
	// functional interface -> 추상 메서드가 한개인 인터페이스
	// 조회하는 방법

	static void print(List<String> list) {
		list.forEach(new Consumer<String>() {
			@Override
			public void accept(String t) {
				// TODO Auto-generated method stub
				System.out.println(t);
			}
				System.out.println("----------------------------");
			
		});
		
		/////////////////////////////////////////////////////////////////////
		
		static void print(List<Member> list) {
		list.forEach(System.out::println);
				System.out.println("----------------------------");
			}
		
		
		 * 
		 * */
		
	//}
}


//Iterator<String> i = list.iterator();
//while (i.hasNext()) {
//System.out.println(i.next());

//for (String e : list) {	// list에는 리스트나 이터러블 인터페이스 가능	// 이터러블 인터페이스를 갖고 있음
//System.out.println(e);
//}