package top.chenbn.guli.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.Map;

/**
 * @author chbn
 * @create 2020-07-01 23:20
 */
public class ExcelListener extends AnalysisEventListener<DemoData> {
  /**
   * 读取表头内容
   *
   * @param headMap
   * @param context
   */
  @Override
  public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
    System.out.println("表头：" + headMap);
  }
  /**
   * invoke 方法会一行一行读取excel的内容
   *
   * @param data 每次读取的每行的内容
   * @param analysisContext
   */
  @Override
  public void invoke(DemoData data, AnalysisContext analysisContext) {
    System.out.println("*" + data);
  }

  /**
   * doAfterAllAnalysed 方法定义读取完成之后要做的事情
   *
   * @param analysisContext
   */
  @Override
  public void doAfterAllAnalysed(AnalysisContext analysisContext) {}
}
