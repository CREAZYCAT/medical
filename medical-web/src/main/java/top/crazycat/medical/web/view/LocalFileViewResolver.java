package top.crazycat.medical.web.view;

import com.alibaba.fastjson.JSON;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @author liyongbing
 * @date: 2018/1/23
 * description:
 */
@Component
public class LocalFileViewResolver implements ViewResolver {
    private static final Logger log = LoggerFactory.getLogger(LocalFileViewResolver.class);

    @Value("${view.prefix}")
    private String prefix;
    @Value("${view.suffix}")
    private String suffix;
    @Resource
    private VelocityEngine velocityEngine;


    public LocalFileViewResolver() {

    }

    public LocalFileViewResolver(String prefix, String suffix) {
        this();
        this.prefix = prefix;
        this.suffix = suffix;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    @Override
    public View resolveViewName(String s, Locale locale) throws Exception {
        StringBuilder builder = new StringBuilder();
        if (!StringUtils.isEmpty(prefix)) {
            builder.append(prefix);
        }
        builder.append(s);
        if (!StringUtils.isEmpty(suffix)) {
            builder.append(suffix);
        }
        return new LocalFileView(builder.toString(),velocityEngine);
    }


    public static class LocalFileView implements View {
        private String viewName;
        private VelocityEngine velocityEngine;

        public LocalFileView(String viewName,VelocityEngine velocityEngine) {
            this.viewName = viewName;
            this.velocityEngine = velocityEngine;
        }

        @Override
        public String getContentType() {
            return "text/html";
        }

        @Override
        public void render(Map<String, ?> map, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
            httpServletResponse.setHeader("Content-Type", getContentType());
            httpServletResponse.setCharacterEncoding("UTF-8");
            try {
                Template template = velocityEngine.getTemplate(viewName, "UTF-8");
                VelocityContext context = new VelocityContext(map);
                template.merge(context, httpServletResponse.getWriter());
            } catch (Exception e) {
                log.error("",e);
                Map<String,Object> errrorMsg = new HashMap<>();
                if(e instanceof ResourceNotFoundException){
                    errrorMsg.put("message","not found resource:"+velocityEngine.getProperty("file.resource.loader.path")+viewName);
                }else {
                    errrorMsg.put("message",e.getMessage());
                }
                errrorMsg.put("code",-1);
                httpServletResponse.getWriter().write(JSON.toJSONString(errrorMsg));
            }
        }
    }
}
