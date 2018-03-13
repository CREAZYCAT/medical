package top.crazycat.medical.web.engine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.io.IOException;
import java.io.Reader;

/**
 * Created with IntelliJ IDEA.
 *
 * @author liyongbing
 * @date: 2018/1/12
 * description:
 */
public class GroovyEngine {
    private static final Logger logger = LoggerFactory.getLogger(GroovyEngine.class);
    private static final ScriptEngine engine;
    private static final String ENGINE_TARGET="groovy";
    public static final String DEFAULT_FUNC = "execute";

    static {
        ScriptEngineManager factory = new ScriptEngineManager();
        engine = factory.getEngineByName(ENGINE_TARGET);
    }

    public static Object execute(Reader script, String funcName, Object... params) throws IOException {
        Object result = null;
        try{
            engine.eval(script);
            result = ((Invocable)engine).invokeFunction(funcName,params);
        }catch (Throwable t){
            logger.error("",t);
            throw new RuntimeException(t.getMessage());
        }finally {
            script.close();
        }
        return result;
    }
}
