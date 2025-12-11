package enshu11;

import java.util.ArrayList;
import java.util.List;
import processing.core.PVector;

public class Chouhoukei extends Zukei {
  float w, h;

  public Chouhoukei(float x, float y, float w, float h, float a) {
    super(x, y, a);  // 共通の位置・回転情報は親へ
    this.w = w;
    this.h = h;
  }

  @Override
  public List<PVector> getVertices() {
    List<PVector> list = new ArrayList<>();

    PVector[] corners = new PVector[] {
      new PVector(-w / 2, -h / 2),  // 左上
      new PVector(w / 2, -h / 2),   // 右上
      new PVector(w / 2, h / 2),    // 右下
      new PVector(-w / 2, h / 2)    // 左下
    };

    for (PVector v : corners) {
      PVector rotated = rotateVertex(v, a);  // 親から継承されたユーティリティ
      rotated.add(x, y);  // 平行移動
      list.add(rotated);
    }

    return list;
  }
}



