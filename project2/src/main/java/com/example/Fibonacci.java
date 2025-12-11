package com.example;

public class Fibonacci {
    public static void main(String[] args) {
        int a = 0, b = 1;

        System.out.println("10,000までのフィボナッチ数列:");
        while (a <= 10000) {
            System.out.print(a + " ");
            int next = a + b;
            a = b;
            b = next;
        }
    }
}

