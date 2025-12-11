package enshu12;

import processing.core.PApplet;

/** タートルグラフィクスを描くために用いるクラス */
public class Turtle extends PApplet{

    // finalを付けると変数の値は変更できなくなる 定数を表現するのに使う
    final float deg30 = (float)(Math.PI / 6);
    final float deg45 = (float)(Math.PI / 4);
    final float deg60 = (float)(Math.PI / 3);
    final float deg90 = (float)(Math.PI / 2);

    /** 亀をウィンドウ中心に置く(上向きに置く) */
    void reset() {
        resetMatrix(); // translate()やrotate()で変更した座標軸を初期値に戻す
        translate(width/2, height/2); //亀をウィンドウ中心に置く
    }
    
    /** 前方にn進む */
    void forward(float n) {
        line(0, 0, 0, -n); // 「前方に進む」=「上方向に進む」なので-n
        translate(0, -n);
    }

    /** 右に回転する */
    void right(float rad) {
        rotate(rad);
    }

    /** 左に回転する */
    void left(float rad) {
        rotate(-rad);
    }

    /**
     * 亀が向いている方向を基準に，右方向にright, 下方向(後方)にdown移動する
     * 線は描かない 亀の向きは変えない
     */
    void move(float right, float down) {
        translate(right, down);
    }

}
