package com.example;

public class ArgsTest5 {
    int grade;

    public static void main(String[] args){
        int i = 60; // 元のコードの '60"' を '60' に修正
        ArgsTest5 kodai = new ArgsTest5();
        setGrade(kodai, i); // i を引数にして呼び出し i の値は変わらない
        System.out.println("1: grade = " + kodai.grade + " i = " + i);
        setGrade(kodai, 70); // 値を直接引数にして呼び出し
        System.out.println("2: grade = " + kodai.grade + " i = " + i);
    }
    public static void setGrade(ArgsTest5 student, int grade){
    student.grade = grade; // 実質的に main の kodai.grade への代入
    student.grade++; // student.grade に 1 を加える (kodai.grade も)
    student = new ArgsTest5(); // 新しいインスタンスを生成して代入
    student.grade = 80; // main の kodai.grade は変わらない
}
}
