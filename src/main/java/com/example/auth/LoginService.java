package com.example.auth;

import javax.servlet.http.HttpServletRequest;

public class LoginService {

    public boolean authenticate(String email, String password, HttpServletRequest request) {
        // 이메일과 비밀번호 확인 (DB나 서비스로 처리)
        if ("user@example.com".equals(email) && "password123".equals(password)) {
            // 이메일과 비밀번호가 일치하면 세션에 이메일 저장
            request.getSession().setAttribute("userEmail", email);
            return true;
        }
        return false;
    }
}
