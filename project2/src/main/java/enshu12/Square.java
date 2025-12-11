package enshu12;

import processing.core.PApplet;

public class Square extends Turtle {

    // 必要な角度定数を追加（Turtleにない場合）
    final float deg120 = (float)(2 * Math.PI / 3);       // 120度
    final float deg144 = (float)(4 * Math.PI / 5);       // 144度（星形用）

    public void settings() {
        size(500, 500);
    }

    public void draw() {
        background(0);
        stroke(255);

        // 正方形を右下に再帰的に描く
        /*
        reset();
        move(-150, -100); // 左上にずらしてスタート
        square2(100, 4);  // 最初のサイズ100、再帰4段階
        */

        // 以下は必要に応じて再表示
        /*
        // 正三角形
        reset();
        move(0, 50);
        triangle(60);

        // 正五角形
        reset();
        move(120, 50);
        pentagon(50);

        // 星形
        reset();
        move(0, -60);
        star(80);
        */

        reset();
        move(0, 80);
        triangle2(160, 5); // 最初のサイズ80、再帰4段階
        
    }

    /** 辺の長さlengthの正方形を描く */
    void square(float length) {
        for (int i = 0; i < 4; i++) {
            forward(length);
            right(deg90);
        }
    }

    /** 辺の長さlengthの正三角形を描く */
    void triangle(float length) {
        for (int i = 0; i < 3; i++) {
            forward(length);
            right(deg120);
        }
    }

    /** 辺の長さlengthの正五角形を描く */
    void pentagon(float length) {
        final float angle = TWO_PI / 5; // 72度
        for (int i = 0; i < 5; i++) {
            forward(length);
            right(angle);
        }
    }

    /** 辺の長さlengthの五芒星を描く */
    void star(float length) {
        for (int i = 0; i < 5; i++) {
            forward(length);
            right(deg144);
        }
    }

    /** 大きさの異なる複数の正方形を右下に再帰的に描く */
    void square2(float length, int step) {
        square(length);
        if (step > 0) {
            move(length + 10, length / 3); // 右下に移動
            square2(length / 2, step - 1);
        }
    }

    /** 同じ位置に縮小しながら重ねて描く再帰的な正三角形 */
    void triangle2(float length, int step) {
        triangle(length); // 現在の位置に正三角形を描く
        if (step > 0) {
            triangle2(length / 2, step - 1);
        }
    }
    
    public static void main(String args[]) {
        PApplet.main(Square.class.getName());
    }
}