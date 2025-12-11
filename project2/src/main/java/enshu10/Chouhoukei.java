package enshu10;

import java.util.ArrayList;
import java.util.List;
import processing.core.PVector;

public class Chouhoukei {
  float x, y, w, h, a;

  public Chouhoukei(float x, float y, float w, float h, float a) {
    this.x = x;
    this.y = y;
    this.w = w;
    this.h = h;
    this.a = a;
  }

  public List<PVector> getVertices() {
    List<PVector> list = new ArrayList<>();

    PVector[] corners = new PVector[] {
      new PVector(-w/2, -h/2), // 左上
      new PVector(w/2, -h/2),  // 右上
      new PVector(w/2, h/2),   // 右下
      new PVector(-w/2, h/2)   // 左下
    };

    for (PVector v : corners) {
      PVector rotated = rotateVertex(v, a);
      rotated.add(x, y); // 平行移動
      list.add(rotated);
    }

    return list;
  }

  private PVector rotateVertex(PVector v, float angle) {
    float xNew = (float)(v.x * Math.cos(angle) - v.y * Math.sin(angle));
    float yNew = (float)(v.x * Math.sin(angle) + v.y * Math.cos(angle));
    return new PVector(xNew, yNew);
  }
}


