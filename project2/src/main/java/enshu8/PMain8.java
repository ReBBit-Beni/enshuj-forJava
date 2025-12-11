package enshu8;

import java.util.List;

import processing.core.PApplet;
import processing.core.PVector;

public class PMain8 extends PApplet{

    Sankaku sankaku1;

    public void settings() {
        size(400, 400);
    }

    public void setup() {
        sankaku1 = new Sankaku(100, 100, 20, 10, (float)(Math.PI / 6));
    }

    public void draw() {
        background(0);
        fill(255);
      sankaku(sankaku1); // インスタンスsankaku1の△を描画する
    }

    private void sankaku(Sankaku s) {
        pushMatrix();
        translate(s.x, s.y);

        List<PVector> vertices = s.getVertices(); // 頂点の相対座標のリスト

        beginShape(); // 頂点の登録開始する

        for (PVector vec: vertices) { // リストの各要素をvecにセットしながらループする
            vertex(vec.x, vec.y); // 頂点をvertex()で登録する
        }
        endShape();
        
        popMatrix();
    }
    
    public static void main(String args[]) {
        PApplet.main(PMain8.class.getName()); // Processingの動作を開始する
    }
}

