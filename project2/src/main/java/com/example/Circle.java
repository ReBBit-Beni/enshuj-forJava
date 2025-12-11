package com.example;

// Coord クラスを継承
public class Circle extends Coord {
    // Circle 独自のフィールド (例として色とサイズ)
    // Color クラスがない場合を考慮し、ここでは int を使用します
    int color;
    double size;

    // コンストラクタ
    public Circle() {
        // 親クラス（Coord）のコンストラクタは暗黙的に super() として自動で呼ばれます
        // この時点で "Coord" が出力されます。
        
        color = 0; // 初期化
        size = 1.0; // 初期化

        // ⭐ 指示された出力
        System.out.println("Circle");
    }
}