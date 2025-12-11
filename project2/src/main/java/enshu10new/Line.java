package enshu10new;

import java.awt.*;
import java.awt.geom.*;

public class Line extends Figure {
    Line2D f;

    @Override public void paint(Graphics2D g) {
        f = new Line2D.Double(x, y, x + w, y + h);
        g.setStroke(new BasicStroke(2.0f, BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND));
        g.setPaint(color);
        g.draw(f);
      }
}