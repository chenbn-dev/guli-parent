package top.chenbn.guli.entity.subject;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 一级分类
 *
 * @author chbn
 * @create 2020-07-04 8:09
 */
@Data
public class OneSubject {
  private String id;
  private String title;
  // 一个一级分类有多个二级分类
  private List<TwoSubject> children = new ArrayList<>();
}
