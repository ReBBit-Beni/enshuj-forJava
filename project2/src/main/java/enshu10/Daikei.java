package enshu10;

import java.util.ArrayList;
import java.util.List;
import processing.core.PVector;

public class Daikei {
  float x, y, top, bottom, h, a;

  public Daikei(float x, float y, float top, float bottom, float h, float a) {
    this.x = x;
    this.y = y;
    this.top = top;
    this.bottom = bottom;
    this.h = h;
    this.a = a;
  }

  public List<PVector> getVertices() {
    List<PVector> list = new ArrayList<>();

    float halfTop = top / 2;
    float halfBottom = bottom / 2;

    PVector[] corners = new PVector[] {
      new PVector(-halfTop, -h/2),  // 左上
      new PVector(halfTop, -h/2),   // 右上
      new PVector(halfBottom, h/2), // 右下
      new PVector(-halfBottom, h/2) // 左下
    };

    for (PVector v : corners) {
      PVector rotated = rotateVertex(v, a);
      rotated.add(x, y);
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

