package com.example.auth;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    private final LoginService loginService = new LoginService();

    @PostMapping("/login")
    public void login(@RequestParam String email, @RequestParam String password,
                      HttpServletRequest request, HttpServletResponse response) {
        boolean isAuthenticated = loginService.authenticate(email, password, request);

        if (!isAuthenticated) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 401 Unauthorized
        }
    }
}
