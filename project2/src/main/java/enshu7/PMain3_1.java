package enshu7;

import java.util.ArrayList;

import processing.core.PApplet;

public class PMain3_1 extends PApplet {

    ArrayList<Maru7> marus = new ArrayList<>();
    float squareSize = 40; // 正方形の一辺の長さ

    public void settings() {
        size(500, 500);
    }

    public void setup() {
        float r = 10; // 円の半径
        // カーソルを中心とした正方形の4隅に配置
        marus.add(new Maru7(-2, -2, r)); // 左上
        marus.add(new Maru7(2, -2, r));  // 右上
        marus.add(new Maru7(-2, 2, r));  // 左下
        marus.add(new Maru7(2, 2, r));   // 右下
    }

    public void draw() {
        background(0);
        fill(255);

        for (Maru7 maru : marus) {
            // マウスカーソルの位置を基準に更新
            maru.update(mouseX, mouseY, squareSize);
            maru.display(this);
        }
    }

    public static void main(String[] args) {
        PApplet.main(PMain3_1.class.getName());
    }
}

