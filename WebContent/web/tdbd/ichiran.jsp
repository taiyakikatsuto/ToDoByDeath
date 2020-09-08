<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.ArrayList, bean.TDBDBean"%>

<!DOCTYPE html>
<html>

<head>
<%@ include file="../sogo/head.jsp"%>
<title>TDBD一覧</title>

<%
	String radio = "";
String th = "";
ArrayList<TDBDBean> list = new ArrayList<TDBDBean>();
list = (ArrayList<TDBDBean>) session.getAttribute("list");
//if("on".equals(session.getAttribute("radio"))) {
th = "<th></th>";
//}
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


		<form action="/ToDoByDeath/DUControlServlet">

		<div class="container">

			<div class="top"></div>
			<div class="tablediv">

				<p class="subject">TDBDリスト</p>

				<table border="1" style="border-collapse: collapse"
					class="ichiran-tb">

					<tr>
						<%=th%><th>内容</th>
						<th>やりたい度</th>
						<th>実現難易度</th>
					</tr>

					<%
						for (int i = 0; i < list.size(); i++) {
						String checked = null;
						if (i == 0) {
							checked = "checked";
						}
						//if("on".equals(session.getAttribute("radio"))) {
						radio = "<td><input class=\"radio\" type=\"radio\" name=\"radio\" value=" + list.get(i).getId() + " " + checked
						+ "></td>";
						//}
					%>
					<tr>
						<%=radio%><td class="tdbd"><%=list.get(i).getTDBD()%></td>
						<td><%=list.get(i).getPriority()%></td>
						<td><%=list.get(i).getDifficulty()%></td>
					</tr>
					<%
						}
					%>

				</table>
			</div>

			<div class="bottom"></div>

			</div>

			<div class="btn">
				<div class="btncontainer">
				<%
					if ("on".equals(session.getAttribute("radio"))) {
				%>
				<input type="submit" name="action" value="削除">
				<input type="submit" name="action" value="変更">
				<%
					}
				%>
				<input type="submit" name="action" value="やるべき事を見る">
				</div>
			</div>

		</form>
		<div class="btn">
			<input type="submit" name="return" value="戻る"
				onclick="location.href='/ToDoByDeath/MenuServlet'">
		</div>
	</div>

	<%@ include file="../sogo/footer.jsp"%>

</body>

</html>