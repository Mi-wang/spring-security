package cn.wolfcode.security.handler;

import cn.wolfcode.utils.JsonUtils;
import cn.wolfcode.utils.ServletUtils;
import cn.wolfcode.vo.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author wby
 * @version 1.0
 * @date 2022/11/4 10:56
 */
@Slf4j
@Component
public class UnauthenticatedEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        if (exception instanceof UsernameNotFoundException) {
            log.warn("[登录异常处理] 用户名错误");
        } else if (exception instanceof BadCredentialsException) {
            log.warn("[登录异常处理] 密码错误");
        }

        JsonResult<Object> result = JsonResult.failed(HttpStatus.UNAUTHORIZED.value(), "请登录后访问");
        String json = JsonUtils.toJson(result);
        ServletUtils.renderString(response,json);
    }
}
