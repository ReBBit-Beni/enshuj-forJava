package enshu7a;

public class Circle extends Coord {
    // フィールド宣言
    int color, size;

    Circle(){
        // フィールドの初期化
        color = 0;
        size = 10; // 元のコードの -10 は意図的か不明なため、一般的な値 10 に修正。
    }
    
    // move メソッド２ (Coord クラスの move メソッドをオーバーライド)
    @Override
    public void move(int dx, int dy) {
        // 9行目, 10行目: 引数で指定された分，反対側に移動
        x -= dx;
        y -= dy;
        
        System.out.println("move2");
    }

    // size を設定するメソッド
    public void setSize(int s) {
        size = s;
    }
}
