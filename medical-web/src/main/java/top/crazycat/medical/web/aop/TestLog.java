package top.crazycat.medical.web.aop;

import com.alibaba.fastjson.JSON;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import top.crazycat.medical.web.annotation.Log;

import java.util.Arrays;

@Aspect
@Component
public class TestLog {
    private static final Logger logger = LoggerFactory.getLogger(TestLog.class);

    @Pointcut("execution(* top.crazycat.medical.web..*.*(..))")
    public void executeService(){
    }

    @Around("executeService() && @annotation(log)")
    public Object doAroundAdvice(ProceedingJoinPoint proceedingJoinPoint, Log log) throws Throwable {
        logger.info("==========测试方法执行开始==============");
        logger.info("==========目标方法名：[{}]",proceedingJoinPoint.getSignature().getName());
        logger.info("==========目标方法参数：[{}]",Arrays.asList(proceedingJoinPoint.getArgs()).toString());
        long start = System.currentTimeMillis();
        try {
            Object obj = proceedingJoinPoint.proceed();
            if (null != obj){
                logger.info("==========测试方法执行正常，rs[{}]",JSON.toJSONString(obj));
            }
            logger.info("==========执行时间：[{}ms]",System.currentTimeMillis()-start);
            logger.info("==========测试方法执行结束==============");
            return obj;
        } catch (Throwable throwable) {
            logger.error("==========测试方法执行异常==============",throwable);
            throw throwable;
        }
    }
}
