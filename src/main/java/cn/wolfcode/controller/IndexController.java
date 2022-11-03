package cn.wolfcode.controller;

import cn.wolfcode.utils.SecurityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wby
 * @version 1.0
 * @date 2022/11/3 10:28
 */
@RestController
public class IndexController {

    @GetMapping
    public String hello() {
        UserDetails loginUser = SecurityUtils.getLoginUser();
        String username = loginUser.getUsername();

        return String.format("<h1>系统首页: %s</h1>", username);
    }
}