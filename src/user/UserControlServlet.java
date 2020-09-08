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


@WebServlet("/UserControlServlet")
public class UserControlServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

			HttpSession session = request.getSession(true);

			String name = null;
			String email = null;
			String password = null;
			Errcheck e = new Errcheck();
			String error = null;
			RequestDispatcher rd = request.getRequestDispatcher("/web/mod.jsp");

			if ("登録".equals(request.getParameter("submit"))) {
				name =  request.getParameter("name");
				email = request.getParameter("email");
				password = request.getParameter("password");


				if (e.Test(name, email, password)) {
					session.setAttribute("name", name);
					session.setAttribute("email", email);
					session.setAttribute("password", password);
					session.setAttribute("action", "signup");

					rd = request.getRequestDispatcher("/web/user/userkakunin.jsp");
				} else {
					if(!e.userNotTooLong(name, email, password)) {
						error = "ユーザー名とパスワードは20文字以内で入力してください";
					} else if (!e.userNotNull(name, email, password)) {
						error = "入力されていない項目があります";
					} else if (!e.uniqueUser(name)) {
						error = "このユーザー名は既に使用されています";
					} else if (!e.uniqueEmail(email)) {
						error = "このメールアドレスは既に使用されています";
					}

					request.setAttribute("error", error);
					session.setAttribute("page", "signup");
					rd = request.getRequestDispatcher("/web/user/usermod.jsp");
				}

			}

			rd.forward(request, response);

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}