package enshu10new;

import java.awt.*;
import java.awt.geom.*;

/**
 * 円を描画するクラス。
 * Figure クラスを継承し、Ellipse2D を使用して描画します。
 * 始点 (x, y) からマウスの現在位置までの距離を半径とします。
 */
public class Circle extends Figure {
    // メンバー変数
    Ellipse2D f; // 描画に使用する楕円オブジェクト

    /**
     * 描画のための処理を行います。
     * マウスのドラッグによって設定された幅 (w) と高さ (h) を基に半径を求め、
     * 始点 (x, y) を中心とする円を描画します。
     *
     * @param g Graphics2D オブジェクト
     */
    @Override
    public void paint(Graphics2D g) {
        // 半径の計算:
        // ドラッグの移動量 (w, h) を直角三角形の2辺とし、
        // その斜辺の長さ（始点から終点までの距離）を半径とする。
        double radius = Math.sqrt(w * w + h * h);
        
        // 描画オブジェクトの定義
        // Ellipse2D.Double(左上のX座標, 左上のY座標, 幅, 高さ)
        // 中心 (x, y) から半径だけずらした位置を左上とする (幅と高さは直径 2 * radius)
        f = new Ellipse2D.Double(x - radius, y - radius, radius * 2, radius * 2);

        // 描画設定
        // 線の太さを 2.0f に設定
        g.setStroke(new BasicStroke(2.0f, BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND));
        // 描画色を設定 (color は Figure クラスから継承されていると想定)
        g.setPaint(color);

        // 描画実行
        g.draw(f); // アウトラインを描画
        // g.fill(f); // 塗りつぶしを描画したい場合はこちらを使用
    }
}