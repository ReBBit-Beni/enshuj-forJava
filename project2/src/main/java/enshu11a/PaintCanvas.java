package enshu11a;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

// 描画キャンバス
public class PaintCanvas extends JPanel implements MouseListener,MouseMotionListener,ActionListener {
    
    // メンバー変数
    Figure obj = null;
    double x, y;
    Paint4 p4;
    ArrayList<Figure> objList;
    int mode = 0;

    // ピクセルアートモード用の設定
    private final int GRID_SIZE = 20;
    private boolean isPixelMode = false;
    
    PaintCanvas(Paint4 p, String fname) {
        this.p4 = p;
        objList = new ArrayList<>();
        
        addMouseListener(this);
        addMouseMotionListener(this);
        
        if (fname != null) {
            load(fname);
        }
        
        updateColorDisplay();
    }

    // --- マウスイベント ---
    @Override
    public void mousePressed(MouseEvent e) {
        Point2D p = e.getPoint();
        x = snapToGrid(p.getX());
        y = snapToGrid(p.getY());
        
        mode = 0;
        obj = null;

        // 図形モードの判定
        if (p4.r1.isSelected()) {
            mode = 1;
            obj = new Dot();
        } else if (p4.r2.isSelected()) {
            mode = 2;
            obj = new Circle();
        } else if (p4.r3.isSelected()) {
            mode = 2;
            obj = new Rect();
        } else if (p4.r4.isSelected()) {
            mode = 2;
            obj = new Line();
        } else if (p4.r5.isSelected()) {
            mode = 3;
            String input = JOptionPane.showInputDialog(this, "テキストを入力してください:", "テキスト入力", JOptionPane.PLAIN_MESSAGE);
            
            if (input != null && !input.trim().isEmpty()) {
                obj = new Text(input);
            }
        }
        
        if (obj != null) {
            obj.moveto(x, y);
            obj.setColor(getCurrentColor());

            if (mode == 3) {
                objList.add(obj);
                obj = null;
                mode = 0;
            }
        }
        
        repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        Point2D p = e.getPoint();
        
        double currentX = snapToGrid(p.getX());
        double currentY = snapToGrid(p.getY());
        
        if (obj != null) {
            if (mode == 1) { // Dot (ドット絵の塗りつぶし)
                
                // ★修正: 座標が変わったとき (XまたはYが異なる) のみ描画
                if (currentX != this.x || currentY != this.y) {
                    this.x = currentX;
                    this.y = currentY;
                    
                    obj.moveto(this.x, this.y);
                    objList.add(obj);
                    
                    // 次のドットオブジェクトを作成
                    obj = new Dot();
                    obj.setColor(getCurrentColor());
                    obj.moveto(this.x, this.y);
                }
            } else if (mode == 2) {
                obj.setWH(currentX - this.x, currentY - this.y);
            }
        }
        repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Point2D p = e.getPoint();
        x = snapToGrid(p.getX());
        y = snapToGrid(p.getY());

        if (obj != null) {
            if (mode == 1) {
                obj.moveto(x, y);
            } else if (mode == 2) {
                obj.setWH(x - obj.x, y - obj.y);
            }
            
            if (mode == 1 || mode == 2) {
                objList.add(obj);
                obj = null;
            }
        }
        
        mode = 0;
        repaint();
    }
    //
    @Override public void mouseEntered(MouseEvent e) {}
    @Override public void mouseExited(MouseEvent e) {}
    @Override public void mouseClicked(MouseEvent e) {}
    @Override public void mouseMoved(MouseEvent e) {}

