package top.crazycat.medical.web.common;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;
import top.crazycat.medical.web.daosupport.AbstractJdbcDaoSupport;
import top.crazycat.medical.web.daosupport.BaseQuery;
import top.crazycat.medical.web.daosupport.UpdateQuery;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 *
 * @author liyongbing
 * @date: 2018/1/31
 * description:
 */
public class MapConvertUtil {

    public static<T> T convertToEntity(Map<String,?> source, Class<T> clazz){
        if(null == source || source.size() == 0){
            return null;
        }
        JSONObject target = new JSONObject();
        for(Map.Entry<String,?> entry:source.entrySet()){
            target.put(underlineToCamel(entry.getKey().toLowerCase().trim()),entry.getValue());
        }
        return JSON.parseObject(target.toJSONString(),clazz);
    }

    private static String underlineToCamel(String param) {
        if (param == null || "".equals(param.trim())) {
            return "";
        }
        StringBuilder sb = new StringBuilder(param);
        Matcher mc = Pattern.compile("_").matcher(param);
        int i = 0;
        while (mc.find()) {
            int position = mc.end() - (i++);
            sb.replace(position - 1, position + 1, sb.substring(position, position + 1).toUpperCase());
        }
        return sb.toString();
    }

    public static String camelToUnderline(String param){
        if (param==null||"".equals(param.trim())){
            return "";
        }
        int len=param.length();
        StringBuilder sb=new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c=param.charAt(i);
            if (Character.isUpperCase(c)){
                sb.append("_");
                sb.append(Character.toLowerCase(c));
            }else{
                sb.append(c);
            }
        }
        return sb.toString().toUpperCase();//数据库目前统一大写
    }

    public static void main(String[] args) {
        TestDao t = new TestDao();
        TestDO testDO = new TestDO();
        testDO.setAppCode("heihei");
        testDO.setAppId(1L);
        BaseQuery<TestDO> baseQuery = BaseQuery.getInstance(testDO);
        UpdateQuery<TestDO> up = UpdateQuery.getInstance(baseQuery,testDO);
        up.generatorUpdateParams("update table set ");
        System.out.println(baseQuery.getSql());
        System.out.println(baseQuery.getParams());
    }

    public static class TestDao extends AbstractJdbcDaoSupport<TestDO> {

        @Override
        protected String tableName() {
            return "test_lyb";
        }

        public String preIns(TestDO testDO){
            return prepareInsert(testDO,new ArrayList<>());
        }

        public TestDO queryById(String id){
            TestDO t = new TestDO();
            t.setAppId(Long.valueOf(id));
            BaseQuery<TestDO> baseQuery = BaseQuery.getInstance(t);
            return queryOne(baseQuery);
        }
        public List<TestDO> queryByCode(String code, int pn, int ps){
            TestDO t = new TestDO();
            t.setAppCode(code);
            BaseQuery<TestDO> baseQuery = BaseQuery.getInstance(t);
            baseQuery.openPagination();
            baseQuery.setPageNumber(pn);
            baseQuery.setPageSize(ps);
            return query(baseQuery);
        }
    }
    public static class TestDO implements Serializable {
        private static final long serialVersionUID = -2531011554542759745L;
        private String appCode;
        private Integer isSuccess;
        private Integer appCount;
        private Long appId;
        private Date appInit;

        @Override
        public String toString() {
            return "TestDO{" +
                    "appCode='" + appCode + '\'' +
                    ", isSuccess=" + isSuccess +
                    ", appCount=" + appCount +
                    ", appId=" + appId +
                    ", appInit=" + appInit +
                    '}';
        }

        public String getAppCode() {
            return appCode;
        }

        public void setAppCode(String appCode) {
            this.appCode = appCode;
        }

        public Integer getIsSuccess() {
            return isSuccess;
        }

        public void setIsSuccess(Integer success) {
            isSuccess = success;
        }

        public Integer getAppCount() {
            return appCount;
        }

        public void setAppCount(Integer appCount) {
            this.appCount = appCount;
        }

        public Long getAppId() {
            return appId;
        }

        public void setAppId(Long appId) {
            this.appId = appId;
        }

        public Date getAppInit() {
            return appInit;
        }

        public void setAppInit(Date appInit) {
            this.appInit = appInit;
        }
    }
}
