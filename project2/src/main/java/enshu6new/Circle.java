package enshu6new;

import java.awt.*;
import java.awt.geom.*;

public class Circle extends Coord { // Coord クラスを継承
    Color color;
    double size;

    public Circle(){
        // 親クラス（Coord）のコンストラクタが先に呼ばれ、x, y が初期化される
        color = Color.BLACK;
        size = 10.0;
    }

    @Override
    public void paint(Graphics2D g){ // 描画のための処理
        // 中心座標 (x, y) から size の半分の距離を引いて楕円の左上隅を計算
        Ellipse2D f = new Ellipse2D.Double(x - size / 2, y - size / 2, size, size);
        
        // 描画設定（線の太さ、形状）
        g.setStroke(new BasicStroke(2.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        
        // 描画色
        g.setPaint(color);
        
        // 円の描画 (ここでは線画)
        g.draw(f); // g.fill(f);
    }

    @Override
    public void move(double dx, double dy){ // @Override で明示。
      x -= dx; // Coord.java の move メソッドと正負が逆になっている（逆移動）。
      y -= dy; // Coord.java の move メソッドと正負が逆になっている（逆移動）。
    }
}
