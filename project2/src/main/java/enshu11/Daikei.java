package enshu11;

import java.util.ArrayList;
import java.util.List;
import processing.core.PVector;

public class Daikei extends Zukei {
  float top, bottom, h;

  public Daikei(float x, float y, float top, float bottom, float h, float a) {
    super(x, y, a);  // 共通属性は親に
    this.top = top;
    this.bottom = bottom;
    this.h = h;
  }

  @Override
  public List<PVector> getVertices() {
    List<PVector> list = new ArrayList<>();

    float halfTop = top / 2;
    float halfBottom = bottom / 2;

    PVector[] corners = new PVector[] {
      new PVector(-halfTop, -h / 2),   // 左上
      new PVector(halfTop, -h / 2),    // 右上
      new PVector(halfBottom, h / 2),  // 右下
      new PVector(-halfBottom, h / 2)  // 左下
    };

    for (PVector v : corners) {
      PVector rotated = rotateVertex(v, a);
      rotated.add(x, y);
      list.add(rotated);
    }

    return list;
  }
}


