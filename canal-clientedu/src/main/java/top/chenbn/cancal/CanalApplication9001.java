package top.chenbn.cancal;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import top.chenbn.cancal.client.CanalClient;

import javax.annotation.Resource;

/**
 * @author chbn
 * @create 2020-07-30
 */
@SpringBootApplication
public class CanalApplication9001 implements CommandLineRunner {
  @Resource private CanalClient canalClient;

  @Override
  public void run(String... strings) throws Exception {
    // 项目启动，执行canal客户端监听
    canalClient.run();
  }

  public static void main(String[] args) {
    SpringApplication.run(CanalApplication9001.class, args);
  }
}
