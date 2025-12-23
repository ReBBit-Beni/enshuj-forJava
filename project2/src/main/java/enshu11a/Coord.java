package enshu11a;

import java.io.Serializable;

public class Coord implements Serializable {
    // メンバー変数
    double x; // X座標
    double y; // Y座標
    Coord() {
        x = 0;
        y = 0;
    }
    public void move(double dx, double dy) {
        x += dx;
        y += dy;
    }
    public void moveto(double x, double y) {
        this.x = x;
        this.y = y;
    }
}
