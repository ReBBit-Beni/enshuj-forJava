package enshu10new;

import java.awt.Color;
import java.awt.Graphics2D;

public class Figure extends Coord {
    // メンバー変数
    Color color; // 図形の色
    double w; // 図形の幅、またはX軸方向の相対移動量
    double h; // 図形の高さ、またはY軸方向の相対移動量
    Figure() {
        // Coord() のコンストラクタが暗黙的に呼ばれ、x, y は (0, 0) に初期化される
        color = Color.BLACK;
    }
    public void setWH(double w, double h) {
        this.w = w;
        this.h = h;
    }
    public void paint(Graphics2D g) {
        // 基底クラスでは何もしない (具象クラスで実装される)
    }
}