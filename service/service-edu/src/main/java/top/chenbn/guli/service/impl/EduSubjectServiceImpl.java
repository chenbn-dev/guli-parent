package top.chenbn.guli.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import top.chenbn.guli.entity.EduSubject;
import top.chenbn.guli.entity.excel.SubjectData;
import top.chenbn.guli.listerer.SubjectExcelListener;
import top.chenbn.guli.mapper.EduSubjectMapper;
import top.chenbn.guli.service.EduSubjectService;

import java.io.InputStream;

/**
 * 课程科目 服务实现类
 *
 * @author chenbn
 * @since 2020-07-02
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject>
    implements EduSubjectService {

  @Override
  public void saveSubject(MultipartFile file, EduSubjectService eduSubjectService) {
    try {
      // 文件输入流
      InputStream inputStream = file.getInputStream();
      // 调用方法读取
      EasyExcel.read(inputStream, SubjectData.class, new SubjectExcelListener(eduSubjectService))
          .sheet()
          .doRead();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
