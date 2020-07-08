package top.chenbn.guli.entity.vo;

import lombok.Data;

/**
 * @author chbn
 * @create 2020-07-08 12:54
 */
@Data
public class CoursePublishVO {
  private String id;
  private String title;
  private String cover;
  private Integer lessonNum;
  private String subjectLevelOne;
  private String subjectLevelTwo;
  private String teacherName;
  private String price; // 只用于显示
}
