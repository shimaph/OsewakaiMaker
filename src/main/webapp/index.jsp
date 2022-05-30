<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>OsewakaiMaker β</title>
</head>
<body>
<h1>Osewakai-Maker</h1>
<form action="/OsewakaiMaker/Login" method="post">
ユーザ名:<input type="text" name="name"><br>
PASS:<input type="password" name="pass"><br>
<input type="submit" value="ログイン"></form><br>
<p>${errorMsg}</p>

<p>Copyright © 2022 yurei All Rights Reserved.<p>
</body>
</html>