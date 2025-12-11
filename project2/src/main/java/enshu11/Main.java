package enshu11;

public class Main {

    public static void main(String args[]) {
        Pokemon p1 = new Pikachu();  // PikachuはPokemonのサブクラスなのでp1に代入できる
        Pokemon p2 = new Zenigame(); // ZenigameもPokemonのサブクラスなのでp2に代入できる
        System.out.printf("[start]");
        System.out.println(p1);
        System.out.println(p2);

        // p1が先攻でバトル開始 どちらかのHPが0になるまで繰り返す
        while (true) {
            p2.attackedBy(p1); // p2がp1に攻撃される(p2 is attacked by p1)
            System.out.println("[P1 -> P2]");
            System.out.println(p1);
            System.out.println(p2);
            if (p2.hitPoint == 0) break; // P2のHP=0なのでループ終了
 
            p1.attackedBy(p2); // p1がp2に攻撃される(p1 is attacked by p2)
            System.out.println("[P1 <- P2]");
            System.out.println(p1);
            System.out.println(p2);
            if (p1.hitPoint == 0) break; // P1のHP=0なのでループ終了
        }
        if (p1.hitPoint == 0) {
            System.out.println("P2 Win");
        } else {
            System.out.println("P1 Win");
        }
    }
}
