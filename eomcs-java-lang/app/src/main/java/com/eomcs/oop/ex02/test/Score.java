package com.eomcs.oop.ex02.test;

public class Score {
  String name;
  int kor;
  int eng;
  int math;
  int sum;
  float aver;

  public Score(String n, int k, int e, int m) {
    this.name = n;
    this.kor = k;
    this.eng = e;
    this.math = m;

    compute();
  }

  public void compute() {
    this.sum = this.kor + this.eng + this.math;
    this.aver = (float) this.sum / 3;
  }
}
