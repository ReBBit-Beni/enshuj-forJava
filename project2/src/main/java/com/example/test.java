package com.example;

public class test {
    public static void main(String[] args) {
        int x = 0, y = 10;

        if (x + y < 10) {
            x = 5;
        } else {
            if (x == 5) {
                x = 100;
            } else {
                x = 22;
            }
        }

        System.out.println("x = " + x);
    }
}


