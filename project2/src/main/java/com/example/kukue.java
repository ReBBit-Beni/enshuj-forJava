package com.example;

public class kukue {
  public static void main(String[] args){
    int x, y;

    for (y = 1; y < 10; y++) { // 行 (縦方向) の変数を変化させる
      for (x = 1; x < 10; x++) { // 列 (横方向) の変数を変化させる
        if (x * y < 10) { // 1桁の数の場合 前に空白を一つ表示
          System.out.print(" " + x * y + " ");
        } else { // 2桁の数の場合
          System.out.print(x * y + " ");
        }
      }
      System.out.println(); // 改行表示
    }
  }
}
