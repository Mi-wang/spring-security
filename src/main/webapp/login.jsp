<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    用户名：<input type="text" name="name"> <br>
    密　码：<input type="password" name="pass"> <br>
    <button type="submit">登录</button>
</form>
</body>
</html>
