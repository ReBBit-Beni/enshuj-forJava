package enshu11;

/** 全てのポケモンに共通する属性とメソッドを持つクラス */
public class Pokemon {

    /** 体力 */
    int hitPoint;

    /** 攻撃力 */
    int attackPower;

    /** 防御力 */
    int defensePower;

    /** コンストラクタ HP,AP,DPを初期化する */
    Pokemon(int hitPoint, int attackPower, int defensePower) {
        this.hitPoint = hitPoint;
        this.attackPower = attackPower;
        this.defensePower = defensePower;
    }

    /** 攻撃力を返す */
    int getAttackPower() {
        return attackPower;
    }

    /** 防御力を返す */
    int getDefensePower() {
        return defensePower;
    }

     /** pからの攻撃を受けて，HPを減らす */
    void attackedBy(Pokemon p) {
        int ap = p.getAttackPower(); // 相手の攻撃力
        int dp = getDefensePower();  // 自分の防御力
        int damage = 0;
        if (ap > dp) {
            damage = ap - dp;
        }
        hitPoint -= damage;
        if (hitPoint <= 0) {
            hitPoint = 0;
        }
    }

   /** System.out.println()で表示する文字列を返す */
    public String toString() {
        String status = String.format("抽象概念: HP=%d", hitPoint);
        return status;
    }
}
