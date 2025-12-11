package enshu11a;

import java.awt.Graphics2D;
import java.io.Serializable;

public class Text extends Figure implements Serializable {
    private static final long serialVersionUID = 1L;
    private String text;
    public Text(String text) {
        this.text = text;
    }
    public void setText(String text) {
        this.text = text;
    }
    @Override
    protected void draw(Graphics2D g) {
        g.drawString(this.text, (int) x, (int) y);
    }
}