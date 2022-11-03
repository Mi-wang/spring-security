package cn.wolfcode.config;

import cn.wolfcode.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author wby
 * @version 1.0
 * @date 2022/11/3 11:33
 * Security 的配置类
 * 1. 实现WebSecurityConfigurerAdapter 适配器
 * 2. configure(HttpSecurity http) 来对 Security 进行配置
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserServiceImpl userService;

    public WebSecurityConfig(UserServiceImpl userService) {
        this.userService = userService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //CSRF 防护
        http.csrf().disable();
        // HttpSecurity: Security 过滤器链的构造对象，几乎大部分的配置都是在这完成的
        // 访问控制
        http.authorizeRequests()
                // 匿名时访问，已经登录了就不能访问了
                .antMatchers("/login.jsp", "/login")
                .anonymous()
                // 对匹配的资源直接放行
                .antMatchers("/static/**")
                .permitAll()
                .anyRequest()
                .authenticated();

        // 1. 配置登录页面 => 表单请求
        http.formLogin()
                // 配置登录页面
                .loginPage("/login.jsp")
                // 提交登录表单时 进行的接口
                .loginProcessingUrl("/login")
                // 默认登录的页面
                .defaultSuccessUrl("/")
                .usernameParameter("name")
                .passwordParameter("pass");

        http.rememberMe()
                .tokenValiditySeconds(60 * 60 * 24)
                .userDetailsService(userService);
    }
}
