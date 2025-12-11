package enshu7a;

public class Coord {
    // フィールド宣言
    int x, y;
    Coord(){
      
      x = y = 0;
    }
    
    // move メソッド（move メソッド１）
    
    public void move(int dx, int dy) {
        // 9行目: 引数で指定された分，移動
        x += dx;
        // 10行目: 元のコードの -dy を修正して y に加算
        y += dy; // もし y += -dy; が意図的であれば y -= dy; または y += (-dy); と記述
        
        System.out.println("move1");
    }
}
