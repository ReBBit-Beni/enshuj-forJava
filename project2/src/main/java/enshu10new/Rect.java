package enshu10new;

import java.awt.*;
import java.awt.geom.*;

/**
 * 四角形（矩形）を描画するクラス。
 * Figure クラスを継承し、Rectangle2D を使用して描画します。
 */
public class Rect extends Figure {
    // メンバー変数
    Rectangle2D f; // 描画に使用する矩形オブジェクト

    /**
     * 描画のための処理を行います。
     * 始点 (x, y) を中心として、ドラッグで設定された幅 (w) と高さ (h) を基に
     * 四角形を描画します。
     *
     * @param g Graphics2D オブジェクト
     */
    @Override
    public void paint(Graphics2D g) {
        // 描画オブジェクトの定義
        // Rectangle2D.Double(左上のX座標, 左上のY座標, 幅, 高さ)
        // 元のコードでは (x, y) が中心点となるように描画しているようです。
        // 左上のX座標: x - w
        // 左上のY座標: y - h
        // 幅: w * 2
        // 高さ: h * 2
        f = new Rectangle2D.Double(x - w, y - h, w * 2, h * 2);

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