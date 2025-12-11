package enshu7a;

import java.util.ArrayList;

public class CircleTest2 {
    public static void main(String[] args) {
      ArrayList<Coord> objList = new ArrayList<>();
        
        // 8行目: Coord クラスの変数に Circle を代入 (構文エラーの修正)
        Coord c = new Circle();
        
        // 9行目: objList の最後に c を挿入 (コメントの調整)
        objList.add(c);
        
        // 10行目: c を (100, 100) へ移動 (move メソッド)
        c.move(100, 100);
        
        // 11行目: 出力文字列の空白を調整
        System.out.println("x = " + c.x + " y = " + c.y);
        
        // 12行目: Coord クラスにないので呼び出せない (コメントの調整)
        // c.setSize(20);
    }
}