package enshu10new;

import javax.swing.*;
import java.awt.*;

public class Paint3 extends JFrame {
    // メンバー変数（インスタンス変数）の宣言
    JButton clearBtn;
    JButton endBtn;
    ButtonGroup bg;
    JRadioButton r1, r2, r3, r4;

    public static void main(String[] args) {
        // メインウィンドウ（JFrame）の初期化
        Paint3 p = new Paint3();
        PaintCanvas canvas;
        p.setTitle("Paint3");

        // キャンバス（描画エリア）の初期化
        canvas = new PaintCanvas(p);
        canvas.setBackground(Color.WHITE);

        // ツールバー（JToolBar）の作成と設定
        JToolBar toolBar = new JToolBar(JToolBar.VERTICAL);
        toolBar.setFloatable(false); // ツールバーのドラッグ移動を不可にする

        // ラジオボタンの作成と設定
        p.r1 = new JRadioButton("丸", true); // 初期選択
        p.r2 = new JRadioButton("円");
        p.r3 = new JRadioButton("四角");
        p.r4 = new JRadioButton("線");

        // ButtonGroupにラジオボタンを追加して、排他的に選択できるようにする
        p.bg = new ButtonGroup();
        p.bg.add(p.r1);
        p.bg.add(p.r2);
        p.bg.add(p.r3);
        p.bg.add(p.r4);

        // ボタンの作成
        p.clearBtn = new JButton("消去");
        p.endBtn = new JButton("終了");

        // ボタンにアクションリスナーとしてキャンバスを設定
        p.clearBtn.addActionListener(canvas);
        p.endBtn.addActionListener(canvas);

        // ツールバーにコンポーネントを追加
        toolBar.add(p.r1);
        toolBar.add(p.r2);
        toolBar.add(p.r3);
        toolBar.add(p.r4);
        toolBar.addSeparator(new Dimension(0, 200)); // 区切り線（スペース）
        toolBar.add(p.clearBtn);
        toolBar.add(p.endBtn);

        // メインウィンドウにコンポーネントを配置
        // ツールバーを東側 (右側) に配置
        p.getContentPane().add(toolBar, BorderLayout.EAST);
        // キャンバスを中心 (残り) に配置
        p.getContentPane().add(canvas, BorderLayout.CENTER);

        // ウィンドウの設定
        p.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        p.setSize(900, 600);
        p.setLocationRelativeTo(null); // 画面中央に表示
        p.setVisible(true); // ウィンドウを表示
    }
}