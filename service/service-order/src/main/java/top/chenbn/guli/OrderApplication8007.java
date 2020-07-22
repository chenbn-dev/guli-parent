package top.chenbn.guli;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author chbn
 * @create 2020-07-22 23:33
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@MapperScan("top.chenbn.guli.mapper")
public class OrderApplication8007 {
  public static void main(String[] args) {
    SpringApplication.run(OrderApplication8007.class, args);
  }
}
