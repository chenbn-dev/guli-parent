package top.chenbn.guli.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author chbn
 * @create 2020-07-01 23:00
 */
@Data
public class DemoData {
  // 设置表头数据
  @ExcelProperty(value = "学生编号", index = 0)
  private Integer sno;

  @ExcelProperty(value = "学生姓名", index = 1)
  private String sname;
}
