package enshu7new;

import javax.swing.*;
import java.awt.*;

public class Paint2 extends JFrame {
    
    public static void main(String[] args) {
        Paint2 p = new Paint2();
        PaintCanvas canvas; // PaintCanvasのインスタンスを保持
        
        p.setTitle("Paint2");
        
        // canvas
        canvas = new PaintCanvas();
        canvas.setBackground(Color.WHITE);
        
        // PaintCanvasをウィンドウの中央に配置
        p.getContentPane().add(canvas, BorderLayout.CENTER);
        p.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        p.setSize(900, 600);
        p.setLocationRelativeTo(null);
        p.setVisible(true);
    }
}
/*
 * --- 配布プログラムからの変更点 ---
 * 【課題】本クラス自体に変更はありません。
 * * --- このプログラムで達成できたこと ---
 * アプリケーションのエントリポイントとして機能し、描画および制御の核となる PaintCanvas を実行可能なウィンドウとしてユーザーに提供しています。メインウィンドウ（JFrame）の作成と設定を担当します。
 */