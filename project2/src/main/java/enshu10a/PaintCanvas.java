package enshu10a;

import javax.swing.*;
import java.awt.*;

public class PaintCanvas extends JPanel {
  @Override protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D)g;
    
    g2.setStroke(new BasicStroke(2.0f, BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND));
    g2.setPaint(Color.red);
    g2.drawString("Hello Java!", 70, 120);
    g2.drawRect(50, 50, 100, 100);
    }
}