package user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/PasswordControlServlet")
public class PasswordControlServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		HttpSession session = request.getSession(true);

		int id = (Integer)session.getAttribute("id");
		String name = (String)session.getAttribute("name");
		String email= (String)session.getAttribute("email");
		String password =(String)session.getAttribute("password");

		String error = null;
		RequestDispatcher rd = request.getRequestDispatcher("/web/sogo/error.jsp");
		UserDAO dao = new UserDAO();

		if(request.getParameter("before") == ""
				|| request.getParameter("after1") == ""
				|| request.getParameter("after2") == "") {

			error = "空欄があります";
			request.setAttribute("error", error);
			rd = request.getRequestDispatcher("/web/user/password.jsp");

		} else if (password.equals(request.getParameter("before"))) {

			if(request.getParameter("after1").equals(request.getParameter("after2"))) {
				dao.update(id, name, email, request.getParameter("after1"));
				request.setAttribute("message", "更新しました");
				rd = request.getRequestDispatcher("/MenuServlet");
			} else {
				error = "一度目の入力と二度目の入力が異なっています";
				request.setAttribute("error", error);
				rd = request.getRequestDispatcher("/web/user/password.jsp");
			}

		} else {
			error = "パスワードが間違っています";
			request.setAttribute("error", error);
			rd = request.getRequestDispatcher("/web/user/password.jsp");
		}


		rd.forward(request, response);
	}
}
