//package top.crazycat.medical.web.config;
//
//import com.alibaba.dubbo.common.URL;
//import com.alibaba.dubbo.registry.Registry;
//import com.alibaba.dubbo.registry.zookeeper.ZookeeperRegistry;
//import com.alibaba.dubbo.remoting.zookeeper.zkclient.ZkclientZookeeperTransporter;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//
//@Configuration
//public class DubboConfig {
//
//    @Bean
//    public Registry registry(){
//        return new ZookeeperRegistry(new URL("zookeeper","zk1.test.yiyaowang.com",2181),new ZkclientZookeeperTransporter());
//    }
//}
