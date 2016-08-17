<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录</title>
</head>
<body>
<h1>投哪网抓取接口</h1>
<form action="/spider/touna/doLogin" method="post">
  投哪网账号:<input type="text" value="" name="username"><br>
  投哪网密码:<input type="password" value="" name="password"><br>
   投哪网密码:<input type="text" value="" name="authcode"><br>
  <input type="submit" value="登录">
</form>
</body>
</html>