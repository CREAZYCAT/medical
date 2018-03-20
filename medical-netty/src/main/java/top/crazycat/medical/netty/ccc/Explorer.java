package top.crazycat.medical.netty.ccc;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created with IntelliJ IDEA.
 *
 * @author liyongbing
 * @date: 2018/3/20
 * description:
 */
public class Explorer extends Thing implements Mob {

    private int health;
    private Map<String,Thing> inventory = new ConcurrentHashMap<>();

    public Explorer(String shortDescription, String longDescription) {
        super(shortDescription, longDescription);
    }
    public Explorer(Explorer p){
        super(p.getShortDescription(),p.getDescription());
    }

    public Explorer(java.lang.String shortDesc, java.lang.String longDesc, int health){
        super(shortDesc, longDesc);
        this.health = health;
    }

    void add(Thing t) {
        inventory.put(t.getDescription(),t);
    }

    //    Put t into player's inventory
    Thing drop(java.lang.String s) {
        return inventory.remove(s);
    }

    //    Remove Thing from inventory
    void drop(Thing t) {
        inventory.remove(t.getDescription());
    }

    //    Remove Thing from inventory.
    java.util.List<Thing> getContents() {
        return new ArrayList<>(inventory.values());
    }
//    What is in the player's inventory

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
