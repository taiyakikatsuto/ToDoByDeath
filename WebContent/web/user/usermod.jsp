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
	if ("signup".equals(session.getAttribute("page")) || "return".equals(request.getParameter("select"))) {
	content = "登録";
	href = "'/ToDoByDeath/TopServlet'";
} else if ("update".equals(session.getAttribute("page"))) {
	content = "変更";
	href = "'/ToDoByDeath/UserInformationServlet?flg=ud'";
}
%>
<title>ユーザー情報<%=content%></title>
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
			TDBD<%=content%></h1>


		<%
			if ("登録".equals(content)) {
		%>

		<h2>
			新規ユーザー<%=content%></h2>

		<form action="/ToDoByDeath/UserControlServlet">
			<div class="table">
				<table class="newtb">
					<tr>
						<td class="column">ユーザー名：</td>
						<td><input type="text" name="name"></td>
					</tr>

					<tr>
						<td class="column">メールアドレス：</td>
						<td><input type="email" name="email"></td>
					</tr>

					<tr>
						<td class="column">パスワード：</td>
						<td><input type="password" name="password"></td>
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
		<h2>
			ユーザー情報<%=content%></h2>

		<form action="/ToDoByDeath/UserDUControlServlet">
			<div class="table">
				<table class="newtb">
					<tr>
						<td class="column">ユーザー名：</td>
						<td><input type="text" name="name"
							value=<%=session.getAttribute("name")%>></td>
					</tr>

					<tr>
						<td class="column">メールアドレス：</td>
						<td><input type="email" name="email"
							value=<%=session.getAttribute("email")%>></td>
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
				onclick="location.href=<%=href%>">
		</div>
	</div>
	<%@ include file="../sogo/footer.jsp"%>
</body>
</html>