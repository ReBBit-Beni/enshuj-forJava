package enshu11;

import java.util.List;
import java.util.Arrays;

import processing.core.PApplet;
import processing.core.PVector;

public class PMain11 extends PApplet {

    Zukei[] zukeiArray; // 共通型でまとめる

    public void settings() {
        size(500, 500);
    }

    public void setup() {
        zukeiArray = new Zukei[] {
            new Sankaku(100, 100, 20, 10, (float)(Math.PI / 6)),
            new Daikei(250, 250, 40, 100, 60, (float)(Math.PI / 10)),
            new Chouhoukei(400, 150, 80, 40, (float)(-Math.PI / 8))
        };
    }
    
    public void draw() {
        background(0);
        fill(255);
        for (Zukei z : zukeiArray) {
            z.draw(this);
        }
    }

    private void drawShape(List<PVector> vertices) {
        beginShape();
        for (PVector vec : vertices) {
            vertex(vec.x, vec.y);
        }
        endShape(CLOSE);
    }

    public static void main(String[] args) {
        PApplet.main(PMain11.class.getName());
    }
}

