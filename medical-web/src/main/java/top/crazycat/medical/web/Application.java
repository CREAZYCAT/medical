package top.crazycat.medical.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import top.crazycat.medical.web.daosupport.BankDao;

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
        BankDao bankDao = app.getBean(BankDao.class);
        System.out.println(bankDao.queryById("ABC"));
        System.out.println(bankDao.queryAll());
    }
}
