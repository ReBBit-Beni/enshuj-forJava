package com.example;

public class Coord {
    double x, y;

    // コンストラクタ
    public Coord() {
        x = y = 10.0;
        System.out.println("Coord");
    }
    
    // 相対座標で移動
    public void move(double dx, double dy){
        x += dx;
        y += dy;
    }
    
    // 絶対座標で移動
    public void moveto(double x, double y){
        this.x = x;
        this.y = y;
    }
    
    // ⭐ 追加した print メソッド
    public void print(){
        System.out.println("座標: (x = " + x + ", y = " + y + ")");
    }
}
