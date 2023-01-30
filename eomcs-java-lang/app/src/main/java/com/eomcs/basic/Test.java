package com.eomcs.basic;
import java.util.Collection;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Set;

public class Test {
	
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
		HashMap<String, Member> map = new HashMap<>();
		map.put("aaa", new Member(1, "aaa"));
		map.put("bbb", new Member(2, "bbb"));
		map.put(null, new Member(3, "ccc"));
		map.put("ddd", null);
		map.put(null, new Member(5, "eee"));
		
		print2(map);
		
		Collection<Member> values1 = map.values();
/*
		print(values1);
		
		map.put("fff", new Member(6, "fff"));
		map.remove("bbb");

		print(values1);
*/		
		Hashtable<String, Member> table = new Hashtable<>();
		table.put("aaa", new Member(1, "aaa"));
		table.put("bbb", new Member(2, "bbb"));
		table.put("ccc", new Member(3, "ccc"));
		table.put("ddd", new Member(4, "ddd"));
		table.put("eee", new Member(5, "eee"));
		print2(table);
/*		
		Collection<Member> values2 = table.values();
		print(values2);
		
		table.put("fff", new Member(6, "fff"));
		table.remove("bbb");

		print(values2);
*/		
	}

	// 순서가 뒤죽박죽인 이유는 줄을 나눠서 남은 나머지의 크기를 확인하여 출력하기 때문에
	// functional interface -> 추상 메서드가 한개인 인터페이스
	// 조회하는 방법
	static void print(Collection<Member> list) {
		list.forEach(System.out::println);
		System.out.println("----------------------------");
	}
	
	static void print2(Map<String,Member> map) {
		Set<Entry<String, Member>> entrySet = map.entrySet();
		for(Entry<String, Member> entry : entrySet) {
			System.out.printf("%s ==> %s\n", entry.getKey(), entry.getValue());
		}
		
		System.out.println("----------------------------");
	}
	
		
		/*
		// key 값 꺼낼 때
		Set<String> keySet = map.keySet();
		for(String k :keySet) {
			System.out.printf("%s ==> %s\n", k, map.get(k));

		// value 값 꺼낼 때
		Collection<Member> values = map.values();
		for (Member m : values) {
			System.out.println(m);
		}
		System.out.println("----------------------------");
		*/
	//}

			
		/*
		 * 
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