package com.example;

public class SetGradeSample {
    public static void main(String[] args) {
        // 元のデータ:
        String studentNo = "24G3018";
        String studentName = "工大 太郎";
        int studentGrade = 60;
        
        // ★ 変更点1: コンストラクタ２（引数あり）を使用し、noとnameを初期化
        CitStudent2 kodai = new CitStudent2(studentNo, studentName); 
        
        // ★ 変更点2: noとnameの設定はコンストラクタで行ったため、コメントアウト
        //kodai.setNo(studentNo);               // 学生番号 (String 型)
        //kodai.setName(studentName);            // 氏名 (String 型)
        
        // ★ 変更点3: setNo()を呼び出して、departmentを自動設定する
        //   （コンストラクタ２ではdepartmentが0になるため、この行は元の動作維持のために必要）
        kodai.setNo(studentNo);

        kodai.setYear(2);                     // 学年 (int 型)
        kodai.setGrade(studentGrade);         // 成績 (int 型: 0〜100 の範囲)

        // 学科の設定：setNo()で自動設定されるため、元の直接アクセスは不要
        // kodai.department = Integer.parseInt("" + kodai.no.charAt(3)); // 削除

        showGrade(kodai);
    }

    // 以前のSetGradeSampleのshowGradeメソッド（CitStudent2のゲッターを使用するバージョン）
    public static void showGrade(CitStudent2 data) {
        String deptName = switch (data.getDepartment()) { // getDepartment()を使用
            case 1 -> "情報工学科";
            case 2 -> "認知情報科学科";
            case 3 -> "高度応用情報科学科";
            default -> "不明な学科";
        };
        String rank = switch (data.getGrade()) {
            case 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100 -> "S";
            case 80, 81, 82, 83, 84, 85, 86, 87, 88, 89 -> "A";
            case 70, 71, 72, 73, 74, 75, 76, 77, 78, 79 -> "B";
            case 60, 61, 62, 63, 64, 65, 66, 67, 68, 69 -> "C";
            default -> "D";
        };
        System.out.println("No: " + data.getNo() + " " + data.getName() + " " + data.getGrade() + "点 " + deptName + " " + rank);
    }
}