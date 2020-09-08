package user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import sogo.Errcheck;


@WebServlet("/UserDUControlServlet")
public class UserDUControlServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		HttpSession session = request.getSession(true);

		int id;
		String name;
		String email;
		String password;

		Errcheck e = new Errcheck();
		String error = null;
		RequestDispatcher rd = request.getRequestDispatcher("/web/sogo/error.jsp");
		UserDAO dao = new UserDAO();


		if ("基本情報変更".equals(request.getParameter("action"))) {
			session.setAttribute("page", "update");
			session.setAttribute("action", "update");
			rd = request.getRequestDispatcher("/web/user/usermod.jsp");
		}

		if ("変更".equals(request.getParameter("modsubmit"))) {
			name = request.getParameter("name");
			email = request.getParameter("email");
			session.setAttribute("name", name);
			session.setAttribute("email", email);
			rd = request.getRequestDispatcher("/web/user/userkakunin.jsp");
		}

		if ("変更".equals(request.getParameter("submit"))) {
			id = (Integer)session.getAttribute("id");
			name = (String)session.getAttribute("name");
			email = (String)session.getAttribute("email");
			password = (String)session.getAttribute("password");
			dao.update(id, name, email, password);
			request.setAttribute("message", "更新しました");
			rd = request.getRequestDispatcher("/MenuServlet");
		}


		if ("パスワード変更".equals(request.getParameter("action"))) {
			session.setAttribute("action", "reset");
			rd = request.getRequestDispatcher("/web/user/password.jsp");
		}

		rd.forward(request, response);
	}
}
