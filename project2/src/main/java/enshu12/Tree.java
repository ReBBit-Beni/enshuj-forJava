package enshu12;

import processing.core.PApplet;

public class Tree extends Turtle {

    public void settings() {
        size(400, 400);
    }

    public void draw() {
        background(0);
        stroke(255);

        reset();
        move(0, 150);         // 地面からスタート
        tree2(120, 6);        // 生い茂った木を描く

        // tree(150, 5);      // シンプルな二分木（必要に応じて切り替え）
        // tree3(300, 3);     // 非対称な木（必要に応じて切り替え）
    }

    /** シンプルな二分木 */
    void tree(float length, int step) {
        if (step == 0) {
            forward(length);
        } else {
            forward(length);
            push();
            right(deg30);
            tree(length * 0.5f, step - 1);
            pop();
            push();
            left(deg30);
            tree(length * 0.5f, step - 1);
            pop();
        }
    }

    /** 非対称な木 */
    void tree3(float length, int step) {
        if (step == 0) {
            forward(length);
        } else {
            forward(length * 0.5f);
            push();
            right(deg60);
            tree3(length * 0.3f, step - 1);
            pop();
            forward(length * 0.3f);
            push();
            left(deg60);
            tree3(length * 0.2f, step - 1);
            pop();
            forward(length * 0.2f);
        }
    }

    /** より生い茂った木を描く再帰メソッド */
    void tree2(float length, int step) {
        if (step == 0) {
            forward(length);
        } else {
            forward(length * 0.5f); // 幹の下半分

            // 右の枝
            push();
            right(deg30);
            tree2(length * 0.6f, step - 1);
            pop();

            // 中央の枝（少し上向き）
            push();
            tree2(length * 0.5f, step - 1);
            pop();

            // 左の枝
            push();
            left(deg30);
            tree2(length * 0.6f, step - 1);
            pop();

            forward(length * 0.5f); // 幹の上半分
        }
    }

    public static void main(String args[]) {
        PApplet.main(Tree.class.getName());
    }
}

