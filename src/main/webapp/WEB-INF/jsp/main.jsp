<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList,model.Member" %>

<% ArrayList<Member> memberList = (ArrayList<Member>) session.getAttribute("memberList"); %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>OsewakaiMaker</title>
</head>
<body>
<div style="display:flex">
<div>
<h1>メンバー登録</h1>

<form action="/OsewakaiMaker/Main" method="post">
あだ名:<input type="text" name="name"><br>
パーティ:<input type="text" name="party"><br>
期:<br>
<input type="radio" name="grade" value="4">4期<br>
<input type="radio" name="grade" value="3">3期<br>
<input type="radio" name="grade" value="2">2期<br>
<input type="radio" name="grade" value="1">1期<br>
性別:<br>
<input type="radio" name="sex" value="0">男<br>
<input type="radio" name="sex" value="1">女<br>
表活経験:<br>
<input type="radio" name="exp" value="1">あり<br>
<input type="radio" name="exp" value="0">なし<br>

<input type="submit" value="追加"></form>
</div>


<div>
<!-- お世話会数の入力を受け、Showサーブレットに送る -->
<form action="/OsewakaiMaker/Show" method="post">
お世話会の数<input type="number" name="number" required="required"><br>
<input type= "submit" value="このメンバーでお世話会をつくる">
</form>


<% if(memberList != null) {%>
<h2>メンバーリスト</h2>
<% for(Member member : memberList) {%>
<%= member.getGrade() + "期 " + member.getName() + " " + member.getParty() + "P<br>" %>
<% } %>
<% } %><br>



</div>
</div>
</body>
</html>