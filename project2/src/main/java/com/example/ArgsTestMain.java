package com.example;

public class ArgsTestMain {
    public static void main(String[] args){
        int i = 60;
        ArgsTest6 kodai = new ArgsTest6(); // 1回目 (count = 1)
        
        // i を引数にして呼び出し
        ArgsTest6.setGrade(kodai, i); // 2回目 (setGrade内で new ArgsTest6() で count = 2)
        System.out.println("1: grade = " + kodai.grade + " i = " + i + " count = " + ArgsTest6.count); // (行 8)
        
        // 値を直接引数にして呼び出し
        ArgsTest6.setGrade(kodai, 70); // 3回目 (setGrade内で new ArgsTest6() で count = 3)
        System.out.println("2: grade = " + kodai.grade + " i = " + i + " count = " + ArgsTest6.count); // (行 10)

        // ⭐ 追加した処理 (元の行 9, 10 と同様)
        ArgsTest6.setGrade(kodai, 80); // 4回目 (setGrade内で new ArgsTest6() で count = 4)
        System.out.println("3: grade = " + kodai.grade + " i = " + i + " count = " + ArgsTest6.count); // (行 12)
    }
}