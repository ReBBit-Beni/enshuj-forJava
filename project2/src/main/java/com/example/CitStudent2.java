package com.example;

public class CitStudent2 {
    String no;         // 学生番号
    String name;       // 氏名 (文字列型)
    int year;          // 学年 (1-4)
    int grade;         // 成績 (0-100)
    int department;    // 学科 (1:CS 2:NS など)

    // コンストラクタ１（引数なし）
    public CitStudent2() {
        no = "";
        name = "";
        year = 0;
        grade = 0;
        department = 0;
    }

    // コンストラクタ２（引数あり：no, name）
    public CitStudent2(String p_no, String p_name) {
        // setNo()を呼び出して、noとdepartmentを初期化
        setNo(p_no);
        name = p_name;
        year = 0;
        grade = 0;
        // departmentはsetNo()で設定されるため、明示的な初期化は不要（setNo()内の処理に依存）
    }

    // ★ コンストラクタ３（引数あり：no, name, year）を追加
    public CitStudent2(String p_no, String p_name, int p_year) {
        // setNo()を呼び出して、noとdepartmentを初期化
        setNo(p_no);
        name = p_name;
        year = p_year;
        grade = 0;
        // departmentはsetNo()で設定されるため、明示的な初期化は不要
    }

    public void setNo(String p_no) {
        no = p_no;
        try {
            // 学科を学生番号の4文字目から自動設定
            department = Integer.parseInt("" + p_no.charAt(3));
        } catch (Exception e) {
            System.err.println("エラー: 学生番号から学科を設定できませんでした。");
            department = 0;
        }
    }

    // ... (その他のゲッター/セッターは省略) ...
    public String getNo() { return no; }
    public void setName(String p_name) { name = p_name; }
    public String getName() { return name; }
    public void setYear(int p_year) { year = p_year; }
    public int getYear() { return year; }
    public void setGrade(int p_grade) { grade = p_grade; }
    public int getGrade() { return grade; }
    public int getDepartment() { return department; }
}
