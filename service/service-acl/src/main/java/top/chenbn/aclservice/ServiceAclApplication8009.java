package top.chenbn.aclservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author chbn
 * @create 2020-08-01 11:13
 */
@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan("top.chenbn")
@MapperScan("top.chenbn.aclservice.mapper")
public class ServiceAclApplication8009 {
  public static void main(String[] args) {
    SpringApplication.run(ServiceAclApplication8009.class, args);
  }
}
