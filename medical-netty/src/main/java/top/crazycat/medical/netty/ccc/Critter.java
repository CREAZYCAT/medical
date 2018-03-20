package top.crazycat.medical.netty.ccc;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 *
 * @author liyongbing
 * @date: 2018/3/20
 * description:
 */
public class Critter extends Thing implements Serializable, Mob {
    private double value;
    private int health;
    public Critter(String shortDescription, String longDescription, double value, int health) {
        super(shortDescription, longDescription);
        this.value = value;
        this.health = health;
    }


    boolean canLoot(Thing looter) {
        return false;
    }

    int getHealth() {
        return health;
    }

    double getValue() {
        return value;
    }

    @Override
    public void fight(Mob mob) {

    }

    @Override
    public int getDamage() {
        return 0;
    }

    @Override
    public boolean isAlive() {
        return false;
    }

    @Override
    public void setAlive(boolean b) {

    }

    @Override
    public void takeDamage(int d) {

    }

    @Override
    public boolean wantsToFight(Mob mob) {
        return false;
    }
}
