<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../sogo/head.jsp"%>
<%
	String content = null;
String link = null;
String href = null;
%>
<%
	if ("signup".equals(session.getAttribute("action"))) {
	content = "登録";
	link = "/ToDoByDeath/UserCreateServlet";
	href = "/ToDoByDeath/web/user/usermod.jsp?select=return";
} else if ("delete".equals(session.getAttribute("action"))) {
	content = "削除";
	link = "/ToDoByDeath/TopServlet";
	href = "/ToDoByDeath/ListControlServlet?select=ud";
} else if ("update".equals(session.getAttribute("action"))) {
	content = "変更";
	link = "/ToDoByDeath/UserDUControlServlet";
	href = "/ToDoByDeath/web/user/usermod.jsp";
}
%>
<title>ユーザー情報<%=content%></title>
</head>
<body>
	<%@ include file="../sogo/header.jsp"%>

	<div class="body">
		<%
			if (request.getAttribute("error") != null) {
		%>
		<div class="errormessage">
			<%=request.getAttribute("error")%>
		</div>
		<%
			}
		%>
		<h1>
			ユーザー情報<%=content%></h1>
		<h2><%=content%>します。よろしいですか？
		</h2>
		<div>
			<table class="newtb">
				<tr>
					<td class="column">ユーザー名:</td>
					<td><%=session.getAttribute("name")%></td>
				</tr>
				<tr>
					<td class="column">メールアドレス:</td>
					<td><%=session.getAttribute("email")%></td>
				</tr>
				<%
					if (!content.equals("変更")) {
				%>
				<tr>
					<td class="column">パスワード:</td>
					<td><%=session.getAttribute("password")%></td>
				</tr>
				<%
					}
				%>
			</table>
		</div>
		<div class="btn">
			<form action=<%=link%>>
				<input type="submit" name="submit" value="<%=content%>">
			</form>
		</div>
		<div class="btn">
			<input type="submit" name="return" value="戻る"
				onclick=<%="location.href='" + href + "'"%>>
		</div>
	</div>
	<%@ include file="../sogo/footer.jsp" %>
</body>
</html>