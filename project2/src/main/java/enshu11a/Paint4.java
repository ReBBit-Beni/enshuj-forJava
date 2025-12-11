package enshu11a;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JToolBar;

public class Paint4 extends JFrame {
    JButton clearBtn;
    JButton endBtn;
    JButton saveBtn;
    JButton loadBtn;
    JButton undoBtn;
    
    // 図形選択
    ButtonGroup bg;
    JRadioButton r1, r2, r3, r4, r5;
    
    // 色選択
    ButtonGroup colorBg;
    // エラーの原因となっていたコンポーネント
    JRadioButton colorR, colorB, colorG;
    JRadioButton colorCustomR;
    JButton pickColorBtn;
    public Color selectedCustomColor = Color.BLACK; // カスタム色を記憶
    
    // ピクセルモード
    JCheckBox pixelModeCbx;

    public static void main(String[] args) {
        Paint4 p = new Paint4();
        PaintCanvas canvas;
        String fname = null;
        if (args.length == 1) { fname = args[0]; }
        p.setTitle("Paint4");

        // --- 色選択ラジオボタンの設定（インスタンス生成） ---
        p.colorR = new JRadioButton("赤", true);
        p.colorB = new JRadioButton("青");
        p.colorG = new JRadioButton("緑");
        
        p.colorCustomR = new JRadioButton("カスタム");
        p.pickColorBtn = new JButton("色を選択");
        p.colorBg = new ButtonGroup();
        
        // --- 図形選択ラジオボタンの設定（インスタンス生成） ---
        p.r1 = new JRadioButton("ドット", true);
        p.r2 = new JRadioButton("円");
        p.r3 = new JRadioButton("四角");
        p.r4 = new JRadioButton("線");
        p.r5 = new JRadioButton("テキスト");
        p.bg = new ButtonGroup();

        // --- 操作ボタンの設定（インスタンス生成） ---
        p.clearBtn = new JButton("消去");
        p.endBtn = new JButton("終了");
        p.saveBtn = new JButton("保存");
        p.loadBtn = new JButton("読込");
        p.undoBtn = new JButton("Undo");
        p.pixelModeCbx = new JCheckBox("ピクセルアートモード", false);

        canvas = new PaintCanvas(p, fname);
        canvas.setBackground(Color.WHITE);

        // ツールバー（JToolBar）の作成と設定
        JToolBar toolBar = new JToolBar(JToolBar.VERTICAL);
        toolBar.setFloatable(false);

        // --- ButtonGroupへの追加とActionListenerの設定 ---
        
        // 色選択
        p.colorBg.add(p.colorR);
        p.colorBg.add(p.colorB);
        p.colorBg.add(p.colorG);
        p.colorBg.add(p.colorCustomR); // グループに追加

        p.colorR.addActionListener(canvas);
        p.colorB.addActionListener(canvas);
        p.colorG.addActionListener(canvas);
        p.colorCustomR.addActionListener(canvas);
        p.pickColorBtn.addActionListener(canvas);

        // 図形選択
        p.bg.add(p.r1);
        p.bg.add(p.r2);
        p.bg.add(p.r3);
        p.bg.add(p.r4);
        p.bg.add(p.r5);

        // 操作ボタン
        p.pixelModeCbx.addActionListener(canvas);
        p.clearBtn.addActionListener(canvas);
        p.endBtn.addActionListener(canvas);
        p.saveBtn.addActionListener(canvas);
        p.loadBtn.addActionListener(canvas);
        p.undoBtn.addActionListener(canvas);
        
        // 1. 図形選択 (r1-r5)
        toolBar.add(new JLabel(" 図形選択: "));
        toolBar.add(p.r1);
        toolBar.add(p.r2);
        toolBar.add(p.r3);
        toolBar.add(p.r4);
        toolBar.add(p.r5);
        toolBar.addSeparator();
        toolBar.add(p.pixelModeCbx);
        toolBar.addSeparator(new Dimension(0, 10));

        // 色選択 (colorR-colorG, カスタム色)
        toolBar.add(new JLabel(" 線の色: "));
        toolBar.add(p.colorR);
        toolBar.add(p.colorB);
        toolBar.add(p.colorG);
        toolBar.addSeparator(new Dimension(0, 10)); // 短い区切り線

        toolBar.add(p.colorCustomR);
        toolBar.add(p.pickColorBtn); 
        toolBar.addSeparator(new Dimension(0, 50));

        // 操作ボタン (保存、読込、クリア、Undo、終了)
        toolBar.add(p.saveBtn);
        toolBar.add(p.loadBtn);
        toolBar.addSeparator(new Dimension(0, 10));
        toolBar.add(p.clearBtn);
        toolBar.add(p.undoBtn);
        toolBar.addSeparator(new Dimension(0, 10));
        toolBar.add(p.endBtn);

        p.getContentPane().add(toolBar, BorderLayout.EAST);
        p.getContentPane().add(canvas, BorderLayout.CENTER);

        // ウィンドウの設定と表示
        p.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        p.setSize(900, 600);
        p.setLocationRelativeTo(null);
        p.setVisible(true);
    }
}
