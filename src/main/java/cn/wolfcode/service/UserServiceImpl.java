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
@Service
public class UserServiceImpl implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 基于传入的username 去数据库查询用户对象
        String admin = "admin";
        String xiaoliu = "xiaoliu";

        if (admin.equals(username)) {
//            return User.withUsername(admin).password("{noop}123456").authorities("admin", "ROLE_hr").build();
          return new LoginUser(username,"{noop}123", Arrays.asList("admin", "ROLE_hr"));
        } else if (xiaoliu.equals(username)) {
//            return User.withUsername(admin).password("{noop}123456").authorities("ROLE_hr", "dept").build();
            return new LoginUser(username,"{noop}123", Arrays.asList("ROLE_hr", "dept"));
        }
        throw new UsernameNotFoundException("用户不存在");
    }
}
