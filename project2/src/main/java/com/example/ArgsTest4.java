package com.example;

public class ArgsTest4 {
    int grade;
    public static void main(String[] args){
    int i = 60;
    ArgsTest4 kodai = new ArgsTest4();
    kodai.setGrade(i); // i を引数にして呼び出し　i の値は変わらない
    System.out.println("1: grade = " + kodai.grade + " i = " + i);
    kodai.setGrade(70); // 値を直接引数にして呼び出し
    System.out.println("2: grade = " + kodai.grade + " i = " + i);
  }

  public void setGrade(int grade) {
    this.grade = grade;
  }
}
