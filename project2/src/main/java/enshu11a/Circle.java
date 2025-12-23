package enshu11a;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

public class Circle extends Figure {
    Ellipse2D f;
    @Override
    protected void draw(Graphics2D g) {
        double radius = Math.sqrt(w * w + h * h);
        f = new Ellipse2D.Double(x - radius, y - radius, radius * 2, radius * 2);
        g.setStroke(new BasicStroke(2.0f, BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND));
        g.draw(f);
    }
}