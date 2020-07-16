package top.chenbn.guli;

/**
 * @author chbn
 * @create 2020-07-16 13:11
 */
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({"top.chenbn.guli"})
@SpringBootApplication // 取消数据源自动配置
@MapperScan("top.chenbn.guli.ucenterservice.mapper")
public class UcenterApplication8006 {
  public static void main(String[] args) {
    SpringApplication.run(UcenterApplication8006.class, args);
  }
}
