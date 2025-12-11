package enshu6new;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;

// 描画キャンバス
public class PaintCanvas extends JPanel implements MouseListener, MouseMotionListener {
    
    Circle c = null; // 描画する Circle オブジェクト
    double x, y;     // 前回のマウス座標

    // コンストラクタ
    public PaintCanvas() { // public に修正 (外部からアクセス可能にするため)
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    @Override
    public void mousePressed(MouseEvent e) { // ボタン押下時
        Point2D p = e.getPoint();

        if (c == null) {
            // 初回押下時のみ新しい Circle を生成し、現在のマウス位置に移動
            c = new Circle();
            c.moveto(p.getX(), p.getY());
        }
        // 現在のマウス座標を保存
        x = p.getX();
        y = p.getY();
        repaint(); // 再描画要求
    }
    
    @Override
    public void mouseDragged(MouseEvent e) { // ボタン押して移動 (ドラッグ時)
        Point2D p = e.getPoint();

        if (c != null) {
            // 現在の座標と前回の座標の差分を移動量として Circle の move メソッドに渡す
            c.move(p.getX() - x, p.getY() - y);
        }
        // 現在のマウス座標を更新
        x = p.getX();
        y = p.getY();
        repaint(); // 再描画要求
    }
    
    // 未使用の MouseListener/MouseMotionListener メソッド
    @Override public void mouseReleased(MouseEvent e) { }
    @Override public void mouseEntered(MouseEvent e) { }
    @Override public void mouseExited(MouseEvent e) { }
    @Override public void mouseClicked(MouseEvent e) { }
    @Override public void mouseMoved(MouseEvent e) { }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // 親クラスの描画 (背景のクリアなど)
        Graphics2D g2 = (Graphics2D)g;
        if (c != null) {
            // Circle オブジェクトの描画メソッドを呼び出す
            c.paint(g2);
        }
    }
}
