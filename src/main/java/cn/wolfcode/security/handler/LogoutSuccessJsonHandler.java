package cn.wolfcode.security.handler;

import cn.wolfcode.domain.LoginUser;
import cn.wolfcode.utils.JsonUtils;
import cn.wolfcode.utils.ServletUtils;
import cn.wolfcode.utils.TokenUtils;
import cn.wolfcode.vo.JsonResult;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author wby
 * @version 1.0
 * @date 2022/11/4 10:31
 */
@Component
public class LogoutSuccessJsonHandler implements LogoutSuccessHandler {


    @Override
    public void onLogoutSuccess(HttpServletRequest request,
                                HttpServletResponse response,
                                Authentication authentication)
            throws IOException, ServletException {

        LoginUser user = (LoginUser)authentication.getPrincipal();

        // 清楚map 中的用户数据
        TokenUtils.remove(user.getToken());

        // 返回 Json 字符串
        String json = JsonUtils.toJson(JsonResult.success().entrySet());
        ServletUtils.renderString(response, json);
    }
}
