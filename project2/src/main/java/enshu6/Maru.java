package enshu6;

public class Maru {
    float x, y, r, vx, vy;

    public Maru(float x, float y, float r, float vx, float vy) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.vx = vx;
        this.vy = vy;
    }

    public void update(int width, int height) {
        x += vx;
        y += vy;

        // 画面端で反射処理
        if (x - r < 0 || x + r > width) vx *= -1;
        if (y - r < 0 || y + r > height) vy *= -1;
    }
}

