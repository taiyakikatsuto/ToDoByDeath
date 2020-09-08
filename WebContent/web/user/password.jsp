<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../sogo/head.jsp"%>
<title>パスワード変更</title>
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

		<h1>パスワード変更</h1>


		<form action="/ToDoByDeath/PasswordControlServlet">
			<div>
				<table class="newtb">
					<tr>
						<td class="column">もとのパスワード：</td>
						<td><input type="password" name="before"></td>
					</tr>
					<tr></tr>
					<tr>
						<td class="column">新しいパスワード：</td>
						<td><input type="password" name="after1"></td>
					</tr>
					<tr>
						<td class="column">新しいパスワード(再入力)：</td>
						<td><input type="password" name="after2"></td>
					</tr>
				</table>
			</div>
			<div class="btn">
				<input type="submit" name="submit" value="確定">
			</div>
		</form>


		<div class="btn">
			<input type="submit" name="return" value="戻る"
				onclick="location.href='/ToDoByDeath/UserInformationServlet'">
		</div>
	</div>
	<%@ include file="../sogo/footer.jsp"%>

</body>
</html>