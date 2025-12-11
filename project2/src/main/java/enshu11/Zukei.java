package enshu11;

import java.util.List;
import processing.core.PApplet;
import processing.core.PVector;

public abstract class Zukei {
  protected float x, y, a;  // 共通属性

  public Zukei(float x, float y, float a) {
    this.x = x;
    this.y = y;
    this.a = a;
  }

  // 各図形が具体的な頂点リストを提供
  public abstract List<PVector> getVertices();

  // 共通の描画処理
  public void draw(PApplet app) {
    app.beginShape();
    for (PVector v : getVertices()) {
      app.vertex(v.x, v.y);
    }
    app.endShape(PApplet.CLOSE);
  }

  // 頂点の回転処理も共通化
  protected PVector rotateVertex(PVector v, float angle) {
    float xNew = (float)(v.x * Math.cos(angle) - v.y * Math.sin(angle));
    float yNew = (float)(v.x * Math.sin(angle) + v.y * Math.cos(angle));
    return new PVector(xNew, yNew);
  }
}


