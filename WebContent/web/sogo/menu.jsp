<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.util.ArrayList, bean.ProcessBean"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../sogo/head.jsp" %>
<title>メニュー</title>

<%
	ArrayList<ProcessBean> list = new ArrayList<ProcessBean>();
	list = (ArrayList<ProcessBean>)session.getAttribute("ToDo");
%>
</head>
<body>
<%@ include file="../sogo/header.jsp" %>

<div class="body">


	<%if(request.getAttribute("message") != null && request.getAttribute("root") == null){ %>
		<div class="message">
			<%= request.getAttribute("message") %>
		</div>
	<% } %>



	<div class="container">
	<p class="subtitle"><%= session.getAttribute("current_user") %>さんが今やるべきこと</p>

				<div class="todo-container">
				<% for(int i = 0; i < list.size(); i++) {%>
					<div class="todo">
						<div class="top"></div>
						<div class="middle">
							<div class="wrapper">
								<div class="process" id="<%=i%>">
									<%= list.get(i).getProcess() %>
								</div>
								<div class="numbers">
									<span>実行難易度：<%= list.get(i).getDifficulty() %></span>
									<span>重要度：<%= list.get(i).getPriority() %></span>
								</div>
								<div class="tdbds">
									for <%= list.get(i).getTDBD() %>
								</div>
								<div class="btncontainer">
									<!--  <form action="/ToDoByDeath/MenuServlet">-->
										<button class="login-show" id=<%= list.get(i).getProcess() %>>完了！</button>

								</div>
							</div>
						</div>
						<div class="bottom"></div>
					</div>
					<div class="login-modal-wrapper" id="login-modal">
							<div class="modal">
								<div class="close-modal">
									<i class="fa fa-2x fa-times"></i>
								</div>
								<div id="login-form">
									<h2>完了しますか？</h2>
									<form action="/ToDoByDeath/CompleteServlet">
										<input type="text" name="process" id="kanryo" readonly>
										<div class="btncontainer">
											<button>完了する！</button>
										</div>
									</form>
								</div>
							</div>
						</div>
				<% } %>
				</div>
			</div>
	</div>

	<%@ include file="../sogo/footer.jsp" %>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/test.js"></script>
</body>
</html>