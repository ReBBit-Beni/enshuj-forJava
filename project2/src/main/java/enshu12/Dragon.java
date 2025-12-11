package enshu12;

import processing.core.PApplet;

/** ドラゴン曲線を描画するクラス */
public class Dragon extends Turtle {

    int currentStep = 0; // 再帰段階
    int r, g, b;         // 基本色（クリックで更新）

    public void settings() {
        size(1000, 800); // ウィンドウサイズ

        // 初期色（明るめのランダムカラー）
        r = (int) random(100, 255);
        g = (int) random(100, 255);
        b = (int) random(100, 255);
    }

    public void draw() {
        background(255); // 白背景

        // 💓 鼓動する色の生成（暗めの色との補間）
        int baseColor = color(r, g, b);
        int pulseColor = color(max(r - 30, 0), max(g - 30, 0), max(b - 30, 0)); // 色が負にならないよう調整
        // 鼓動の色生成（時間ベースで徐々に明暗を変化させる）
        float t = (sin(frameCount * 0.05f) * 0.5f) + 0.5f;
        // baseColorとpulseColorの間を行き来して色が脈打つように演出
        int heartbeatColor = lerpColor(baseColor, pulseColor, t);

        reset();         // 座標系初期化
        move(-200, 100); // 初期位置ずらし
        right(deg90);    // 初期向きを設定

        // ドラゴン曲線の描画（鼓動色を渡す）
        dragon(500, true, currentStep, heartbeatColor);

        // 徐々に描画を進行
        if (frameCount % 10 == 0 && currentStep < 15) {
            currentStep++;
        }
    }

    /** クリックで色を更新＆描き直し */
    public void mousePressed() {
        currentStep = 0;

        // 新しいランダムカラーを生成
        r = (int) random(100, 255);
        g = (int) random(100, 255);
        b = (int) random(100, 255);
    }

    /** 再帰的にドラゴン曲線を描画（鼓動色を受け取る） */
    void dragon(float length, boolean first, int step, int heartbeatColor) {
        stroke(heartbeatColor); // 鼓動色で描画

        if (step == 0) {
            forward(length); // 最小単位の線
        } else {
            float len = (float)(length / Math.sqrt(2));

            if (first) {
                left(deg45);
                dragon(len, true, step - 1, heartbeatColor);
                right(deg90);
                dragon(len, false, step - 1, heartbeatColor);
                left(deg45);
            } else {
                right(deg45);
                dragon(len, true, step - 1, heartbeatColor);
                left(deg90);
                dragon(len, false, step - 1, heartbeatColor);
                right(deg45);
            }
        }
    }

    public static void main(String args[]) {
        PApplet.main(Dragon.class.getName());
    }
}

