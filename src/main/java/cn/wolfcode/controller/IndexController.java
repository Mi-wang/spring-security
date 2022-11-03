package cn.wolfcode.controller;

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
        return "<h1>系统首页</h1>";
    }
}