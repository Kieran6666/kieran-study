package com.kieran.study.sms;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@RequestMapping("/aliyun")
@RestController
public class AliyunSmsController {
    @Resource
    private AliyunSmsService sms;

    @RequestMapping("/test")
    public String test() {
        ServletRequestAttributes requestAttributes
                = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        System.err.println("本地端口:" + request.getLocalPort());
        System.err.println("网络端口:" + request.getServerPort());
        return "test";
    }

    /**
     * 可以成功发送，需要到阿里云把用户组的子用户激活
     * @return
     * @throws Exception
     */
    @PostMapping("/sms")
    public String sendSms() throws Exception {
        String phoneNumbers = "15170007081,19542778319";
        String signName = "阿里云短信测试";
        String templateCode = "SMS_154950909";
        HashMap<String, Integer> map = new HashMap<>();
        map.put("code", 2023);

        String res = sms.sendSms(phoneNumbers, signName, templateCode,
                JSONObject.toJSONString(map));

        return res;
    }

    /**
     * 无法成功
     * @return
     * @throws Exception
     */
    @RequestMapping("/batchSms")
    public String sendBatchSms() throws Exception {
        String[] phoneNumbers = {"15170007081"};
        String[] signNames = {"KieranSMS"};
        JSONArray arr = new JSONArray();
        JSONObject object = new JSONObject();
        object.put("code", "2023");
        arr.add(object);

        String res = sms.sendBatchSms(JSONArray.toJSONString(phoneNumbers),
                JSONArray.toJSONString(signNames),
                "SMS_154950909",
                arr.toJSONString());

        return res;
    }
}
