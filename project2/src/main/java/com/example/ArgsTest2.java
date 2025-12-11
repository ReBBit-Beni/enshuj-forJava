package com.example;

public class ArgsTest2 {
    public static void main(String[] args) {
        String topArg = ""; // 先頭オプション

        if (args.length >= 1) {
            topArg = args[0];
        }

        // 先頭オプションの長さだけ繰り返し
        for (int i = 0; i < topArg.length(); i++) {
            System.out.print("[" + topArg.charAt(i) + "]"); // i 番目の文字を表示
        }
        System.out.println();

        // オプションの数だけ繰り返し
        for (int i = 0; i < args.length; i++) {
            System.out.print("arg " + i + " : " + args[i]); // i 番目を表示

            if (args[i].equals(topArg)) { // 先頭のオプションと同じならば
                System.out.println(" same"); // "same"と表示
            } else { // 同じでないならば
                System.out.println(" diff"); // "diff"と表示
            }
        }
    }
}

