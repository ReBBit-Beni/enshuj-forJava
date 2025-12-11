package enshu11a;

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.Serializable;

public abstract class Figure extends Coord implements Serializable {
    
    private static final long serialVersionUID = 1L;
    private Color color;
    double w;
    double h;

    Figure() {
        this.color = Color.BLACK;
    }
    public void setColor(Color c) {
        this.color = c;
    }
    public Color getColor() {
        return this.color;
    }
    public void setWH(double w, double h) {
        this.w = w;
        this.h = h;
    }
    public final void paint(Graphics2D g) {
        g.setPaint(this.color);
        draw(g);
    }
    protected abstract void draw(Graphics2D g);
}