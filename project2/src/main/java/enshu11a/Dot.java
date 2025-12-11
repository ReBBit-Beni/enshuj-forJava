package enshu11a;

import java.awt.*;
import java.awt.geom.*;

public class Dot extends Figure {

    private static final long serialVersionUID = 1L;
    
    private final static double PIXEL_SIZE = 20.0;
    Rectangle2D f;

    @Override
    protected void draw(Graphics2D g) {
        f = new Rectangle2D.Double(x, y, PIXEL_SIZE, PIXEL_SIZE);
        
        g.fill(f);
    }
}
