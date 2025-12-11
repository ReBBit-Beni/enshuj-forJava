package enshu11;

/** クラスZenigameはクラスPokemonをスーパークラスとする(extendsの後ろにスーパークラスを書く) */
public class Zenigame extends Pokemon {

    /** コンストラクタ */
    Zenigame() {
        super(44, 48, 65); // スーパークラスPokemonのコンストラクタ呼び出し
    }

    /** 防御力を返す ゼニガメはHPが少なくなると殻にこもって防御力が上がることにする */
    int getDefensePower() {
        int d = defensePower;  // スーパークラスに定義された防御力
        if (hitPoint <20) { // HPが少なくなると防御力+50
            d += 40;
        }
        return d;
    }

    /** System.out.println()で表示する文字列を返す */
    public String toString() {
        String status = String.format("ゼニガメ: HP=%d", hitPoint);
        return status;
    }

}
