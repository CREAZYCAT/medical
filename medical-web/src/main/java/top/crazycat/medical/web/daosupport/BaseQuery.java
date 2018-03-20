package top.crazycat.medical.web.daosupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import top.crazycat.medical.web.common.MapConvertUtil;

import java.lang.reflect.Method;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @author liyongbing
 * @date: 2018/2/1
 * description:dao层查询参数
 */
public class BaseQuery<T> {
    private static  final Logger logger = LoggerFactory.getLogger(BaseQuery.class);
    private List<Object> params = new ArrayList<>();
    private T t;
    private Map<String,Object> notEqual;//<>
    private Map<String,Object> greater;//>
    private Map<String,Object> less;//<
    private Map<String,Object> greaterAndEqual;//>=
    private Map<String,Object> lessAndEqual;//<=
    private Map<String,List<Object>> in;
    private Map<String,List<Object>> notIn;
    private String sql;
    private boolean isPagination = false;
    private Integer pageNumber;
    private Integer pageSize;

    private BaseQuery(){}

    public  static <T> BaseQuery<T> getInstance(T t){
        BaseQuery<T> baseQuery = new BaseQuery<>();
        baseQuery.setT(t);
        return baseQuery;
    }

    public void openPagination(){
        isPagination = true;
    }

    public boolean isPagination() {
        return isPagination;
    }

    public BaseQuery<T> generatorQueryParams(String sql){
        StringBuilder builder = new StringBuilder(sql);
        if(!(sql.contains("where") || sql.contains("WHERE"))){
            builder.append(" where 1=1 ");
        }
        T domain = this.getT();
        Class clazz = domain.getClass();
        Method[] methods = clazz.getDeclaredMethods();
        for(Method method:methods){
            String methodName = method.getName();
            if(methodName.startsWith("get")){
                try {
                    Object value = method.invoke(domain);
                    if(StringUtils.isEmpty(value)){
                        continue;
                    }
                    if(value instanceof Date){//TODO 暂不支持Date类型
                        continue;
//                        value = DateUtil.formatDate((Date) value,"yyyy-MM-dd HH:mm:ss");
                    }
                    String column = MapConvertUtil.camelToUnderline(methodName).substring(4);
                    builder.append(" and ").append(column).append("=? ");
                    params.add(value);
                } catch (Exception e) {
                    logger.error("获取DO属性异常",e);
                    throw new RuntimeException(e);
                }
            }
        }
        singleGene(this.getNotEqual(),builder,"<>");
        singleGene(this.getGreater(),builder,">");
        singleGene(this.getLess(),builder,"<");
        singleGene(this.getGreaterAndEqual(),builder,">=");
        singleGene(this.getLessAndEqual(),builder,"<=");
        batchGene(this.getIn(), builder," in ");
        batchGene(this.getNotIn(), builder," not in ");
        this.sql = builder.toString();
        return this;
    }

    public void addNotEqual(String column, Object value){
        if(null == notEqual){
            notEqual = new HashMap<>();
        }
        notEqual.put(column,value);
    }

    public void addGreater(String column, Object value){
        if(null == greater){
            greater = new HashMap<>();
        }
        greater.put(column,value);
    }

    public void addLess(String column, Object value){
        if(null == less){
            less = new HashMap<>();
        }
        less.put(column,value);
    }

    public void addGreaterAndEqual(String column, Object value){
        if(null == greaterAndEqual){
            greaterAndEqual = new HashMap<>();
        }
        greaterAndEqual.put(column,value);
    }

    public void addLessAndEqual(String column, Object value){
        if(null == lessAndEqual){
            lessAndEqual = new HashMap<>();
        }
        lessAndEqual.put(column,value);
    }

    public void addIn(String column, List<Object> value){
        if(null == in){
            in = new HashMap<>();
        }
        in.put(column,value);
    }

    public void addNotIn(String column, List<Object> value){
        if(null == notIn){
            notIn = new HashMap<>();
        }
        notIn.put(column,value);
    }

    private void singleGene(Map<String,Object> queryMap, StringBuilder builder, String keyWord){
        if(null != queryMap && queryMap.size()>0){
            for(Map.Entry<String,Object> entry : queryMap.entrySet()){
                String column = MapConvertUtil.camelToUnderline(entry.getKey());
                builder.append(" and ").append(column).append(keyWord).append("? ");
                params.add(entry.getValue());
            }
        }
    }

    private void batchGene(Map<String,List<Object>> queryMap, StringBuilder builder, String keyWord) {
        if(null != queryMap && queryMap.size()>0){
            for(Map.Entry<String, List<Object>> entry : queryMap.entrySet()){
                String column = MapConvertUtil.camelToUnderline(entry.getKey());
                List<Object> values = entry.getValue();
                if(null == values || values.size() == 0){
                    continue;
                }
                builder.append(" and ").append(column).append(keyWord).append(" ( ");
                boolean isFirst = true;
                for(Object obj : values){
                    if(isFirst){
                        builder.append("?");
                        isFirst=false;
                    }else {
                        builder.append(",?");
                    }
                    params.add(obj);
                }
                builder.append(" ) ");
            }
        }
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public List<Object> getParams() {
        return params;
    }

    public void setParams(List<Object> params) {
        this.params = params;
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    public Map<String, Object> getNotEqual() {
        return notEqual;
    }

    public void setNotEqual(Map<String, Object> notEqual) {
        this.notEqual = notEqual;
    }

    public Map<String, Object> getGreater() {
        return greater;
    }

    public void setGreater(Map<String, Object> greater) {
        this.greater = greater;
    }

    public Map<String, Object> getLess() {
        return less;
    }

    public void setLess(Map<String, Object> less) {
        this.less = less;
    }

    public Map<String, Object> getGreaterAndEqual() {
        return greaterAndEqual;
    }

    public void setGreaterAndEqual(Map<String, Object> greaterAndEqual) {
        this.greaterAndEqual = greaterAndEqual;
    }

    public Map<String, Object> getLessAndEqual() {
        return lessAndEqual;
    }

    public void setLessAndEqual(Map<String, Object> lessAndEqual) {
        this.lessAndEqual = lessAndEqual;
    }

    public Map<String, List<Object>> getIn() {
        return in;
    }

    public void setIn(Map<String, List<Object>> in) {
        this.in = in;
    }

    public Map<String, List<Object>> getNotIn() {
        return notIn;
    }

    public void setNotIn(Map<String, List<Object>> notIn) {
        this.notIn = notIn;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
