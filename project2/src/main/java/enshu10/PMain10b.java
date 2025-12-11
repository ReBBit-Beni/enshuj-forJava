package enshu10;

import java.util.List;

import processing.core.PApplet;
import processing.core.PVector;

public class PMain10b extends PApplet{

    Sankaku sankaku1;
    Daikei daikei1;
    Chouhoukei chouhoukei1;

    public void settings() {
        size(500, 500);
    }

    public void setup() {
        sankaku1 = new Sankaku(100, 100, 20, 10, (float)(Math.PI / 6));
        daikei1 = new Daikei(250, 250, 40, 100, 60, (float)(Math.PI / 10));
        chouhoukei1 = new Chouhoukei(400, 150, 80, 40, (float)(-Math.PI / 8));
    }

    public void draw() {
        background(0);
        fill(255);
        drawShape(sankaku1.getVertices());
        drawShape(daikei1.getVertices());
        drawShape(chouhoukei1.getVertices());
    }

    private void drawShape(List<PVector> vertices) {
        beginShape();
        for (PVector vec : vertices) {
            vertex(vec.x, vec.y);
        }
        endShape(CLOSE);
    }
    
    public static void main(String args[]) {
        PApplet.main(PMain10b.class.getName()); // Processingの動作を開始する
    }
}
