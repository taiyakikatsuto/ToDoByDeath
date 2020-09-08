<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.ArrayList, bean.ProcessBean"%>

<!DOCTYPE html>
<html>

<head>
<%@ include file="../sogo/head.jsp"%>
<title>やること一覧</title>

<%
	String radio = "";
String th = "";
ArrayList<ProcessBean> list = new ArrayList<ProcessBean>();
list = (ArrayList<ProcessBean>) session.getAttribute("list");
if ("on".equals(session.getAttribute("radio"))) {
	th = "<th></th>";
}
%>

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


		<div class="container">

		<div class="top"></div>

		<form action="/ToDoByDeath/ProcessDUControlServlet">

			<div class="tablediv">
				<p class="subject">
					「<%=session.getAttribute("tdbd")%>」に向けてやるべきこと一覧
				</p>

				<table border="1" style="border-collapse: collapse"
					class="ichiran-tb">

					<tr>
						<%=th%><th>内容</th>
						<th>実行難易度</th>
						<th>つながる目標</th>
						<th>やりたい度</th>
					</tr>

					<%
						for (int i = 0; i < list.size(); i++) {
						String checked = null;
						if (i == 0) {
							checked = "checked";
						}
						if ("on".equals(session.getAttribute("radio"))) {
							radio = "<td><input class=\"radio\" type=\"radio\" name=\"radio\" value=" + list.get(i).getId() + " " + checked
							+ "></td>";
						}
					%>
					<tr>
						<%=radio%>
						<td><%=list.get(i).getProcess()%></td>
						<td><%=list.get(i).getDifficulty()%></td>
						<td><%=list.get(i).getTDBD()%></td>
						<td><%=list.get(i).getPriority()%></td>
					</tr>
					<%
						}
					%>

				</table>
			</div>

			<div class="bottom"></div>

			</div>

			<div class="btn">
				<%
					if ("on".equals(session.getAttribute("radio"))) {
				%>
				<input type="submit" name="action" value="削除"> <input
					type="submit" name="action" value="変更">
				<%
					}
				%>
			</div>

		</form>
		<div class="btn">
			<input type="submit" name="return" value="やること追加"
				onclick="location.href='/ToDoByDeath/ProcessControlServlet'">
			<input type="submit" name="return" value="戻る"
				onclick="location.href='/ToDoByDeath/ListControlServlet'">
		</div>
	</div>

	<%@ include file="../sogo/footer.jsp"%>

</body>

</html>