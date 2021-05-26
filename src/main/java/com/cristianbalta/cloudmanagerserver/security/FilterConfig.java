package com.cristianbalta.cloudmanagerserver.security;

import com.cristianbalta.cloudmanagerserver.jwt.JwtRequestFilter;
import com.cristianbalta.cloudmanagerserver.jwt.JwtUtil;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.KeyPair;

@Configuration
public class FilterConfig {

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public KeyPair keyPair() {
        return Keys.keyPairFor(SignatureAlgorithm.RS256);
    }

    @Bean
    public FilterRegistrationBean registerJwtFilter(KeyPair keyPair) {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        JwtRequestFilter jwtRequestFilter = new JwtRequestFilter(new JwtUtil(keyPair));
        filterRegistrationBean.setFilter(jwtRequestFilter);
        filterRegistrationBean.addUrlPatterns("/api/*");
        return filterRegistrationBean;
    }
}
