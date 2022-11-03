package cn.wolfcode.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wby
 * @version 1.0
 * @date 2022/11/3 10:29
 */
@RestController
@RequestMapping("/departments")
public class DepartmentController {

    @GetMapping
    public String list() {
        return "<h1>部门管理列表</h1>";
    }

    @PostMapping
    public String save() {
        return "<h1>新增部门</h1>";
    }
}