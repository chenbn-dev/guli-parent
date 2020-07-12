package top.chenbn.guli;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author chbn
 * @create 2020-06-26 8:43
 */
@EnableFeignClients
@EnableDiscoveryClient // nacos注解
@SpringBootApplication
@ComponentScan(basePackages = {"top.chenbn.guli"})
public class EduApplication8001 {
  public static void main(String[] args) {
    SpringApplication.run(EduApplication8001.class, args);
  }
}
