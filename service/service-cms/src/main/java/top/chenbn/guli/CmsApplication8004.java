package top.chenbn.guli;

/**
 * @author chbn
 * @create 2020-07-14 12:56
 */
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// @ComponentScan({"top.chenbn.guli"}) // 指定扫描位置
@MapperScan("top.chenbn.guli.mapper")
public class CmsApplication8004 {
  public static void main(String[] args) {
    SpringApplication.run(CmsApplication8004.class, args);
  }
}
