package enshu11;

import java.util.ArrayList;
import java.util.List;
import processing.core.PVector;

public class Sankaku extends Zukei {
  float r1, r2;

  public Sankaku(float x, float y, float r1, float r2, float a) {
    super(x, y, a); // 共通プロパティをスーパークラスに
    this.r1 = r1;
    this.r2 = r2;
  }

  public void print() {
    System.out.printf("x=%f y=%f r1=%f r2=%f a=%f\n", x, y, r1, r2, a);
    List<PVector> list = getVertices();
    System.out.println(list);
  }

  @Override
  public List<PVector> getVertices() {
    List<PVector> list = new ArrayList<>();

    // 頂角（r1の距離で水平方向にあると仮定）
    PVector v0 = new PVector(r1, 0);

    // 底角1：左下（120度方向にr2の長さ）
    float x1 = (float)(r2 * Math.cos(Math.PI * 2 / 3));
    float y1 = (float)(r2 * Math.sin(Math.PI * 2 / 3));
    PVector v1 = new PVector(x1, y1);

    // 底角2：右下（-120度方向にr2の長さ）
    float x2 = (float)(r2 * Math.cos(-Math.PI * 2 / 3));
    float y2 = (float)(r2 * Math.sin(-Math.PI * 2 / 3));
    PVector v2 = new PVector(x2, y2);

    // 回転を適用 + 平行移動（中心座標 x, y を加算）
    v0 = rotateVertex(v0, a);
    v0.add(x, y);

    v1 = rotateVertex(v1, a);
    v1.add(x, y);

    v2 = rotateVertex(v2, a);
    v2.add(x, y);

    // 頂点をリストに追加
    list.add(v0);
    list.add(v1);
    list.add(v2);

    return list;
  }
}
