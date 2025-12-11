package enshu10a;

import java.awt.*;
import javax.swing.*;

public class SwingSample extends JFrame {
  public static void main(String[] args) {
    SwingSample p = new SwingSample();
    PaintCanvas canvas = new PaintCanvas();
    canvas.setBackground(Color.WHITE);
    p.getContentPane().add(canvas, BorderLayout.CENTER);
    p.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    p.setSize(200, 200);
    p.setLocationRelativeTo(null);
    p.setVisible(true);
  }
}