package top.chenbn.guli.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import top.chenbn.guli.service.MsmService;

import java.util.Map;

/**
 * @author chbn
 * @create 2020-07-15 23:29
 */
@Service
public class MsmServiceImpl implements MsmService { // 发送短信的方法
  @Override
  public boolean send(Map<String, Object> param, String phone) {
    if (StringUtils.isEmpty(phone)) {
      return false;
    }
    DefaultProfile profile =
        DefaultProfile.getProfile(
            "default", "LTAI4G1VHTjAorNScbVLLMCm", "tPYIz9ASxkpg54Ch1DG6cMhSKmMgZs");
    IAcsClient client = new DefaultAcsClient(profile);
    // 设置相关固定的参数
    CommonRequest request = new CommonRequest();
    // request.setProtocol(ProtocolType.HTTPS);
    request.setMethod(MethodType.POST);
    request.setDomain("dysmsapi.aliyuncs.com");
    request.setVersion("2017-05-25");
    request.setAction("SendSms");

    // 设置发送相关的参数
    request.putQueryParameter("PhoneNumbers", phone); // 手机号
    request.putQueryParameter("SignName", "格桑学院在线教育"); // 申请阿里云 签名名称
    request.putQueryParameter("TemplateCode", "SMS_196616516"); // 申请阿里云 模板code
    request.putQueryParameter("TemplateParam", JSONObject.toJSONString(param)); // 验证码数据，转换json数据传递

    try {
      // 最终发送
      CommonResponse response = client.getCommonResponse(request);
      boolean success = response.getHttpResponse().isSuccess();
      return success;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }
}
