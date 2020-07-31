package top.chenbn.guli;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author chbn
 * @create 2020-07-28 12:59
 */
@EnableScheduling
@EnableFeignClients
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("top.chenbn.guli.mapper")
public class StaApplication8008 {
  public static void main(String[] args) {
    SpringApplication.run(StaApplication8008.class, args);
  }
}
