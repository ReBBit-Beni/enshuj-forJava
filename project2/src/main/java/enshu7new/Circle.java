package enshu7new;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

public class Circle extends Figure {
    
    private static final double MIN_SIZE = 20.0;
    private static final double SHRINK_THRESHOLD = 100.0;
    
    // 遅延消滅ロジックの時間定数 (5秒待機、5秒かけて縮小)
    private static final int SHRINK_DURATION_MS = 5000;
    private static final int DELAY_MS = 5000;
    
    private double size;
    private double initialSize;
    private final long birthTime; // 生成時刻
    private boolean isAlive = true;
    private boolean isShrinkable = false; // 100px以上でのみ true
    
    Circle() {
        super();
        this.size = MIN_SIZE;
        this.initialSize = MIN_SIZE;
        setRandomColor(); // 色設定（Figure.javaで実装）
        
        // 円の生成時刻を記録し、寿命計算の基準とする
        this.birthTime = System.currentTimeMillis();
    }
    
    @Override
    public void setEnd(double endX, double endY) {
        double dx = endX - this.x;
        double dy = endY - this.y; // 垂直方向の座標差を計算
        
        double calculatedSize = Math.sqrt(dx * dx + dy * dy); // 半径として扱う
        
        this.size = Math.max(calculatedSize, MIN_SIZE);
        this.initialSize = this.size;
        
        // サイズが100px以上の円のみを縮小対象とする判定ロジックを追加
        if (this.initialSize >= SHRINK_THRESHOLD) {
            this.isShrinkable = true;
        } else {
            this.isShrinkable = false;
        }
    }

    @Override
    public void paint(Graphics2D g) {
        Ellipse2D.Double circleShape = new Ellipse2D.Double(
            this.x - size / 2,
            this.y - size / 2,
            size, size
        );
        
        g.setStroke(new BasicStroke(2.0f, BasicStroke.CAP_ROUND,
                                    BasicStroke.JOIN_ROUND));
        
        g.setPaint(this.color);
        g.draw(circleShape);
    }
    
    public double getSize() {
        return this.size;
    }
    
    /**
     * 他の円を完全に内包しているか判定する
     */
    public boolean contains(Circle other) {
        double dx = this.x - other.x;
        double dy = this.y - other.y;
        double distance = Math.sqrt(dx * dx + dy * dy); // 中心間距離
        
        double r_A = other.size / 2.0; // 被捕食円の半径
        double r_B = this.size / 2.0;  // 捕食円の半径
        
        // 幾何学的内包条件
        return (distance + r_A) <= r_B; 
    }
    
    public boolean isAlive() {
        return isAlive;
    }

    /**
     * 遅延縮小処理を更新 (5秒待機後、5秒かけて縮小)
     */
    public void updateShrinking() {
        if (!isShrinkable) {
            return;
        }
        
        if (!isAlive) return;
        
        long currentTime = System.currentTimeMillis();
        long elapsedTime = currentTime - birthTime;
        
        // =========================================================
        // 【★★ 待機と縮小のフェーズ判定 ★★】
        // =========================================================
        
        // 1. 全期間 (待機5秒 + 縮小5秒 = 10秒) が過ぎたかチェック
        if (elapsedTime >= DELAY_MS + SHRINK_DURATION_MS) {
            isAlive = false; // 消滅
            return;
        }
        
        // 2. 待機期間 (5秒) 中かチェック
        if (elapsedTime < DELAY_MS) {
            // 待機期間中はサイズを固定
            this.size = this.initialSize;
            return;
        }
        
        // 3. 縮小期間中 (5秒経過後)
        
        // 縮小フェーズが始まってからの経過時間
        long shrinkTime = elapsedTime - DELAY_MS;
        
        // 縮小率の計算 (0.0 から 1.0 に近づく)
        double ratio = (double)shrinkTime / SHRINK_DURATION_MS;
        
        final double targetMinSize = 1.0;
        
        // サイズの変動: initialSize から targetMinSize まで線形補間
        double shrinkRange = this.initialSize - targetMinSize;
        this.size = this.initialSize - (shrinkRange * ratio);
        
        if (this.size < targetMinSize) {
            this.size = targetMinSize;
        }
    }
}
/*
 * --- 配布プログラムからの変更点 ---
 * 遅延消滅ロジックの時間定数(5秒待機、5秒かけて縮小)を定義。
 * 円の生成時刻(birthTime)の記録と、isAlive、isShrinkableフラグの追加。
 * setEnd内で、サイズが100px以上の円のみを縮小対象とする判定ロジックを追加。
 * 捕食ロジックの核となる内包判定メソッド(contains)を新規追加。
 * 時間経過に基づき、5秒待機後5秒かけて縮小し消滅するロジック(updateShrinking)を新規追加。
 * --- このプログラムで達成できたこと ---
 * 1. 寿命制御: 100px以上の円に対し、5秒待機後、5秒かけて縮小する多段階の時間制御を実現。
 * 2. 捕食判定: 幾何学的な条件に基づき、他の円を完全に内包しているか厳密に判定できるようになった。
 * 3. Figureを継承することで、図形としての抽象的な振る舞いを実装した。
 */