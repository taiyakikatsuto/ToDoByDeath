<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<%@ include file="../sogo/head.jsp"%>
<title>ログイン</title>
</head>

<body>
	<%if(request.getAttribute("error") != null && request.getAttribute("root") == null){ %>
	<div class="errormessage">
		<%= request.getAttribute("error") %>
	</div>
	<% } %>

	<h1>ログイン画面</h1>
	<form method="post" action="/ToDoByDeath/LoginServletConf">
		<div>
			<table class="newtb">
				<tr>
					<td><input type="email" size="50" name="email" placeholder="メールアドレス"></td>
				</tr>
				<tr>
					<td><input type="password" size="20" name="password" placeholder="パスワード"></td>
				</tr>
			</table>
			<br>
		</div>
		<p><input class="checkbox" type="checkbox" name="cookie" value="on">次回からログインを省略する</p>
		<div class="btn">
			<input type="submit" name="submit" value="ログイン">
		</div>
	</form>
	<div class="btn">
		<input type="submit" name="return" value="戻る"
			onclick=<%= "location.href='/ToDoByDeath/TopServlet'" %>>
	</div>


</body>
</html>