<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../sogo/head.jsp"%>
<%
	String content = null;
String href = null;
%>
<%
	if ("insert".equals(session.getAttribute("page")) || "return".equals(request.getParameter("select"))) {
	content = "登録";
	href = "'/ToDoByDeath/CompletedListServlet'";
} else if ("update".equals(session.getAttribute("page"))) {
	content = "変更";
	href = "'/ToDoByDeath/CompletedListServlet?flg=ud'";
}
%>
<title>叶えた夢<%=content%></title>
</head>
<body>

	<%@ include file="../sogo/header.jsp"%>

	<div class="body">

		<%
			if (request.getAttribute("error") != null && request.getAttribute("root") == null) {
		%>
		<div class="errormessage">
			<%=request.getAttribute("error")%>
		</div>
		<%
			}
		%>

		<h1>
			叶えた夢<%=content%></h1>
		<%
			if ("登録".equals(content)) {
		%>
		<h2><%=content%>する内容を入力しましょう
		</h2>

		<form action="/ToDoByDeath/CompletedControlServlet">
			<div class="table">
				<table class="newtb">
					<tr>
						<td class="column">内容：</td>
						<td><input type="text" name="dream"></td>
					</tr>

					<tr>
						<td class="column">当時の年齢：</td>
						<td><select name="age">
								<option value="-1">---選択してください---</option>
								<% for(int i = 0; i < 100; i++) { %>
									<option value=<%= i %>><%= i %></option>
								<% } %>
						</select></td>
					</tr>
				</table>
			</div>
			<div class="btn">
				<input type="submit" name="submit" value="<%=content%>">
			</div>
		</form>
		<%
			}
		%>

		<%
			if ("変更".equals(content)) {
		%>

		<form action="/ToDoByDeath/CompletedDUControlServlet">
			<div class="table">
				<table class="newtb">
					<tr>
						<td class="column">内容：</td>
						<td><input type="text" name="dream" value=<%= session.getAttribute("dream") %>></td>
					</tr>

					<tr>
						<td class="column">当時の年齢：</td>
						<td><select name="age">
								<option value="-1"><%= session.getAttribute("age") %></option>
								<% for(int i = 0; i < 100; i++) { %>
									<option value=<%= i %>><%= i %></option>
								<% } %>
						</select></td>
					</tr>
				</table>
			</div>
			<div class="btn">
				<input type="submit" name="modsubmit" value="<%=content%>">
			</div>
		</form>
		<%
			}
		%>

		<div class="btn">
			<input type="submit" name="return" value="戻る"
				onclick="location.href=<%= href %>">
		</div>
	</div>

	<%@ include file="../sogo/footer.jsp"%>

</body>
</html>