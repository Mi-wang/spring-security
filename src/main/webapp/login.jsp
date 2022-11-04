<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>登录页面</title>
</head>
<body>
<h1>自定义登录页</h1>
<form action="/login" method="post">
    <span style="color: red;">${SPRING_SECURITY_LAST_EXCEPTION.message}</span> <br>
    <%-- 从 session 中的 _csrf 对象中获取服务端生成的 token，提交的时候 CsrfFilter 会进行校验 --%>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
    用户名：<input type="text" name="username"> <br>
    密　码：<input type="password" name="password"> <br>
    记住我：<input type="checkbox" name="rememberMe"> <br>
    　　　　<button type="submit">登录</button>
</form>
</body>
</html>