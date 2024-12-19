package com.example.auth;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class AuthenticationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // 초기화 코드 (필요한 경우)
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // 세션에서 이메일 정보 확인
        String userEmail = (String) httpRequest.getSession().getAttribute("userEmail");

        // 세션에 이메일이 없다면 쿠키에서 찾아본다.
        if (userEmail == null) {
            Cookie[] cookies = httpRequest.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if ("JSESSIONID".equals(cookie.getName())) {
                        String sessionId = cookie.getValue();
                        // 세션 ID로 세션을 검색하여 인증 정보를 확인할 수 있도록 구현
                        // 세션 유효성 검사 후 사용자 이메일을 찾으면 인증 처리
                        userEmail = findUserBySessionId(sessionId);
                        break;
                    }
                }
            }
        }

        if (userEmail == null) {
            // 인증 실패 처리
            httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
        } else {
            // 인증 통과 시 다음 필터로 요청을 전달
            chain.doFilter(request, response);
        }
    }

    // 세션 ID를 기반으로 사용자 이메일을 찾는 메소드
    private String findUserBySessionId(String sessionId) {
        // 실제로 세션 관리 방식에 맞게 구현해야 함
        // 예시로 고정된 이메일 반환
        return "user@example.com";  // 예시: 세션 ID가 user@example.com과 매칭
    }

    @Override
    public void destroy() {
        // 필터 종료 시 필요한 작업이 있으면 처리
    }
}
