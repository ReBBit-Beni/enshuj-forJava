package enshu10new;

import java.awt.*;
import java.awt.geom.*;

/**
 * 点（丸）を描画するクラス。
 * Figure クラスを継承し、Ellipse2D を使用して描画します。
 */
public class Dot extends Figure {
    // メンバー変数
    private static final double DEFAULT_SIZE = 10.0; // 点の標準サイズを定数として定義
    double size = DEFAULT_SIZE;
    Ellipse2D f; // 描画に使用する楕円オブジェクト

    /**
     * 描画のための処理を行います。
     * 中心座標 (x, y) を基準に小さな円を描画します。
     *
     * @param g Graphics2D オブジェクト
     */
    @Override
    public void paint(Graphics2D g) {
        // 描画オブジェクトの定義
        // Ellipse2D.Double(左上のX座標, 左上のY座標, 幅, 高さ)
        // 中心 (x, y) からサイズ/2 だけずらした位置を左上とする
        f = new Ellipse2D.Double(x - size / 2, y - size / 2, size, size);

        // 描画設定
        // 線の太さを 2.0f に設定し、線の端と角を丸くする
        g.setStroke(new BasicStroke(2.0f, BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND));
        // 描画色を設定 (color は Figure クラスから継承されていると想定)
        g.setPaint(color);

        // 描画実行
        g.draw(f); // アウトラインを描画
        // g.fill(f); // 塗りつぶしを描画したい場合はこちらを使用
    }
}