package com.example;

public class ArgsTest3 {
    public static void main(String[] args) {
        if (args.length == 0) return;

        char firstChar = args[0].charAt(0); // 最初のオプションの1文字目

        for (int i = 0; i < args.length; i++) {
            String arg = args[i];
            for (int j = 0; j < arg.length(); j++) {
                char c = arg.charAt(j);
                if (c != firstChar) {
                    System.out.print(c);
                }
            }
            System.out.print(" ");
        }
        System.out.println();
    }
}
