package cn.wolfcode.controller;

import cn.wolfcode.domain.LoginUser;
import cn.wolfcode.utils.TokenUtils;
import cn.wolfcode.vo.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wby
 * @version 1.0
 * @date 2022/11/4 11:43
 */
@Slf4j
@RestController
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;


    @PostMapping("/login")
    public JsonResult<?> login(String username, String password) {
        try {
            // 调用 security 进行登录校验
            // AuthenticationManager => 认证管理器， Security 认证流程的管理类
            Authentication authentication = new UsernamePasswordAuthenticationToken(username, password);
            // 进行认证逻辑判断，获取用户对象
            //判断密码是否正确，返回认证信息
            Authentication authenticate = authenticationManager.authenticate(authentication);
            // 将当前登录成功的用户信息保存
            SecurityContextHolder.getContext().setAuthentication(authenticate);

            // 获取认证后的对象
            LoginUser user = (LoginUser)authenticate.getPrincipal();

            // 生成并返回 token
            String token  = TokenUtils.randomToken();
            user.setToken(token);

            // 存入缓存

            TokenUtils.setUser(token,user);

            // 通过认证
            return JsonResult.success(token);
        } catch (Exception e) {
            log.error("[用户登录] 登录失败",e);
            return JsonResult.failed("用户名或密码错误");
        }
    }
}
