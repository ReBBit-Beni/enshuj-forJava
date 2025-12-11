package enshu11a;

import java.awt.*;
import java.awt.geom.*;

/**
 * 四角形（矩形）を描画するクラス。
 * Figure クラスを継承し、Rectangle2D を使用して描画します。
 */
public class Rect extends Figure {
    
    // シリアライズ対応のため ID を設定（省略時はコンパイラが警告を出します）
    private static final long serialVersionUID = 1L;

    // 描画に使用する矩形オブジェクト
    // 描画時に一時的に使用するオブジェクトなので、メンバー変数である必要は低いですが、残しておきます。
    Rectangle2D f; 

    /**
     * 描画のための処理を行います。
     * setWHで決定された左上隅 (x, y) と正の幅 (w)・高さ (h) を基に
     * 四角形を描画します。
     *
     * @param g Graphics2D オブジェクト
     */
    @Override
    protected void draw(Graphics2D g) {
        // ★修正点: setWHで計算された x, y, w, h をそのまま使用する
        // x: 左上のX座標
        // y: 左上のY座標
        // w: 幅 (正の値)
        // h: 高さ (正の値)
        f = new Rectangle2D.Double(x, y, w, h);

        // 線の太さを 2.0f に設定
        g.setStroke(new BasicStroke(2.0f, BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND));
        
        // 描画実行
        g.draw(f); // アウトラインを描画
    }
    
    /**
     * PaintCanvasから相対変位を受け取り、長方形の左上座標とサイズを計算する。
     * どの方向へドラッグされても正しく描画できるようにする。
     * (このロジックは Figureクラスの setWH 処理を前提としているか、
     * Coord クラスのメソッドをオーバーライドしている可能性があります。
     * ここでは Rect 専用に、以前の会話で作成したロジックを再実装します。)
     */
    @Override
    public void setWH(double currentW, double currentH) {
        // 描画開始時（マウスプレス時）の座標を保持
        final double startX = super.x;
        final double startY = super.y;

        // 1. 新しい左上のX座標 (this.x) を決定: 開始点と終了点 (startX + currentW) のうち小さい方
        super.x = Math.min(startX, startX + currentW);

        // 2. 新しい左上のY座標 (this.y) を決定: 開始点と終了点 (startY + currentH) のうち小さい方
        super.y = Math.min(startY, startY + currentH);

        // 3. 幅 (this.w) と高さ (this.h) を絶対値（常に正の値）にする
        this.w = Math.abs(currentW);
        this.h = Math.abs(currentH);
    }
}