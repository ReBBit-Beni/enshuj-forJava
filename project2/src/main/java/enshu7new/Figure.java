package enshu7new;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random; // Randomクラスをインポートに追加

public abstract class Figure extends Coord {
    
    //protected に変更しすることで、子クラスからのアクセスを許可する
    protected Color color;

    public Figure() {
        super();
        this.color = Color.BLACK;
    }
    
    // RGBモデルに戻した setRandomColor()
    public void setRandomColor() {
        // --- 変更点: HSB から RGB モデルへ ---
        Random rand = new Random();
        int r = rand.nextInt(256); // 赤(Red)の値をランダムに生成
        int g = rand.nextInt(256); // 緑(Green)の値をランダムに生成
        int b = rand.nextInt(256); // 青(Blue)の値をランダムに生成
        this.color = new Color(r, g, b); // 新しいランダムなRGBカラーを設定
    }
    
    public abstract void paint(Graphics2D g);
    public abstract void setEnd(double endX, double endY);
}
/*
 * --- 配布プログラムからの変更点 ---
 * 1. 継承の変更: Coord クラスを継承するように変更しました。これにより、座標管理（x, y フィールドと moveto() メソッド）の責任を Coord クラスに委譲しました。
 * 2. setRandomColor() の実装変更: HSBモデルから、Randomクラスを使用したRGBモデルでのランダムな色生成に戻しました。
 * * --- このプログラムで達成できたこと ---
 * 1. オブジェクト指向の向上: 座標管理を Coord クラスに分離し、Figure クラスの役割を「図形としての抽象的な振る舞いの定義」に特化させることで、継承と責務の分離を明確にしました。
 * 2. 色の生成方法変更: よりシンプルなRandomクラスによるRGB生成方式に戻しました。
 */