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
public class PaintCanvas extends JPanel implements MouseListener, MouseMotionListener, ActionListener {

    // メンバー変数
    Figure obj = null;
    double x, y;
    Paint4 p4;
    ArrayList<Figure> objList;
    
    int mode = 4;

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

        obj = null;
        Color currentColor = getCurrentColor();

        // 図形モードの判定
        if (p4.r1.isSelected()) {
            mode = 1; // Dot (ピクセルアート用)
        } else if (p4.r2.isSelected()) {
            mode = 2; // Circle
            obj = new Circle();
        } else if (p4.r3.isSelected()) {
            mode = 2; // Rect
            obj = new Rect();
        } else if (p4.r4.isSelected()) {
            mode = 2; // Line
            obj = new Line();
        } else if (p4.r5.isSelected()) {
            mode = 3; // Text
            String input = JOptionPane.showInputDialog(this, "テキストを入力してください:", "テキスト入力", JOptionPane.PLAIN_MESSAGE);
            if (input != null && !input.trim().isEmpty()) {
                obj = new Text(input);
            }
        } else if (p4.r6.isSelected()) {
            mode = 4; // ★筆モード
        }

        if (obj != null) {
            obj.moveto(x, y);
            obj.setColor(currentColor);

            if (mode == 3) { // Textはクリック地点で確定
                objList.add(obj);
                obj = null;
            }
        }

        repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        Point2D p = e.getPoint();
        double currentX = snapToGrid(p.getX());
        double currentY = snapToGrid(p.getY());
        Color currentColor = getCurrentColor();

        if (mode == 1) { // Dot (ピクセルアートの連続塗りつぶし)
            if (currentX != this.x || currentY != this.y) {
                this.x = currentX;
                this.y = currentY;
                Dot d = new Dot();
                d.moveto(this.x, this.y);
                d.setColor(currentColor);
                objList.add(d);
            }
        } else if (mode == 2) { // Circle, Rect, Line (ドラッグでサイズ変更)
            if (obj != null) {
                obj.setWH(currentX - this.x, currentY - this.y);
            }
        } else if (mode == 4) { // ★筆モード (短い線分の連続)
            if (currentX != this.x || currentY != this.y) {
                // 前の地点(x, y)から現在の地点(currentX, currentY)まで線を引く
                Line l = new Line();
                l.moveto(this.x, this.y);
                l.setWH(currentX - this.x, currentY - this.y);
                l.setColor(currentColor);
                objList.add(l);

                // 現在の地点を次の「開始地点」にする
                this.x = currentX;
                this.y = currentY;
            }
        }
        repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (obj != null && mode == 2) {
            Point2D p = e.getPoint();
            double lastX = snapToGrid(p.getX());
            double lastY = snapToGrid(p.getY());
            obj.setWH(lastX - this.x, lastY - this.y);
            objList.add(obj);
            obj = null;
        }
        repaint();
    }

    @Override public void mouseEntered(MouseEvent e) {}
    @Override public void mouseExited(MouseEvent e) {}
    @Override public void mouseClicked(MouseEvent e) {}
    @Override public void mouseMoved(MouseEvent e) {}

    // --- ボタンアクション ---
    @Override
    public void actionPerformed(ActionEvent e) {
        // モード切替ラジオボタンの処理
        if (e.getSource() == p4.r1) mode = 1;
        else if (e.getSource() == p4.r2) mode = 2;
        else if (e.getSource() == p4.r3) mode = 2;
        else if (e.getSource() == p4.r4) mode = 2;
        else if (e.getSource() == p4.r5) mode = 3;
        else if (e.getSource() == p4.r6) mode = 4;

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
        } else if (e.getSource() == p4.pixelModeCbx) {
            isPixelMode = p4.pixelModeCbx.isSelected();
            repaint();
        } else if (e.getSource() == p4.pickColorBtn) {
            Color newColor = JColorChooser.showDialog(this, "カスタム色の選択", p4.selectedCustomColor);
            if (newColor != null) {
                p4.selectedCustomColor = newColor;
                p4.colorCustomR.setSelected(true);
            }
            updateColorDisplay();
        } else if (e.getSource() == p4.colorR || e.getSource() == p4.colorB || e.getSource() == p4.colorG || e.getSource() == p4.colorCustomR) {
            updateColorDisplay();
        }
    }

    // --- 描画処理 ---
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        for (Figure figure : objList) {
            figure.paint(g2);
        }
        // mode 2 (図形) の作成中プレビュー
        if (obj != null && mode == 2) {
            obj.paint(g2);
        }

        if (isPixelMode) {
            g2.setColor(Color.LIGHT_GRAY);
            g2.setStroke(new BasicStroke(1.0f));
            int width = getWidth();
            int height = getHeight();
            for (int i = 0; i < width; i += GRID_SIZE) g2.drawLine(i, 0, i, height);
            for (int j = 0; j < height; j += GRID_SIZE) g2.drawLine(0, j, width, j);
        }
    }

    private Color getCurrentColor() {
        if (p4.colorR.isSelected()) return Color.RED;
        if (p4.colorB.isSelected()) return Color.BLUE;
        if (p4.colorG.isSelected()) return Color.GREEN;
        if (p4.colorCustomR.isSelected()) return p4.selectedCustomColor;
        return Color.BLACK;
    }

    private double snapToGrid(double coord) {
        if (isPixelMode) return Math.round(coord / GRID_SIZE) * GRID_SIZE;
        return coord;
    }

    private Color getContrastColor(Color color) {
        double y = (0.299 * color.getRed() + 0.587 * color.getGreen() + 0.114 * color.getBlue()) / 255;
        return (y > 0.5) ? Color.BLACK : Color.WHITE;
    }

    private void updateColorDisplay() {
        p4.colorR.setForeground(Color.BLACK);
        p4.colorB.setForeground(Color.BLACK);
        p4.colorG.setForeground(Color.BLACK);
        p4.colorCustomR.setForeground(Color.BLACK);
        p4.pickColorBtn.setForeground(Color.BLACK);
        p4.pickColorBtn.setBackground(null);

        if (p4.colorR.isSelected()) p4.colorR.setForeground(Color.RED);
        else if (p4.colorB.isSelected()) p4.colorB.setForeground(Color.BLUE);
        else if (p4.colorG.isSelected()) p4.colorG.setForeground(Color.GREEN);
        else if (p4.colorCustomR.isSelected()) {
            Color customColor = p4.selectedCustomColor;
            p4.colorCustomR.setForeground(customColor);
            p4.pickColorBtn.setForeground(getContrastColor(customColor));
            p4.pickColorBtn.setBackground(customColor);
        }
        p4.repaint();
    }

    public void save(String fname) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fname))) {
            oos.writeObject(objList);
        } catch (IOException e) {
            System.err.println("保存エラー: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public void load(String fname) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fname))) {
            objList = (ArrayList<Figure>) ois.readObject();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "読込エラー", "エラー", JOptionPane.ERROR_MESSAGE);
        }
        repaint();
    }
}