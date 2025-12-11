package enshu7;

import processing.core.PApplet;

public class Maru7 {
    float offsetX, offsetY, x, y, r;

    public Maru7(float offsetX, float offsetY, float r) {
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.r = r;
    }

    public void update(float mx, float my, float squareSize) {
        // マウスカーソルを中心とした正方形の4隅に配置
        x = mx + offsetX * squareSize / 3;
        y = my + offsetY * squareSize / 3;
    }

    public void display(PApplet p) {
        p.circle(x, y, r * 3);
    }
}


