package com.example.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.example.auth.AuthenticationFilter;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<AuthenticationFilter> authenticationFilter() {
        FilterRegistrationBean<AuthenticationFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new AuthenticationFilter());  // 필터 객체 등록
        registrationBean.addUrlPatterns("/protected/*");  // 필터를 적용할 URL 패턴
        registrationBean.setOrder(1);  // 필터의 순서 지정 (숫자가 작을수록 먼저 실행됨)
        return registrationBean;
    }
}

