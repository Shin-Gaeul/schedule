package com.example.auth;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class LoginController {

    private final LoginService loginService = new LoginService();  // 로그인 서비스 객체 생성

    // 로그인 API: 이메일과 비밀번호를 받아 로그인 처리
    @PostMapping("/login")
    public void login(@RequestParam String email, @RequestParam String password,
                      HttpServletRequest request, HttpServletResponse response) {
        boolean isAuthenticated = loginService.authenticate(email, password, request, response);

        if (!isAuthenticated) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);  // 401 Unauthorized
        } else {
            response.setStatus(HttpServletResponse.SC_OK);  // 200 OK
        }
    }
}
