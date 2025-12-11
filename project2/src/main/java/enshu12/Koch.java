package enshu12;

import processing.core.PApplet;

public class Koch extends Turtle {

    // 角度定数（Turtle クラスで定義されていない場合はここで定義）
    final float deg30 = PI / 6;
    final float deg60 = PI / 3;
    final float deg90 = PI / 2;

    public void settings() {
        size(500, 500);
    }

boolean useSharp = true; // ← ここを切り替える

    public void draw() {
        background(0);
        stroke(255);
        
        reset();
        move(-200, 50);
        right(deg90);
        if (useSharp) {
            sharpKoch(400, 3); // 鋭角なコッホ曲線
            } else {
                koch(400, 3); // 通常のコッホ曲線
        }
    }

    // 通常のコッホ曲線
    void koch(float length, int step) {
        if (step == 0) {
            forward(length);
        } else {
            koch(length / 3, step - 1);
            left(deg60);
            koch(length / 3, step - 1);
            right(deg60 * 2);
            koch(length / 3, step - 1);
            left(deg60);
            koch(length / 3, step - 1);
        }
    }

    // 鋭角なコッホ曲線
    void sharpKoch(float length, int step) {
        if (step == 0) {
            forward(length);
        } else {
            sharpKoch(length / 3, step - 1);
            left(deg30); // 鋭角に折れる
            sharpKoch(length / 3, step - 1);
            right(deg30 * 2); // より鋭く折り返す
            sharpKoch(length / 3, step - 1);
            left(deg30); // 元の方向に戻す
            sharpKoch(length / 3, step - 1);
        }
    }

    public static void main(String args[]) {
        PApplet.main(Koch.class.getName());
    }
}

