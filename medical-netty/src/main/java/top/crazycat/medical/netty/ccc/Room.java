package top.crazycat.medical.netty.ccc;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 *
 * @author liyongbing
 * @date: 2018/3/20
 * description:
 */
public class Room implements Serializable {

    private static final long serialVersionUID = 5498921495172523324L;

    void addExit(java.lang.String name, Room target) {

    }

    //    Add a new exit to this Room
    void enter(Thing item) {

    }

    //    Add Thing to this Room
    java.util.List<Thing> getContents() {
        return null;
    }

    //    What Things are in this Room?
    java.lang.String getDescription() {
        return null;
    }

    //    A description of the room
    java.util.Map<java.lang.String, Room> getExits() {
        return null;
    }

    //    What exits are there from this Room?
    boolean leave(Thing item) {
        return false;
    }

    //    Remove item from Room.
    void removeExit(java.lang.String name) {

    }

    //    Remove an exit from this Room Note: silently fails if exit does not exist
    void setDescription(java.lang.String s) {

    }
    //Change Room description Note: any newlines in s will be replaced with *
}
