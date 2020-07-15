package top.chenbn.guli;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author chbn
 * @create 2020-07-15 23:25
 */
@ComponentScan({"top.chenbn.guli"})
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class) // 取消数据源自动配置
public class MsmApplication8005 {
  public static void main(String[] args) {
    SpringApplication.run(MsmApplication8005.class, args);
  }
}
