package top.chenbn.guli.excel;

import com.alibaba.excel.EasyExcel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chbn
 * @create 2020-07-01 23:02
 */
public class TestEasyExcel {

  public static void main(String[] args) {
    //    // 实现 Excel写操作
    //    // 1.设置写入文件夹地址和excel文件名称
    //    String fileName = "E:/write.xlsx";
    //    // 2.调用easyexcel里面的方法实现写操作
    //    EasyExcel.write(fileName, DemoData.class).sheet("学生列表").doWrite(getData());
    // 实现excel的读操作
    String fileName = "E:/write.xlsx";
    EasyExcel.read(fileName,DemoData.class,new ExcelListener()).sheet().doRead();
  }

  // 创建方法返回list集合
  private static List<DemoData> getData() {
    List<DemoData> list = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      DemoData demoData = new DemoData();
      demoData.setSno(i);
      demoData.setSname("jack" + i);
      list.add(demoData);
    }
    return list;
  }
}
