package com.example;

public class kuku1 {
    public static void main(String[] args) {
        // 九九表の出力
        for (int y = 1; y < 10; y++) {
            for (int x = 1; x < 10; x++) {
                System.out.printf("%2d ", x * y); // 結果を整列して表示
            }
            System.out.println(); // 改行
        }
    }
}

