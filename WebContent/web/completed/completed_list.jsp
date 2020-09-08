<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.ArrayList, bean.CompletedBean"%>

<!DOCTYPE html>
<html>

<head>
<%@ include file="../sogo/head.jsp"%>
<title>叶えた夢一覧</title>

<%
	String radio = "";
String th = "";
ArrayList<CompletedBean> list = new ArrayList<CompletedBean>();
list = (ArrayList<CompletedBean>) session.getAttribute("list");
th = "<th></th>";

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


		<form action="/ToDoByDeath/CompletedDUControlServlet">

		<div class="container">

			<div class="top"></div>
			<div class="tablediv">

				<p class="subject">叶えた夢リスト</p>

				<table border="1" style="border-collapse: collapse"
					class="ichiran-tb">

					<tr>
						<%=th%><th>内容</th>
						<th>当時の年齢</th>
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
						<%=radio%><td class="tdbd"><%=list.get(i).getDream()%></td>
						<td><%=list.get(i).getAge()%></td>
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
				</div>
			</div>

		</form>
		<div class="btn">
			<input type="submit" name="action" value="追加する"
				onclick="location.href='/ToDoByDeath/CompletedControlServlet'">
			<input type="submit" name="return" value="戻る"
				onclick="location.href='/ToDoByDeath/MenuServlet'">
		</div>
	</div>

	<%@ include file="../sogo/footer.jsp"%>

</body>

</html>