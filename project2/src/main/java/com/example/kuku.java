package com.example;
public class kuku {
    public static void main(String[] args) {
        int x, y, v;
        // 上側の見出し
        System.out.print("   ");
        for (x = 1; x <= 15; x++) {
            printHex2(x);
        }
        System.out.println();
        // 本体
        for (y = 1; y <= 15; y++) {
            printHex2(y);
            for (x = 1; x <= 15; x++) {
                v = x * y;
                printHex2(v, 3);
                System.out.print(" ");
            }
            System.out.println();
        }
    }
    // 0〜15 → 0〜f を表示する
    public static void printHex(int v) {
        if (v < 10)
            System.out.print(v);
        else
            System.out.print((char) ('a' + v - 10));
    }
    // 2桁16進数で表示（上位が0でも0を表示）
    public static void printHex2(int v) {
        int w;
        w = v / 16;
        if (w != 0)
            printHex(w);
        else
            System.out.print("0");
        printHex(v % 16);
        System.out.print(" ");
    }
    // 任意桁数の16進数で表示（上位が0でも0を表示）
    public static void printHex2(int v, int n) { // v は表示する値，n は桁数
        int i, x = 1, w;
        // 割るための数の算出 (最初は 16 の (n-1) 乗)
        for (i = 1; i < n; i++) x = x * 16;
        //-System.out.println("[x␣=␣" + x + "]");
        for (i = 0; i < n; i++) {
            w = v / x; // 最上位の値
            v = v % x; // 下位表示のため余りを計算
            printHex(w);
            x = x / 16; // 次は1桁小さい数で割る
        }
    }
}