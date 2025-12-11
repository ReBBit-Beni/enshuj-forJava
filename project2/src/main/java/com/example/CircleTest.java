package com.example;

public class CircleTest {
    public static void main(String[] args){
        Circle c = new Circle(); // この時点では (0.0, 0.0)
        
        c.move(100.0, 100.0); // (100.0, 100.0) へ相対移動
        
        c.move(100.0, 100.0); // (200.0, 200.0) へ相対移動
        
        c.moveto(100.0, 100.0); // (100.0, 100.0) へ絶対移動

        c.print();
    }
}
