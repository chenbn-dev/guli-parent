package top.chenbn.guli;

/**
 * @author chbn
 * @create 2020-07-16 13:11
 */
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("top.chenbn.guli")
@EnableDiscoveryClient
@SpringBootApplication
@MapperScan("top.chenbn.guli.mapper")
public class UcenterApplication8006 {
  public static void main(String[] args) {
    SpringApplication.run(UcenterApplication8006.class, args);
  }
}
