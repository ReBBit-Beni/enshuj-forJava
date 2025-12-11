package enshu11;

/** クラスPicachuはクラスPokemonをスーパークラスとする(extendsの後ろにスーパークラスを書く) */
public class Pikachu extends Pokemon {

    /** でんきの量 このクラス特有の属性 */
    int denki;

    /** コンストラクタ */
    Pikachu() {
        super(35, 55, 30); // スーパークラスPokemonのコンストラクタ呼び出し
        denki = 3;
    }

    /** 攻撃力を返す ピカチュウはでんきがあると攻撃力が上がることにする */
    int getAttackPower() {
        int a = attackPower;  // スーパークラスに定義された攻撃力
        if (denki > 0) { // でんきが残ってれば攻撃力+30
            a += 30;
            denki--;
        }
        return a;
    }

    /** でんきを充電する このクラス特有のメソッド */
    void charge() {
        denki += 3;
    }

    /** System.out.println()で表示する文字列を返す */
    public String toString() {
        String status = String.format("ピカチュウ: HP=%d denki=%d", hitPoint, denki);
        return status;
    }

}