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
	if ("insert".equals(session.getAttribute("action"))) {
	content = "登録";
	link = "/ToDoByDeath/CompletedCreateServlet";
	href = "/ToDoByDeath/web/completed/completed_mod.jsp?select=return";
} else if ("delete".equals(session.getAttribute("action"))) {
	content = "削除";
	link = "/ToDoByDeath/CompletedDUControlServlet";
	href = "/ToDoByDeath/CompletedListServlet?select=ud";
} else if ("update".equals(session.getAttribute("action"))) {
	content = "変更";
	link = "/ToDoByDeath/CompletedDUControlServlet";
	href = "/ToDoByDeath/web/completed/completed_mod.jsp";
}
%>
<title>叶えた夢<%=content%></title>
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
			叶えた夢<%=content%></h1>
		<h2><%=content%>します。よろしいですか？
		</h2>
		<div>
			<table class="newtb">
				<tr>
					<td class="column">内容:</td>
					<td><%=session.getAttribute("dream")%></td>
				</tr>
				<tr>
					<td class="column">当時の年齢:</td>
					<td><%=session.getAttribute("age")%></td>
				</tr>

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

	<%@ include file="../sogo/footer.jsp"%>

</body>
</html>