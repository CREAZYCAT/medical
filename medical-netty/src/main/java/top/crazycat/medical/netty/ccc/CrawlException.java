package top.crazycat.medical.netty.ccc;

/**
 * Created with IntelliJ IDEA.
 *
 * @author liyongbing
 * @date: 2018/3/20
 * description:
 */
public class CrawlException extends Exception  {
    public CrawlException() {
    }

    public CrawlException(String message) {
        super(message);
    }

    public CrawlException(String message, Throwable cause) {
        super(message, cause);
    }

    public CrawlException(Throwable cause) {
        super(cause);
    }

    public CrawlException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
