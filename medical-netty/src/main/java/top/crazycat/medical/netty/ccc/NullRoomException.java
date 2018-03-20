package top.crazycat.medical.netty.ccc;

/**
 * Created with IntelliJ IDEA.
 *
 * @author liyongbing
 * @date: 2018/3/20
 * description:
 */
public class NullRoomException extends CrawlException {
    public NullRoomException() {
    }

    public NullRoomException(String message) {
        super(message);
    }

    public NullRoomException(String message, Throwable cause) {
        super(message, cause);
    }

    public NullRoomException(Throwable cause) {
        super(cause);
    }

    public NullRoomException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
