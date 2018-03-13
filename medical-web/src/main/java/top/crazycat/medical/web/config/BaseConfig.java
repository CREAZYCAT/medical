package top.crazycat.medical.web.config;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 *
 * @author liyongbing
 * @date: 2018/3/12
 * description:
 */
@Configuration
public class BaseConfig {

    @Value("${view.file.path}")
    private String fileResourceLoaderPath;

    @Bean
    public VelocityEngine velocityEngine(){
        Properties properties = new Properties();
        properties.setProperty("resource.loader","file");
        properties.setProperty("file.resource.loader.class","org.apache.velocity.runtime.resource.loader.FileResourceLoader");
        properties.setProperty("file.resource.loader.path",fileResourceLoaderPath);
        return new VelocityEngine(properties);
    }
}
