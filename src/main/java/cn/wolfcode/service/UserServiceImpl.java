package cn.wolfcode.service;

import cn.wolfcode.domain.LoginUser;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * @author wby
 * @version 1.0
 * @date 2022/11/3 14:58
 */
@Service("userService")
public class UserServiceImpl implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 基于传入的username 去数据库查询用户对象
        String admin = "admin";
        String roots = "root";

        if (admin.equals(username)) {
//            return User.withUsername(admin).password("{noop}123456").authorities("admin", "ROLE_hr").build();
          return new LoginUser(username,"$2a$10$T9y5edISA2ni4a2e1e5EWe61d3NKopFKczJ8ugiRa2e3AcBo/LLfa", Arrays.asList("admin", "ROLE_hr"));
        } else if (roots.equals(username)) {
//            return User.withUsername(admin).password("{noop}123456").authorities("ROLE_hr", "dept").build();
            return new LoginUser(username,"$2a$10$rMFpvKv0cqzYP4AeYFhcjeDC9osocmLTChmi.daXLJ0WR3dS8Zmvy", Arrays.asList("ROLE_hr", "dept"));
        }
        throw new UsernameNotFoundException("用户不存在");
    }
}
