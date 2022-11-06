package cn.wolfcode.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;

/**
 * @author wby
 * @version 1.0
 * @date 2022/11/3 10:33
 */
@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @PreAuthorize("hasAuthority('admin')")
    @GetMapping
    public String list() {
        return "<h1>员工管理列表</h1>";
    }

    @PreAuthorize("hasRole('boss')")
    @GetMapping("/save")
    public String save() {
        return "<h1>新增员工</h1>";
    }

    @Secured({"ROLE_boss"})
    @GetMapping("/update")
    public String update() {
        return "<h1>更新员工</h1>";
    }

    @RolesAllowed({"admin", "other"})
    @GetMapping("/delete")
    public String delete() {
        return "<h1>删除员工</h1>";
    }
}