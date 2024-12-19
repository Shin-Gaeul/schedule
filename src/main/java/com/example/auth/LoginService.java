package com.example.auth;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginService {

    // 이메일과 비밀번호를 받아서 인증 처리
    public boolean authenticate(String email, String password, HttpServletRequest request, HttpServletResponse response) {
        // 예시로 이메일과 비밀번호가 맞는지 확인
        if ("user@example.com".equals(email) && "password123".equals(password)) {
            // 인증 성공 시, 세션에 사용자 이메일 저장
            request.getSession().setAttribute("userEmail", email);

            // 로그인 성공 시 쿠키에 세션 ID 저장
            Cookie userCookie = new Cookie("JSESSIONID", request.getSession().getId());
            userCookie.setMaxAge(60 * 60 * 24);  // 쿠키 유효 시간 24시간
            userCookie.setPath("/");  // 모든 경로에서 쿠키를 사용할 수 있도록 설정
            response.addCookie(userCookie);

            return true;  // 인증 성공
        }

        return false;  // 인증 실패
    }
}
