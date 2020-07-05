package top.chenbn.guli.entity.chapter;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chbn
 * @create 2020-07-05 9:45
 */
@Data
public class ChapterVO {
  private String id;
  private String title;
  // 一个一级分类有多个二级分类
  private List<VideoVO> children = new ArrayList<>();
}