    // --- ボタンアクション ---
    @Override
    public void actionPerformed(ActionEvent e) {
        // ... (クリア、終了、保存、読込、Undoの処理は省略) ...
        if (e.getSource() == p4.endBtn) {
            save("paint.dat");
            System.exit(0);
        } else if (e.getSource() == p4.clearBtn) {
            objList.clear();
            repaint();
        } else if (e.getSource() == p4.saveBtn) {
            JFileChooser fileChooser = new JFileChooser();
            if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
                save(fileChooser.getSelectedFile().getAbsolutePath());
            }
        } else if (e.getSource() == p4.loadBtn) {
            JFileChooser fileChooser = new JFileChooser();
            if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                load(fileChooser.getSelectedFile().getAbsolutePath());
            }
        } else if (e.getSource() == p4.undoBtn) {
            if (!objList.isEmpty()) {
                objList.remove(objList.size() - 1);
            }
            repaint();
        }
        // ピクセルアートモードの切り替え処理
        else if (e.getSource() == p4.pixelModeCbx) {
            isPixelMode = p4.pixelModeCbx.isSelected();
            repaint();
        }
        // カスタム色ピッカーボタンの処理
        else if (e.getSource() == p4.pickColorBtn) {
            Color newColor = JColorChooser.showDialog(this, "カスタム色の選択", p4.selectedCustomColor);
            if (newColor != null) {
                p4.selectedCustomColor = newColor;
                p4.colorCustomR.setSelected(true); // ★重要: カスタム色を選択状態にする
            }
            updateColorDisplay(); // ★追加: UI表示を更新
        }
        // ★修正: 色ラジオボタンがクリックされた場合の処理
        else if (e.getSource() == p4.colorR || e.getSource() == p4.colorB || e.getSource() == p4.colorG || e.getSource() == p4.colorCustomR) {
            updateColorDisplay(); // UI表示を更新
        }
    }
    
    // --- 描画処理 ---
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        
        // 確定済みオブジェクトの描画と現在作成中の要素の描画は省略
        for (Figure figure : objList) {
            figure.paint(g2);
        }
        if (obj != null && mode > 1) {
            obj.paint(g2);
        }
        
        // グリッド線の描画
        if (isPixelMode) {
            g2.setColor(Color.LIGHT_GRAY);
            g2.setStroke(new BasicStroke(1.0f));

            int width = getWidth();
            int height = getHeight();

            for (int x = 0; x < width; x += GRID_SIZE) {
                g2.drawLine(x, 0, x, height);
            }

            for (int y = 0; y < height; y += GRID_SIZE) {
                g2.drawLine(0, y, width, y);
            }
        }
    }

    // --- ヘルパーメソッド ---
    private Color getCurrentColor() {
        if (p4.colorR.isSelected()) {
            return Color.RED;
        } else if (p4.colorB.isSelected()) {
            return Color.BLUE;
        } else if (p4.colorG.isSelected()) {
            return Color.GREEN;
        } else if (p4.colorCustomR.isSelected()) {
            return p4.selectedCustomColor;
        }
        return Color.BLACK;
    }

    private double snapToGrid(double coord) {
        if (isPixelMode) {
            return Math.round(coord / GRID_SIZE) * GRID_SIZE;
        }
        return coord;
    }
    
    // ★追加: UIの文字色・背景色を更新するロジック
    private Color getContrastColor(Color color) {
        double y = (0.299 * color.getRed() + 0.587 * color.getGreen() + 0.114 * color.getBlue()) / 255;
        return (y > 0.5) ? Color.BLACK : Color.WHITE;
    }
    
    private void updateColorDisplay() {
        // 1. すべてのボタンの表示をリセット
        p4.colorR.setForeground(Color.BLACK);
        p4.colorB.setForeground(Color.BLACK);
        p4.colorG.setForeground(Color.BLACK);
        p4.colorCustomR.setForeground(Color.BLACK);
        p4.pickColorBtn.setForeground(Color.BLACK);
        p4.pickColorBtn.setBackground(null);

        // 2. 選択されているボタンの文字色をその色自身に変更
        if (p4.colorR.isSelected()) {
            p4.colorR.setForeground(Color.RED);
        } else if (p4.colorB.isSelected()) {
            p4.colorB.setForeground(Color.BLUE);
        } else if (p4.colorG.isSelected()) {
            p4.colorG.setForeground(Color.GREEN);
        } else if (p4.colorCustomR.isSelected()) {
            Color customColor = p4.selectedCustomColor;
            p4.colorCustomR.setForeground(customColor);
            
            p4.pickColorBtn.setForeground(getContrastColor(customColor));
            p4.pickColorBtn.setBackground(customColor);
        }
        
        p4.repaint();
    }

    // --- ファイル入出力（シリアライズ） ---
    // ... (save/load メソッドは省略) ...
    public void save(String fname) {
        try {
            FileOutputStream fos = new FileOutputStream(fname);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(objList);
            oos.close();
            fos.close();
        } catch (IOException e) {
            System.err.println("ファイルの保存中にエラーが発生しました: " + e.getMessage());
        }
    }
    @SuppressWarnings("unchecked")
    public void load(String fname) {
        try {
            FileInputStream fis = new FileInputStream(fname);
            ObjectInputStream ois = new ObjectInputStream(fis);
            objList = (ArrayList<Figure>) ois.readObject();
            ois.close();
            fis.close();
            JOptionPane.showMessageDialog(this, "ファイル「" + fname + "」を読み込みました。", "読込成功", JOptionPane.INFORMATION_MESSAGE);
        } catch (java.io.FileNotFoundException e) {
            JOptionPane.showMessageDialog(this, "ファイルが見つかりません: " + fname + "\n(先に保存してください)", "読込エラー", JOptionPane.ERROR_MESSAGE);
            System.err.println("ファイルの読み込み中にエラーが発生しました: " + e.getMessage());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "ファイルの読み込み中にエラーが発生しました。\nファイルが破損している可能性があります。", "読込エラー", JOptionPane.ERROR_MESSAGE);
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(this, "読み込んだファイルに不明なクラスが含まれています。\nクラスパスを確認してください。", "読込エラー", JOptionPane.ERROR_MESSAGE);
        }
        repaint();
    }
}