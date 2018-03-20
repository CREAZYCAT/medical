package top.crazycat.medical.web.daosupport;

import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import top.crazycat.medical.web.common.MapConvertUtil;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @author liyongbing
 * @date: 2018/2/1
 * description:dao层基类
 */
@Component
public abstract class AbstractJdbcDaoSupport<DO> extends JdbcDaoSupport {

    @Resource
    private DataSource dataSource;

    public AbstractJdbcDaoSupport() {
        super();
    }

    @Override
    protected void checkDaoConfig() {
        if (null == dataSource) {
            throw new IllegalArgumentException("datasource is required");
        }
        setDataSource(dataSource);
        super.checkDaoConfig();
    }

    public List<DO> query(BaseQuery<DO> query) {
        query.generatorQueryParams(defaultQuerySql());
        if (query.isPagination()) {
            return queryWithPagination(query.getSql(), query.getPageNumber(), query.getPageSize(), query.getParams().toArray());
        }
        return query(query.getSql(), query.getParams().toArray());
    }

    protected List<DO> query(String sql, Object... params) {
        return query(getDOClass(), sql, params);
    }

    protected <T> List<T> query(Class<T> clazz, String sql, Object... params) {
//        logger.info("============查询参数===========");
//        logger.info(Arrays.asList(params));
        List<Map<String, Object>> rs = super.getJdbcTemplate().queryForList(sql, params);
        List<T> result = new ArrayList<>(rs.size());
        for (Map<String, Object> en : rs) {
            result.add(MapConvertUtil.convertToEntity(en, clazz));
        }
        return result;
    }

    public DO queryOne(BaseQuery<DO> query) {
        query.generatorQueryParams(defaultQuerySql());
        return queryOne(query.getSql(), query.getParams().toArray());
    }

    protected DO queryOne(String sql, Object... params) {
        return queryOne(getDOClass(), sql, params);
    }

    protected <T> T queryOne(Class<T> clazz, String sql, Object... params) {
        return MapConvertUtil.convertToEntity(super.getJdbcTemplate().queryForMap(sql, params), clazz);
    }

    protected List<DO> queryWithPagination(String sql, Integer pageNumber, Integer pageSize, Object... params) {
        return query(getPageSQL(sql, pageNumber, pageSize), params);
    }

    protected <T> List<T> queryWithPagination(Class<T> clazz, String sql, Integer pageNumber, Integer pageSize, Object... params) {
        return query(clazz, getPageSQL(sql, pageNumber, pageSize), params);
    }

    public Long count(BaseQuery<DO> query) {
        query.generatorQueryParams(defaultCountSql());
        return count(query.getSql(), query.getParams().toArray());
    }

    protected Long count(String sql, Object... params) {
        Number number = getJdbcTemplate().queryForObject(sql, params, Long.class);
        return number != null ? number.longValue() : 0L;
    }

    public int updatebyQuery(UpdateQuery<DO> updateQuery) {
        updateQuery.generatorUpdateParams(updateHeader());
        return getJdbcTemplate().update(updateQuery.getQuery().getSql(), updateQuery.getQuery().getParams().toArray());
    }

    public int insert(DO d) {
        List<Object> params = new ArrayList<>();
        String sql = prepareInsert(d, params);
        return getJdbcTemplate().update(sql, params);
    }

    public void insertWithPrimary(DO d) {
        List<Object> params = new ArrayList<>();
        String sql = prepareInsert(d, params);
        Long id = insertWithPrimary(sql, params);
        try {
            Method method = d.getClass().getMethod("setId", Long.TYPE);
            method.invoke(d, id);
        } catch (Exception e) {
            logger.error("获取DO_ID异常", e);
        }
    }

    /**
     * 数据库主键自增可调用该方法返回主键
     *
     * @param sql
     * @param params
     * @return
     */
    protected Long insertWithPrimary(String sql, Object... params) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        getJdbcTemplate().update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            int index = 1;
            for (Object param : params) {
                ps.setObject(index++, param);
            }
            return ps;
        }, keyHolder);
        return keyHolder.getKey().longValue();
    }


    protected String updateHeader() {
        return "update " + tableName() + " set ";
    }

    protected String insertHeader() {
        return "insert into " + tableName();
    }

    protected String defaultQuerySql() {
        return "select * from " + tableName();
    }

    protected String defaultCountSql() {
        return "select count(1) from " + tableName();
    }

    protected String prepareInsert(DO d, List<Object> params) {
        StringBuilder columnPart = new StringBuilder();
        columnPart.append(insertHeader()).append("(");
        StringBuilder valuesPart = new StringBuilder(" values(");
        Class clazz = d.getClass();
        Method[] methods = clazz.getDeclaredMethods();
        boolean isFirst = true;
        for (Method method : methods) {
            String methodName = method.getName();
            if (methodName.startsWith("get")) {
                try {
                    Object value = method.invoke(d);
                    if (StringUtils.isEmpty(value)) {
                        continue;
                    }
                    if (value instanceof Date) {
                        value = DateFormatUtils.format((Date) value, "yyyy-MM-dd HH:mm:ss");
                    }
                    String column = MapConvertUtil.camelToUnderline(methodName).substring(4);
                    if (isFirst) {
                        isFirst = false;
                    } else {
                        columnPart.append(",");
                        valuesPart.append(",");
                    }
                    columnPart.append(column);
                    valuesPart.append("?");
                    params.add(value);
                } catch (Exception e) {
                    logger.error("获取DO属性异常", e);
                    throw new RuntimeException(e);
                }
            }
        }
        if (params.size() == 0) {
            throw new IllegalArgumentException("no insert param found!");
        }
        return columnPart.append(")").append(valuesPart).append(")").toString();
    }

    protected abstract String tableName();


    private String getPageSQL(String sql, int pageNumber, int pageSize) {
        Integer startRow = (pageNumber - 1) * pageSize;
        StringBuilder sb = new StringBuilder();
        sb.append(sql);
        sb.append(" limit ");
        sb.append(startRow);
        sb.append(",");
        sb.append(pageSize);
        return sb.toString();
    }

    private Class<DO> getDOClass() {
        return (Class<DO>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }


}
