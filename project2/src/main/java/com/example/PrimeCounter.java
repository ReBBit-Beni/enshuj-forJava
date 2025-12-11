package com.example;

public class PrimeCounter {
    public static void main(String[] args) {
        long start = 1_000_000_000L;
        long end = 2_000_000_000L;
        int count = 0;

        for (long i = start; i <= end; i++) {
            if (isPrime(i)) {
                count++;
            }
        }

        System.out.println("10億から20億までの素数の個数は: " + count);
    }

    // 素数判定（効率は低め）
    public static boolean isPrime(long num) {
        if (num < 2) return false;
        if (num == 2 || num == 3) return true;
        if (num % 2 == 0 || num % 3 == 0) return false;

        for (long i = 5; i * i <= num; i += 6) {
            if (num % i == 0 || num % (i + 2) == 0) return false;
        }
        return true;
    }
}