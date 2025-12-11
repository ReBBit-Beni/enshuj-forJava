package enshu8;

import java.util.ArrayList;
import java.util.List;
import processing.core.PApplet;
import processing.core.PVector;

public class Test_1 extends PApplet {

    List<Sankaku> triangles = new ArrayList<>();

    public void settings() {
        size(600, 600);
    }

    public void setup() {
        int count = 13; // 三角形の数
        float spacing = 40; // 三角形同士の間隔
        
        for (int i = 0; i < count; i++) {
            float x = 50 + i * spacing;
            float y = height / 2;
            float angle = (float)Math.toRadians(i * 30); // 30度ずつ回転させて初期化
            triangles.add(new Sankaku(x, y, 30, 15, angle));
        }
    }
    
    public void draw() {
        background(0);
        fill(255);
    
        for (Sankaku s : triangles) {
            drawTriangle(s);
        }
    }

    private void drawTriangle(Sankaku s) {
        pushMatrix();
        translate(s.x, s.y);
        List<PVector> vertices = s.getVertices();
        beginShape();
        for (PVector v : vertices) {
            vertex(v.x, v.y);
        }
        endShape();
        popMatrix();
    }

    public static void main(String[] args) {
        PApplet.main(Test_1.class.getName());
    }
}
