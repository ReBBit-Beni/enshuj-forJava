package enshu10new;

import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;

// 描画キャンバス
public class PaintCanvas extends JPanel implements MouseListener,MouseMotionListener,ActionListener {
    // メンバー変数（インスタンス変数）の宣言
    Figure obj = null;             // 現在描画中の図形オブジェクト
    double x, y;                   // マウスの現在座標
    Paint3 p3;                     // 親フレーム（Paint3）への参照
    ArrayList<Figure> objList;     // 描画する確定済みオブジェクトを管理するリスト
    int mode = 0;                  // 描画モード (0:なし, 1: Dot/丸, 2: Circle/Rect/Line)

    // コンストラクタ
    PaintCanvas(Paint3 p) {
        this.p3 = p;
        objList = new ArrayList<>();
        // リスナーの登録
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    // --- MouseListener の実装 ---

    @Override
    public void mousePressed(MouseEvent e) {
        Point2D p = e.getPoint();
        x = p.getX();
        y = p.getY();

        // 選択されているラジオボタンに応じて描画モードと図形オブジェクトを決定
        if (p3.r1.isSelected()) {      // 丸 (Dot)
            mode = 1;
            obj = new Dot();
        } else if (p3.r2.isSelected()) { // 円 (Circle)
            mode = 2;
            obj = new Circle();
        } else if (p3.r3.isSelected()) { // 四角 (Rect)
            mode = 2;
            obj = new Rect();
        } else if (p3.r4.isSelected()) { // 線 (Line)
            mode = 2;
            obj = new Line();
        }

        // 図形オブジェクトの描画開始位置を設定
        if (obj != null) {
            obj.moveto(x, y);
        }
        repaint(); // 再描画を要求
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        Point2D p = e.getPoint();
        x = p.getX();
        y = p.getY();

        if (obj != null) {
            if (mode == 1) {
                // Dot/丸: 常に新しい位置に移動 (ドラッグで連続して描画)
                obj.moveto(x, y);
                // 描画確定リストに追加（連続描画のため）
                objList.add(obj);
                obj = new Dot(); // 新しい Dot オブジェクトを作成
                obj.moveto(x, y);
            } else if (mode == 2) {
                // Circle/Rect/Line: 幅と高さを設定 (始点からの相対座標)
                obj.setWH(x - obj.x, y - obj.y);
            }
        }
        repaint(); // 再描画を要求
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Point2D p = e.getPoint();
        x = p.getX();
        y = p.getY();

        if (obj != null) {
            if (mode == 1) {
                obj.moveto(x, y);
            } else if (mode == 2) {
                obj.setWH(x - obj.x, y - obj.y);
            }

            // 描画が確定した場合、リストに追加し、現在のオブジェクトをリセット
            if (mode >= 1) {
                objList.add(obj);
                obj = null;
            }
        }

        mode = 0; // モードをリセット
        repaint(); // 再描画を要求
    }

    // MouseListener/MouseMotionListenerの未使用メソッド
    @Override public void mouseEntered(MouseEvent e) {}
    @Override public void mouseExited(MouseEvent e) {}
    @Override public void mouseClicked(MouseEvent e) {}
    @Override public void mouseMoved(MouseEvent e) {}

    // --- ActionListener の実装（ボタン処理） ---

    @Override
    public void actionPerformed(ActionEvent e) { // ボタン操作の処理
        if (e.getSource() == p3.endBtn) {      // 終了ボタン
            System.exit(0);
        } else if (e.getSource() == p3.clearBtn) { // クリアボタン
            objList.clear(); // 描画リストをクリア
            repaint();       // 再描画を要求
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // 親クラスの描画処理を呼び出す（背景のクリア）
        Graphics2D g2 = (Graphics2D) g;
        Figure f;

        // 確定済みオブジェクトの描画
        for (Figure figure : objList) {
            figure.paint(g2);
        }
        if (obj != null && mode > 1) {
            obj.paint(g2);
        }
    }
}