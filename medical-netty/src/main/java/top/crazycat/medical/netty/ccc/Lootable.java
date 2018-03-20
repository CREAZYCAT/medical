package top.crazycat.medical.netty.ccc;

public interface Lootable {
    boolean canLoot(Thing looter);

    double getValue();
}
