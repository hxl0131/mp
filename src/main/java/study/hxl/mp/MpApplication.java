package study.hxl.mp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;


@SpringBootApplication
public class MpApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(MpApplication.class, args);
        String[] beanDefinitionNames = run.getBeanDefinitionNames();
        for (String name : beanDefinitionNames) {
            System.out.println(name);
        }
        System.out.println("haha");
        System.out.println("master test1");
        System.out.println("hotfix test1");
        System.out.println("test push");
        System.out.println("test pull");
    }

}
