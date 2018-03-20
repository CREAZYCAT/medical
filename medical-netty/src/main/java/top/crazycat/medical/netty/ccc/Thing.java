package top.crazycat.medical.netty.ccc;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 *
 * @author liyongbing
 * @date: 2018/3/20
 * description:
 */
public class Thing implements Serializable {
    private static final long serialVersionUID = -2176522686881004413L;

    private String shortDescription;
    private String shortT;
    private String longDescription;
    private String longT;

    public Thing(String shortDescription, String longDescription) {
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
    }

    public String getDescription() {
        return longDescription;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public String getLong() {
        return longT;
    }

    public void setLong(String longt) {
        this.longT = longt;
    }

    public String getShort() {
        return shortT;
    }

    public void setShort(String shortt) {
        this.shortT = shortt;
    }
}
