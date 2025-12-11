package enshu8;

import java.util.ArrayList;
import java.util.List;

import processing.core.PVector;

public class Sankaku {
  float x, y, r1, r2, a;

  public Sankaku(float x, float y, float r1, float r2, float a) {
      this.x = x;
      this.y = y;
      this.r1 = r1;
      this.r2 = r2;
      this.a = a;
  }

  void print() {
    System.out.printf("x=%f y=%f r1=%f r2=%f a=%f\n", x, y, r1, r2, a);
    List<PVector> list = getVertices();
    System.out.println(list);
  }

  List<PVector> getVertices() {
    List<PVector> list = new ArrayList<>();

    // 頂角
    PVector v0 = new PVector(r1, 0);

    // 底角 1
    float x1 = (float) (r2 * Math.cos(Math.PI * 2 / 3));
    float y1 = (float) (r2 * Math.sin(Math.PI * 2 / 3));
    PVector v1 = new PVector(x1, y1);

    // 底角 2
    float x2 = (float) (r2 * Math.cos(-Math.PI * 2 / 3));
    float y2 = (float) (r2 * Math.sin(-Math.PI * 2 / 3));
    PVector v2 = new PVector(x2, y2);

    // **回転を適用**
    v0 = rotateVertex(v0, a);
    v1 = rotateVertex(v1, a);
    v2 = rotateVertex(v2, a);

    list.add(v0);
    list.add(v1);
    list.add(v2);

    return list;
  }

  private PVector rotateVertex(PVector vec, float angle) {
    float xNew = (float)(vec.x * Math.cos(angle) - vec.y * Math.sin(angle));
    float yNew = (float)(vec.x * Math.sin(angle) + vec.y * Math.cos(angle));
    return new PVector(xNew, yNew);
  }

  public static void main(String args[]) {
    Sankaku s1 = new Sankaku(0, 0, 2, 1, 0);
    s1.print();
  }
}