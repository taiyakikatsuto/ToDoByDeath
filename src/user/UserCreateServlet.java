package user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/UserCreateServlet")
public class UserCreateServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		HttpSession session = request.getSession(true);

			String name = null;
			String email = null;
			String password = null;
			String error;
			RequestDispatcher rd = null;

			name = (String)session.getAttribute("name");
			email = (String)session.getAttribute("email");
			password = (String)session.getAttribute("password");

			UserDAO dao = new UserDAO();

			if(dao.check(name, email, password)) {
				dao.insert(name, email, password);
				request.setAttribute("message", "登録しました");
				session.setAttribute("current_user", dao.getName(dao.searchByEmail(email)));
				rd = request.getRequestDispatcher("/MenuServlet");
			} else {
				error = "このユーザーはすでに登録されています";
				request.setAttribute("error", error);
				session.setAttribute("page", "signup");
				rd = request.getRequestDispatcher("/web/user/usermod.jsp");
			}

			rd.forward(request, response);

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}