package com.example;

public class ArgsTest6 {
    int grade;
    
    // ⭐ 静的変数 (クラス全体で共有される変数)
    public static int count = 0; 
    
    // ⭐ コンストラクタ: インスタンスが生成されるたびに実行される
    public ArgsTest6() {
        count++; // インスタンスが生成された回数をカウント
    }

    public static void setGrade(ArgsTest6 student, int g){
        student.grade = g; // 実質的に main の kodai.grade への代入
        student.grade++; // student.grade に 1 を加える
        
        // setGradeメソッド内でもインスタンスが生成されている
        student = new ArgsTest6(); // 新しいインスタンスを生成して代入 (countがインクリメントされる)
        student.grade = 70; // main の kodai.grade は変わらない
    }
}