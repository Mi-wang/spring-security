package cn.wolfcode.utils;


import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author wby
 * @version 1.0
 * @date 2022/11/3 17:21
 */
public class SecurityUtils {

    /**
     * 获取用户
     **/
    public static UserDetails getLoginUser() {
        try {
            return (UserDetails) getAuthentication().getPrincipal();
        } catch (Exception e) {
            throw new AccountExpiredException("获取用户信息异常");
        }
    }

    /**
     * 获取Authentication
     */
    public static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    /**
     * 生成BCryptPasswordEncoder密码
     *
     * @param password 密码
     * @return 加密字符串
     */
    public static String encryptPassword(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }
}
