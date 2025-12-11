package enshu7new;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PaintCanvas extends JPanel {
    
    private Figure currentDrawingObject = null;
    private final ArrayList<Figure> objList = new ArrayList<>();
    
    private static final int MAX_CIRCLES = 30;
    
    //アニメーション用のタイマー
    private Timer timer;

    public PaintCanvas() {
        MouseHandler handler = new MouseHandler();
        addMouseListener(handler);
        addMouseMotionListener(handler);
        
        //タイマーの設定と開始
        int delay = 10; // 10msごとに updateAnimation() を実行
        
        timer = new Timer(delay, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateAnimation();
            }
        });
        
        timer.start();
    }
    
    //アニメーション更新ロジック (縮小とクリーンアップ)
    private void updateAnimation() {
        // 1. すべての図形の縮小処理を更新し、寿命切れをチェック
        // リストを反復処理し、要素を安全に削除するためIteratorを使用します
        Iterator<Figure> iterator = objList.iterator();
        while (iterator.hasNext()) {
            Figure f = iterator.next();
            
            if (f instanceof Circle) {
                Circle c = (Circle)f;
                c.updateShrinking(); // 寿命とサイズを更新
                
                // 寿命が尽きた円をリストから削除
                if (!c.isAlive()) {
                    iterator.remove();
                    System.out.println("円が寿命により消去されました。");
                }
            }
        }
        repaint(); // 再描画
    }
    
    private class MouseHandler extends MouseAdapter {
        
        @Override
        public void mousePressed(MouseEvent e) {
            Point2D p = e.getPoint();
            currentDrawingObject = new Circle();
            currentDrawingObject.moveto(p.getX(), p.getY());
        }
        
        @Override
        public void mouseDragged(MouseEvent e) {
            if (currentDrawingObject != null) {
                Point2D p = e.getPoint();
                ((Circle)currentDrawingObject).setEnd(p.getX(), p.getY());
                repaint();
            }
        }
        
        @Override
        public void mouseReleased(MouseEvent e) {
            if (currentDrawingObject != null) {
                Point2D p = e.getPoint();
                ((Circle)currentDrawingObject).setEnd(p.getX(), p.getY());

                Circle newCircleB = (Circle)currentDrawingObject;

                //捕食ロジック (既存の円を逆順にチェック)
                for (int i = objList.size() - 1; i >= 0; i--) {
                    Figure existingFigure = objList.get(i);
                    
                    if (existingFigure instanceof Circle) {
                        Circle existingCircleA = (Circle)existingFigure;
                        
                        // 捕食条件の判定
                        boolean isBLarger = newCircleB.getSize() > existingCircleA.getSize();
                        boolean BContainsA = newCircleB.contains(existingCircleA);
                        
                        if (isBLarger && BContainsA) {
                            objList.remove(i);
                            // 捕食された円のサイズをコンソールに表示 (デバッグ補助)
                            System.out.println("円が捕食されました！ (消去された円のサイズ: " + String.format("%.2f", existingCircleA.getSize()) + ")");
                        }
                    }
                }

                // 新しい円を追加
                objList.add(currentDrawingObject);
                
                //30個制限ロジック (最大数を超えたら最も古い(index 0)を削除)
                if (objList.size() > MAX_CIRCLES) {
                    objList.remove(0);
                }
                
                // 円の総数をコンソールに表示
                System.out.println("円が追加されました。現在の円の総数: " + objList.size() + "個");

                currentDrawingObject = null;
                repaint();
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        
        // 既存の図形を描画
        for (Figure f : objList) {
            f.paint(g2);
        }
        
        // ドラッグ中の図形を描画
        if (currentDrawingObject != null) {
            currentDrawingObject.paint(g2);
        }
    }
}
/*
 * --- 配布プログラムからの変更点 ---
 * 1. アニメーション制御: TimerとupdateAnimation()メソッドを新規追加し、10msごとの描画更新基盤を構築しました。
 * 2. 【課題 3】最大描画数 30個の制限定数 MAX_CIRCLES を追加。
 * 3. 【課題 9】mouseReleased内で、新しい円が既存の円を捕食するロジック（サイズ比較と内包判定）を追加。
 * 4. 【課題 3】30個制限: objList.size()がMAX_CIRCLESを超えた場合、最も古い要素（objList.remove(0)）を削除するロジックを追加。
 * 5. 【課題 1】円の総数をコンソールに表示する System.out.println を mouseReleased に追加。
 * 6. 【課題 】updateAnimation内で、Iteratorを使用して寿命切れのCircleを安全に削除するクリーンアップ処理を追加。
 * * --- このプログラムで達成できたこと ---
 * 描画だけでなく、時間経過による動的なオブジェクトの寿命管理、安全なクリーンアップ、リソース制限、そして高度なユーザーインタラクション（捕食）を実現し、アプリケーションの核となる制御層として機能しています。
 */