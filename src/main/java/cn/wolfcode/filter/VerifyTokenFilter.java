package cn.wolfcode.filter;

import cn.wolfcode.domain.LoginUser;
import cn.wolfcode.utils.JsonUtils;
import cn.wolfcode.utils.SecurityUtils;
import cn.wolfcode.utils.ServletUtils;
import cn.wolfcode.utils.TokenUtils;
import cn.wolfcode.vo.JsonResult;
import com.fasterxml.jackson.core.filter.TokenFilter;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * @author wby
 * @version 1.0
 * @date 2022/11/4 14:56
 */
@Component
public class VerifyTokenFilter extends HttpFilter {

    private static final List<String> WHITE_LIST = Arrays.asList("/login","/error");

    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        // 判断是否是登录请求，是就放行
        String uri = request.getRequestURI();
        if (WHITE_LIST.contains(uri)) {
            chain.doFilter(request, response);
            return;
        }
        String token = request.getHeader("X-Token");
        if (StringUtils.hasText(token)) {
            LoginUser user = TokenUtils.getUser(token);
            if (user != null) {
                // 3  告知security 用户已经登录了
                Authentication authentication = new UsernamePasswordAuthenticationToken(user,user.getPassword(),user.getAuthorities());
                SecurityUtils.setAuthentication(authentication);
                // 4  正却就放行
                chain.doFilter(request, response);
                return;
            }
        }

        // 5 错误就拦截并响应认证失败
        JsonResult<Object> result = JsonResult.failed(HttpStatus.UNAUTHORIZED.value(), "请登录后再访问");
        String json = JsonUtils.toJson(result);
        ServletUtils.renderString(response,json);
    }
}
