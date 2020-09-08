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
	href = "'/ToDoByDeath/web/process/process_for_tdbd.jsp'";
} else if ("update".equals(session.getAttribute("page"))) {
	content = "変更";
	href = "'/ToDoByDeath/ProcessListServlet?flg=ud'";
}
%>
<title>やるべきこと<%=content%></title>
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
			やること<%=content%></h1>
		<%
			if ("登録".equals(content)) {
		%>
		<h2><%=content%>する内容を入力しましょう
		</h2>

		<form action="/ToDoByDeath/ProcessControlServlet">
			<div class="table">
				<table class="newtb">
					<tr>
						<td class="column">内容：</td>
						<td><input type="text" name="process"></td>
					</tr>

					<tr>
						<td class="column">難易度：</td>
						<td><select name="difficulty">
								<option value="0">---選択してください---</option>
								<option value="1">1</option>
								<option value="2">2</option>
								<option value="3">3</option>
								<option value="4">4</option>
								<option value="5">5</option>
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

		<form action="/ToDoByDeath/ProcessDUControlServlet">
			<div class="table">
				<table class="newtb">
					<tr>
						<td class="column">内容:</td>
						<td><input type="text" name="process"
							value=<%=session.getAttribute("process")%>></td>
					</tr>
					<tr>
						<td class="column">難易度:</td>
						<td><select name="difficulty">
								<option value=<%=session.getAttribute("difficulty")%>><%=session.getAttribute("difficulty")%></option>
								<option value="1">1</option>
								<option value="2">2</option>
								<option value="3">3</option>
								<option value="4">4</option>
								<option value="5">5</option>
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