package com.eomcs.basic.ex11;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// 정규 표현식
// 계산기에서 연산자 우선순위 통해 계산 가능
// regex regular expression
public class Exam0100 {
  public static void main(String[] args) {
	
	// 1) 패턴 정의
	// 
    Pattern pattern = Pattern.compile("\\d+|\\D", Pattern.CASE_INSENSITIVE);	// .의 갯수만큼 글자 수 잘라서 출력
    
    // 2) 패턴에 따라 콘텐트를 검사할 도구 준비
    Matcher matcher = pattern.matcher("123+2*98-24/19");
   
    // 3) 패턴의 결과를 담을 컬렉션 준비
    ArrayList<String> items = new ArrayList<>();
    
    // 4) 패턴 검사
    while (matcher.find()) {
      // 5) 패턴과 일치하는 항목을 추출하여 컬렉션에 담기
      items.add(matcher.group());
    }
    
    System.out.println("---------------------------------");
    
    for (String item : items) {
      System.out.println(item);
    }
  }
}
