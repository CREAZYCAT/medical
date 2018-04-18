package top.crazycat.medical.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import top.crazycat.medical.web.daosupport.BankDao;
import top.crazycat.medical.web.service.TestDubbo;

/**
 * Created with IntelliJ IDEA.
 *
 * @author liyongbing
 * @date: 2018/3/12
 * description:
 */
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        ApplicationContext app = SpringApplication.run(Application.class, args);
        TestDubbo testDubbo = app.getBean(TestDubbo.class);
        try {
//            testDubbo.testOrderIssued(7662,null);
            testDubbo.testOrderList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.exit(0);
    }
}
