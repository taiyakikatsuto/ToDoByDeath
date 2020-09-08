<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<header>
	<div class="logo">
		<a href="/ToDoByDeath/MenuServlet"><img alt="logo" src="/ToDoByDeath/img/logo2.png"></a>
	</div>

	<div class="header-menu">
		<p class="menu"><a href="/ToDoByDeath/ControlServlet?select=menu">新規TDBD登録</a></p>
		<p class="menu"><a href="/ToDoByDeath/ListControlServlet?select=ud">MyTDBDリスト</a></p>
		<p class="menu"><a href="/ToDoByDeath/ProcessListServlet?select=ud">やるべきこと一覧</a></p>
		<p class="menu"><a href="/ToDoByDeath/CompletedListServlet?select=ud">叶えた夢リスト</a></p>
	</div>

	<div class="header-icon">
		<span class="fa fa-bars fa-2x"></span>
	</div>


</header>