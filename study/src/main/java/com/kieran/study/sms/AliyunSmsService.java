package com.kieran.study.sms;

/**
 * 短信服务
 */
public interface AliyunSmsService {
    /** 发送短信 */
    String sendSms(String phoneNumbers, String signName, String templateCode, String templateParam) throws Exception;

    /** 批量发送短信 */
    String sendBatchSms(String phoneNumberJson, String signNameJson, String templateCode, String templateParamJson)
            throws Exception;
}
