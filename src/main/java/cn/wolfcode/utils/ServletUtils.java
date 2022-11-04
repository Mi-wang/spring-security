package cn.wolfcode.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 20463
 * @version 1.0
 * @date 2022/11/4 10:31
 */
public class ServletUtils {

    private static final Logger log = LoggerFactory.getLogger(ServletUtils.class);

    public static void renderString(HttpServletResponse response, String json) throws IOException {
        try {
            response.setStatus(200);
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            response.getWriter().print(json);
        } catch (Exception e) {
            log.error("响应 json 数据失败", e);
        }
    }
}