package top.chenbn.guli.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author chbn
 * @create 2020-06-26 14:34
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
  @Override
  public void insertFill(MetaObject metaObject) {
    this.setFieldValByName("gmtCreate", new Date(), metaObject);
    this.setFieldValByName("gmtModified", new Date(), metaObject);
  }

  @Override
  public void updateFill(MetaObject metaObject) {
    this.setFieldValByName("gmtModified", new Date(), metaObject);
  }
}