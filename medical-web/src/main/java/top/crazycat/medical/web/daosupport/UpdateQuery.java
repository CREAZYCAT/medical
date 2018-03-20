package top.crazycat.medical.web.daosupport;

import org.apache.commons.lang.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import top.crazycat.medical.web.common.MapConvertUtil;

import java.lang.reflect.Method;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 *
 * @author liyongbing
 * @date: 2018/2/2
 * description:基于查询更新
 */
public class UpdateQuery<T> {
    private static  final Logger logger = LoggerFactory.getLogger(UpdateQuery.class);
    private BaseQuery<T> query;
    private T update;

    private UpdateQuery() {
    }

    public static <T> UpdateQuery<T> getInstance(BaseQuery<T> q, T t) {
        UpdateQuery<T> updateQuery = new UpdateQuery<>();
        updateQuery.setQuery(q);
        updateQuery.setUpdate(t);
        return updateQuery;
    }

    public UpdateQuery<T> generatorUpdateParams(String sql){
        StringBuilder builder = new StringBuilder(sql);
        T domain = this.getUpdate();
        Class clazz = domain.getClass();
        Method[] methods = clazz.getDeclaredMethods();
        boolean isFirst = true;
        for(Method method:methods){
            String methodName = method.getName();
            if(methodName.startsWith("get")){
                try {
                    Object value = method.invoke(domain);
                    if(StringUtils.isEmpty(value)){
                        continue;
                    }
                    if(value instanceof Date){
                        value = DateFormatUtils.format((Date) value,"yyyy-MM-dd HH:mm:ss");
                    }
                    String column = MapConvertUtil.camelToUnderline(methodName).substring(4);
                    if(isFirst){
                        isFirst=false;
                    }else {
                        builder.append(",");
                    }
                    builder.append(column).append("=? ");
                    query.getParams().add(value);
                } catch (Exception e) {
                    logger.error("获取DO属性异常",e);
                    throw new RuntimeException(e);
                }
            }
        }
        query.generatorQueryParams(builder.toString());
        return this;
    }

    public BaseQuery<T> getQuery() {
        return query;
    }

    public void setQuery(BaseQuery<T> query) {
        this.query = query;
    }

    public T getUpdate() {
        return update;
    }

    public void setUpdate(T update) {
        this.update = update;
    }
}
