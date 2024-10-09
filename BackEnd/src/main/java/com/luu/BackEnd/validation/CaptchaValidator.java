package com.luu.BackEnd.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class CaptchaValidator {
    @Value("${recaptcha.secret.key}")
    private String scretKey;
    @Value("${recaptcha.verifyUrl}")
    private String verifyUrl;
    private static final String RECAPTCHA_VERIFY_URL = "https://www.google.com/recaptcha/api/siteverify";
    public boolean validateCaptcha(String captchaResponse) {

        // Nếu không có giá trị captchaResponse, trả về false
        if (captchaResponse == null || captchaResponse.isEmpty()) {
            return false;
        }
        // Tạo body request để gửi tới Google reCAPTCHA
        Map<String, String> body = new HashMap<>();
        body.put("secret", scretKey);
        body.put("response", captchaResponse);
        System.out.println(captchaResponse);

        // Gửi request tới Google API
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> response = restTemplate.postForObject(RECAPTCHA_VERIFY_URL, body, Map.class);
        System.out.println(response);

        // Kiểm tra kết quả
        if (response == null || !(Boolean) response.get("success")) {
            return false;
        }
        return true;
    }
}
