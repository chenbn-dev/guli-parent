package top.chenbn.guli.entity.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author chbn
 * @create 2020-07-02 12:44
 */
@Data
public class SubjectData {

  @ExcelProperty(index = 0)
  private String oneSubjectName;

  @ExcelProperty(index = 1)
  private String twoSubjectName;
}
