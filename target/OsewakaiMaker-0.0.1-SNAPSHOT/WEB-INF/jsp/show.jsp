<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, model.Member, model.ShowMembersLogic" %>

<% ArrayList<ArrayList<Member>> groups = (ArrayList<ArrayList<Member>>) session.getAttribute("resultList"); %>
<% ShowMembersLogic sml = new ShowMembersLogic(); %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>振り分け結果</title>
</head>
<body>
<form action="/OsewakaiMaker/Show" method="post" id="form" name="form">
<button  id="reset" form="form" value="reset">リセット</button>
</form>




<% for(int i = 0; i < groups.size();i++) { %>

お世話会<%= i+1%><br>

<% for(int j = 0; j < groups.get(i).size(); j++){ %>

<% Member member = (groups.get(i).get(j));%>
<%= member.getGrade() + "期 " + member.getName() + " " + member.getParty() + "P<br>" %>

<%}%>
<br>
<%}%>


<script>
	var reset_button = document.getElementById('reset');
	reset_button.addEventListener('click',function(){
		document.form.submit();
	})
</script>


</body>
</html>