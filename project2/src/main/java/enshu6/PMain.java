package enshu6;
import java.util.ArrayList;

import processing.core.PApplet;

public class PMain extends PApplet {

    ArrayList<Maru> marus = new ArrayList<>(); // Maru のインスタンスをリストで管理

    public void settings() {
        size(500, 500);
    }

    public void setup() {
        for (int i = 0; i < 10; i++) { // 10個の Maru を生成
            float x = random(width);  // 画面内のランダムな位置
            float y = random(height);
            float r = (float)Math.random() * 30 + 10; // 10～40のランダムな半径
            float vx = random(-3, 3); // -3～3のランダムな速度
            float vy = random(-3, 3);
            marus.add(new Maru(x, y, r, vx, vy));
        }
    }

    public void draw() {
        background(0);
        fill(255);
        for (Maru maru : marus) {
            maru.update(width, height); // 位置を更新
            circle(maru.x, maru.y, maru.r * 2); // 円を描画（直径が r*2）
        }
    }

    public static void main(String args[]) {
        PApplet.main(PMain.class.getName());
    }
}



