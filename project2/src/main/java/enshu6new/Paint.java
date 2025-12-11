package enshu6new;

import javax.swing.*;
import java.awt.*;

public class Paint extends JFrame {
    public static void main(String[] args) {
        Paint p = new Paint();
        PaintCanvas canvas;
        
        p.setTitle("Paint");
        
        // canvas の設定
        canvas = new PaintCanvas();
        canvas.setBackground(Color.WHITE);
        
        // JFrame の中央にキャンバスを追加
        p.getContentPane().add(canvas, BorderLayout.CENTER);
        
        // フレームの基本設定
        p.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        p.setSize(900, 600);
        p.setLocationRelativeTo(null); // 画面中央に配置
        p.setVisible(true);
    }
}
