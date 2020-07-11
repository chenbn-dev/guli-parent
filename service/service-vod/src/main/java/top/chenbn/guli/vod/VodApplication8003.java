package top.chenbn.guli.vod;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author chbn
 * @create 2020-07-11 9:37
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@ComponentScan(basePackages = {"top.chenbn.guli"})
public class VodApplication8003 {
  public static void main(String[] args) {
    SpringApplication.run(VodApplication8003.class, args);
  }
}
