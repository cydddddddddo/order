package com.example.order.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author Cy
 * @data 2020/6/30 - 0:37
 */

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SimpleLoginSuccessHandler simpleLoginSuccessHandler;

    //授权
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/","/sys/toLogin","/**").permitAll()
                .antMatchers("/**","/sys/toLogin").permitAll()
                .anyRequest().authenticated();

        //关闭csrf
        http.csrf().disable();
        //登陆
        http.formLogin()
                //.successHandler(simpleLoginSuccessHandler)
                .loginPage("/sys/toLogin")
                .loginProcessingUrl("/login")
                .successForwardUrl("/login/login")
                .failureForwardUrl("/login/loginError")
                .usernameParameter("userId");
        //注销
        http.logout().logoutSuccessUrl("/sys/toLogin");
        //记住账号功能
        //http.rememberMe();

        http.headers().frameOptions().sameOrigin();
    }

    /**
     * 静态资源获取
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/**");
    }

    /**
     * 获得用户信息对象
     * @return
     */
    @Bean
    public UserDetailsService customUserDetailsService() {
        return new CustomUserDetailsService();
    }

    /**
     * 使用BCrypt加密
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    //认证
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService()).passwordEncoder(passwordEncoder());
    }
}