package top.crazycat.medical.netty.ccc;

/**
 * Created with IntelliJ IDEA.
 *
 * @author liyongbing
 * @date: 2018/3/20
 * description:
 */
public class ExitExistsException extends CrawlException {
    public ExitExistsException() {
    }

    public ExitExistsException(String message) {
        super(message);
    }

    public ExitExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public ExitExistsException(Throwable cause) {
        super(cause);
    }

    public ExitExistsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
