package tdbd;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import user.UserDAO;


@WebServlet("/CreateServlet")
public class CreateServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		HttpSession session = request.getSession(true);

			String tdbd = null;
			int priority = 0;
			int difficulty = 0;
			String error;
			RequestDispatcher rd = null;

			tdbd = (String)session.getAttribute("tdbd");
			priority = (Integer)session.getAttribute("priority");
			difficulty = (Integer)session.getAttribute("difficulty");

			TDBDDAO dao = new TDBDDAO();
			UserDAO userdao = new UserDAO();

			if(dao.check(tdbd, priority, difficulty)) {
				dao.insert(tdbd, priority, difficulty, userdao.searchByName((String)session.getAttribute("current_user")));
				request.setAttribute("message", "登録しました");
				rd = request.getRequestDispatcher("/web/sogo/menu.jsp");
			} else {
				error = "このTDBDはすでに登録されています";
				request.setAttribute("error", error);
				session.setAttribute("page", "insert");
				rd = request.getRequestDispatcher("/web/tdbd/mod.jsp");
			}

			rd.forward(request, response);

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}