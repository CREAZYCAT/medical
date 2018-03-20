package top.crazycat.medical.netty.ccc;

/**
 * Created with IntelliJ IDEA.
 *
 * @author liyongbing
 * @date: 2018/3/20
 * description:
 */
public class Treasure extends Thing implements Lootable {

    public Treasure(String shortDescription, String longDescription) {
        super(shortDescription, longDescription);
    }

    @Override
    public boolean canLoot(Thing looter) {
        return false;
    }

    @Override
    public double getValue() {
        return 0;
    }
}
