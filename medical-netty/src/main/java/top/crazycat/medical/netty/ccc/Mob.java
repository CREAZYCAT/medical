package top.crazycat.medical.netty.ccc;

public interface Mob {
    void fight(Mob mob);

    int getDamage();

    boolean isAlive();

    void setAlive(boolean b);

    void takeDamage(int d);

    boolean wantsToFight(Mob mob);
}
