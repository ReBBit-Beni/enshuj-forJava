package enshu10new;

// import java.awt.*; // 描画に関連するクラスはここでは不要なためコメントアウト

/**
 * 2次元座標 (x, y) を管理するクラス。
 */
public class Coord {
    // メンバー変数
    double x; // X座標
    double y; // Y座標

    /**
     * デフォルトコンストラクタ。
     * 座標を (0, 0) に初期化します。
     */
    Coord() {
        x = 0;
        y = 0;
    }

    /**
     * 現在の座標から指定された量だけ相対的に移動します（加算）。
     *
     * @param dx X方向の移動量
     * @param dy Y方向の移動量
     */
    public void move(double dx, double dy) {
        x += dx;
        y += dy;
    }

    /**
     * 座標を指定された絶対位置に設定します。
     *
     * @param x 新しいX座標
     * @param y 新しいY座標
     */
    public void moveto(double x, double y) {
        this.x = x;
        this.y = y;
    }

    // public void paint(Graphics2D g){} // 描画処理用（ここではコメントアウト）
}