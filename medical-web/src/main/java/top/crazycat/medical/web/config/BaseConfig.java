package top.crazycat.medical.web.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.dubbo.registry.dubbo.DubboRegistry;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 *
 * @author liyongbing
 * @date: 2018/3/12
 * description:
 */
@Configuration
@ImportResource(locations={"classpath:dubbo.xml"})
public class BaseConfig {

    @Autowired
    private Environment env;

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

    @Bean
    public DataSource dataSource(){
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(env.getProperty("spring.datasource.url"));
        dataSource.setUsername(env.getProperty("spring.datasource.username"));
        dataSource.setPassword(env.getProperty("spring.datasource.password"));
        return dataSource;
    }

}
