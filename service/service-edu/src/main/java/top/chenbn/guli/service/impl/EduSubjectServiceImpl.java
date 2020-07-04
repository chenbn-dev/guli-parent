package top.chenbn.guli.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import top.chenbn.guli.entity.EduSubject;
import top.chenbn.guli.entity.excel.SubjectData;
import top.chenbn.guli.entity.subject.OneSubject;
import top.chenbn.guli.entity.subject.TwoSubject;
import top.chenbn.guli.listerer.SubjectExcelListener;
import top.chenbn.guli.mapper.EduSubjectMapper;
import top.chenbn.guli.service.EduSubjectService;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

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

  @Override
  public List<OneSubject> getAllOneTwoSubject() {
    // 1.查询所有一级分类 parentid=0
    QueryWrapper<EduSubject> oneWrapper = new QueryWrapper<>();
    oneWrapper.eq("parent_id", "0");
    List<EduSubject> oneSubjectList = baseMapper.selectList(oneWrapper);
    //    List<EduSubject> list = this.list(oneWrapper);
    // 2.查询所有二级分类
    QueryWrapper<EduSubject> twoWrapper = new QueryWrapper<>();
    twoWrapper.ne("parent_id", "0");
    List<EduSubject> twoSubjectsList = baseMapper.selectList(twoWrapper);
    // 创建集合用于封装最终前端要求的格式并返回
    List<OneSubject> finalSubjectList = new ArrayList<>();
    /*
       3.封装一级分类
       查询出来所有的一级分类list集合遍历，得到每一个分类对象，获取每一个一级分类的对象值，
       封装到要求的list集合里面 finalSubjectList
    */
    for (EduSubject eduSubject : oneSubjectList) {
      OneSubject oneSubject = new OneSubject();
      BeanUtils.copyProperties(eduSubject, oneSubject);
      finalSubjectList.add(oneSubject);
      List<TwoSubject> twoSubjects = new ArrayList<>();
      for (EduSubject eduSubject2 : twoSubjectsList) {
        if (eduSubject2.getParentId().equals(oneSubject.getId())) {
          TwoSubject twoSubject = new TwoSubject();
          BeanUtils.copyProperties(eduSubject2, twoSubject);
          twoSubjects.add(twoSubject);
        }
      }
      oneSubject.setChildren(twoSubjects);
    }
    return finalSubjectList;
  }
}
