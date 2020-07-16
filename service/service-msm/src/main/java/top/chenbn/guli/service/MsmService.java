package top.chenbn.guli.service;

import java.util.Map;

/**
 * @author chbn
 * @create 2020-07-15 23:29
 */
public interface MsmService { // 发送短信的方法
  boolean send(Map<String, Object> param, String phone);
}
