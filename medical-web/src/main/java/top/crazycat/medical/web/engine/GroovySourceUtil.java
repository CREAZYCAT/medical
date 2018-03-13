package top.crazycat.medical.web.engine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileReader;
import java.io.Reader;

/**
 * Created with IntelliJ IDEA.
 *
 * @author liyongbing
 * @date: 2018/1/12
 * description:
 */
@Component
public class GroovySourceUtil {
    private static final Logger logger = LoggerFactory.getLogger(GroovySourceUtil.class);

    @Value("${groovy.script.home}")
    private String script_home;

    public Reader findSource(String scriptPath, String encoding) throws Exception {
        File scriptFile = (new File(script_home+scriptPath));
        if(!scriptFile.exists()){
            return null;
        }
        return new FileReader(scriptFile);
    }
//    private static String inputStreamToString(InputStream is, String encoding) throws Exception {
//        try {
//            byte[] b = new byte[1024];
//            String res = "";
//            if (is == null) {
//                return "";
//            }
//
//            int bytesRead = 0;
//            while (true) {
//                bytesRead = is.read(b, 0, 1024); // return final read bytes counts
//                if (bytesRead == -1) {// end of InputStream
//                    return res;
//                }
//                res += new String(b, 0, bytesRead, encoding); // convert to string using bytes
//            }
//        } catch (Exception e) {
//            logger.error("",e);
//            throw e;
//        }
//    }
}
