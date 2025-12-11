package enshu12;

import processing.core.PApplet;

public class TurtleSample extends Turtle{ // このクラスはクラスTurtleのサブクラス

    public void settings() {
        size(400, 400);
    }

    public void draw() {
        background(0);
        stroke(255);

        reset();        // (0)ウィンドウ中心に亀を置く
        forward(50);  // (1)50進む
        right(deg45);   // (2)45度右回転する
        forward(50);  // (3)50進む
        left(deg45);    // (4)45度左回転する
        forward(100); // (5)100進む

        reset();
        left(deg90);    // (6)90度左回転する
        forward(150); // (7)150進む

        //reset();
        move(50, 100); // (8)右に50, 下に100移動する(線は描かない)
        forward(100);           // (9)100進む
    }

    public static void main(String args[]) {
        PApplet.main(TurtleSample.class.getName()); // Processingの動作を開始する
    }
}