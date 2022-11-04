package cn.wolfcode.security.handler;

import cn.wolfcode.utils.JsonUtils;
import cn.wolfcode.utils.SecurityUtils;
import cn.wolfcode.utils.ServletUtils;
import cn.wolfcode.vo.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.asm.Handle;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author wby
 * @version 1.0
 * @date 2022/11/4 17:08
 */
@Slf4j
@Component
public class UnauthorizedHandle implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException, ServletException {

        // 获取当前用户
        UserDetails user = SecurityUtils.getLoginUser();
        log.warn("[访问拒绝] 用户 {} 想要访问{}资源，鉴权失败，拦截该请求....",user.getUsername(),request.getRequestURI());


        int status = HttpStatus.FORBIDDEN.value();
        String json = JsonUtils.toJson(JsonResult.failed(status, "没有访问权限"));
        ServletUtils.renderString(response,json);
    }
}
