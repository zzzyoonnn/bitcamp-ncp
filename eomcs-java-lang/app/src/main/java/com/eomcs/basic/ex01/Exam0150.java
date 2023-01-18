// hash code 응용 - HashSet 과 hashCode()
// 코드 확인
package com.eomcs.basic.ex01;

import java.util.HashSet;

public class Exam0150 {
  
  static class Student {
    String name;
    int age;
    boolean working;
    
    public Student(String name, int age, boolean working) {
      this.name = name;
      this.age = age;
      this.working = working;
    }
    
//    @Override
//    public int hashCode() {
//    	// TODO Auto-generated method stub
//    	return 100;
//    }
    
//    @Override
//    public boolean equals(Object obj) {
//    	// TODO Auto-generated method stub
//    	return true;
    }
  }
  
  public static void main(String[] args) {
    
    Student s1 = new Student("홍길동", 20, false);
    Student s2 = new Student("홍길동", 20, false);
    Student s3 = new Student("임꺽정", 21, true);
    Student s4 = new Student("유관순", 22, true);
    
    System.out.println(s1 == s2);
    
    System.out.println(s1.hashCode());
    System.out.println(s2.hashCode());
    System.out.println(s3.hashCode());
    System.out.println(s4.hashCode());
    System.out.println("--------------------");
    
    // 해시셋(집합)에 객체를 보관한다.
    HashSet<Student> set = new HashSet<Student>();
    set.add(s1);
    set.add(s2);
    set.add(s3);
    set.add(s4);
    
    // 해시셋에 보관된 객체를 꺼낸다.
    Object[] list = set.toArray();
    for (Object obj : list) {
      Student student = (Student) obj;
      System.out.printf("%s, %d, %s\n", 
          student.name, student.age, student.working ? "재직중" : "실업중");
    }
    
    // 집합?
    // => 중복 값을 저장할 수 없다.
    // 
    // HashSet = Hash + Set
    // => 값을 저장할 때 해시값을 계산하여 저장 위치를 알아낸다.
    // => 집합 방식으로 목록을 다룬다. 즉, 중복 값을 저장하지 않는다.
    // => 저장 과정:
    // 		1) equals()와 hashCode()를 호출하여 중복 여부를 검사한다.
    //		2) equals()의 리턴 값도 true이고 hashCode()의 리턴 값도 같을 경우,
    //		   같은 객체로 판단하여 저장하지 않는다.
    //			- 리턴 값이 true라면, 같은 객체로 간주하여 저장하지 않는다.
    //			- 리턴 값이 false라면, 다른 객체로 간주하여 저장한다.
    //		3) 단, 저장 위치는 hashCode()의 리턴 값을 사용하여 계산한다.
    // 
    
    // 현재 예제의 문제점?
    // => s1과 s2는 같은 데이터를 갖고 있지만, 다른 해시 코드를 리턴하기 때문에 
    //    HashSet 입장에서는 s1과 s2를 다른 값으로 취급한다.
    // => 그래서 s1과 s2 모두 HashSet에 보관된 것이다.
    // 
    // 해결책?
    // => Student 클래스에서 hashCode()를 오버라이딩 하여 
    //    같은 데이터에 대해 같은 해시코드를 리턴하도록 만들어야 한다.
  }

}







