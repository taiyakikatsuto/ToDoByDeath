<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="head.jsp"%>
<title>ToDoByDeath</title>
</head>

<body>

	<div class="top-container">
		<%if (request.getAttribute("message") != null && request.getAttribute("root") == null) {%>

		<div class="message">
			<%=request.getAttribute("message")%>
		</div>

		<% } %>

		<div>
			<img class="topimg" alt="topimg" src="/ToDoByDeath/img/logo2.png">
		</div>

		<span class="title">～死ぬまでにやりたいこと 管理アプリ～</span>

	</div>


	<div class="btn">
	<div class="btncontainer">
		<form action="/ToDoByDeath/LoginServlet">
			<button class="button">ログイン</button>
		</form>
		<!--  <form action="/ToDoByDeath/SignupServlet">-->
			<button id="login-show">新規登録</button>
	</div>
	</div>

	<div class="login-modal-wrapper" id="login-modal">
	    <div class="modal">
	     <div class="close-modal">
       		 <i class="fa fa-2x fa-times"></i>
   		 </div>
	      <div id="login-form">
	        <h2>Emailログイン</h2>
	        <form action="#">
	          <input class="form-control" type="text" placeholder="メールアドレス">
	          <input class="form-control" type="password" placeholder="パスワード">
	          <div id="submit-btn">ログイン</div>
	        </form>
	      </div>
	    </div>
  	</div>



<script type="text/javascript" src="${pageContext.request.contextPath}/js/test.js"></script>
</body>
</html>