package com.ailu.paas;

import com.alibaba.fastjson.JSONObject;

import java.security.DigestException;
import java.util.HashMap;
import java.util.Map;

public class AiluPaasNotice {
    private static final AiluPaasNotice ailuPaas = new AiluPaasNotice();
    private String appKey;
    private String secret;

    private AiluPaasNotice() {
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public static AiluPaasNotice getAiluPaas(String appKey, String secret) {
        ailuPaas.setAppKey(appKey);
        ailuPaas.setSecret(secret);
        return ailuPaas;
    }

    public JSONObject smsSendMsg(String phoneNumber, String content) throws DigestException {
        Map<String, Object> paramMap = new HashMap();
        paramMap.put("phoneNumber", phoneNumber);
        paramMap.put("content", content);
        JSONObject result = NetWorkUtils.httpPost(PaasApi.SMS_SEND_CONTENT.getUrl(), this.appKey, this.secret, paramMap);
        return result;
    }

    public JSONObject smsSendVoiceValidCode(String phoneNumber, String validCode) throws DigestException {
        Map<String, Object> paramMap = new HashMap();
        paramMap.put("phoneNumber", phoneNumber);
        paramMap.put("validCode", validCode);
        JSONObject result = NetWorkUtils.httpPost(PaasApi.SMS_SEND_VOICE.getUrl(), this.appKey, this.secret, paramMap);
        return result;
    }

    public JSONObject smsSendValidCode(String phoneNumber) throws DigestException {
        Map<String, Object> paramMap = new HashMap();
        paramMap.put("phoneNumber", phoneNumber);
        JSONObject result = NetWorkUtils.httpPost(PaasApi.SMS_SEND_VALIDCODE.getUrl(), this.appKey, this.secret, paramMap);
        return result;
    }

    public JSONObject smsCheckValidCode(String phoneNumber, String validCode) throws DigestException {
        Map<String, Object> paramMap = new HashMap();
        paramMap.put("phoneNumber", phoneNumber);
        paramMap.put("validCode", validCode);
        JSONObject result = NetWorkUtils.httpPost(PaasApi.SMS_CHECK_VALIDCODE.getUrl(), this.appKey, this.secret, paramMap);
        return result;
    }

    public JSONObject mailSendWithSubject(String mailAddress, String subject, String content) throws DigestException {
        Map<String, Object> paramMap = new HashMap();
        paramMap.put("mail", mailAddress);
        paramMap.put("subject", subject);
        paramMap.put("content", content);
        JSONObject result = NetWorkUtils.httpPost(PaasApi.EMAIL_SEND_WITH_SUBJECT.getUrl(), this.appKey, this.secret, paramMap);
        return result;
    }
}