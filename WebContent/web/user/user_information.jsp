<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.ArrayList, bean.TDBDBean"%>

<!DOCTYPE html>
<html>

<head>
<%@ include file="../sogo/head.jsp"%>
<title>ユーザー情報</title>
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


		<h1>ユーザー情報</h1>
		<form action="/ToDoByDeath/UserDUControlServlet">

			<div class="tablediv">
				<table class="ichiran-tb">

					<tr>
						<th>ユーザ名</th>
						<th>メールアドレス</th>
						<th>パスワード</th>
					</tr>

					<tr>
						<td><input type="text"
							value=<%=session.getAttribute("name")%> readonly></td>
						<td><input type="email"
							value=<%=session.getAttribute("email")%> readonly></td>
						<td><input type="password"
							value=<%=session.getAttribute("password")%> readonly></td>
					</tr>

				</table>
			</div>

			<div class="btn">
				<input type="submit" name="action" value="基本情報変更"> <input
					type="submit" name="action" value="パスワード変更">
			</div>

		</form>
		<div class="btn">
			<input type="submit" name="return" value="戻る"
				onclick="location.href='/ToDoByDeath/MenuServlet'">
		</div>
	</div>
	<%@ include file="../sogo/footer.jsp" %>
</body>

</html>