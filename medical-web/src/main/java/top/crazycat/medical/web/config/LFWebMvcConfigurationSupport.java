package top.crazycat.medical.web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import top.crazycat.medical.web.view.LocalFileViewResolver;

import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA.
 *
 * @author liyongbing
 * @date: 2018/1/23
 * description:
 */
@Configuration
public class LFWebMvcConfigurationSupport extends WebMvcConfigurationSupport {

    @Resource
    private LocalFileViewResolver localFileViewResolver;

    @Override
    protected void configureViewResolvers(ViewResolverRegistry registry) {
        super.configureViewResolvers(registry);
        registry.viewResolver(localFileViewResolver);
    }

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        super.addResourceHandlers(registry);
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }
}
