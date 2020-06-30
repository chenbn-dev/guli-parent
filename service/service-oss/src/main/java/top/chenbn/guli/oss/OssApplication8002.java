package top.chenbn.guli.oss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author chbn
 * @create 2020-06-26 8:43
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@ComponentScan(basePackages = {"top.chenbn.guli"})
public class OssApplication8002 {
  public static void main(String[] args) {
    SpringApplication.run(OssApplication8002.class, args);
  }
}
