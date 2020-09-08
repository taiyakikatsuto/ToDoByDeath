<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<footer>
	<div class="footer-container">

		<div class="footer-left">
			<p>ログイン中のユーザー<a href="/ToDoByDeath/UserInformationServlet"><%= session.getAttribute("current_user") %></a></p>
		</div>

		<div class="footer-right">
			<p><a class="logout" href="/ToDoByDeath/LogoutServlet">ログアウト</a></p>
		</div>
	</div>

</footer>