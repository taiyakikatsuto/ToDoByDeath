package completed;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import sogo.Errcheck;
import user.UserDAO;


@WebServlet("/CompletedControlServlet")
public class CompletedControlServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

			HttpSession session = request.getSession(true);

			String dream = null;
			int age = 0;
			int user_id = 0;

			UserDAO dao = new UserDAO();

			Errcheck e = new Errcheck();
			String error = null;
			RequestDispatcher rd = request.getRequestDispatcher("/web/completed/completed_mod.jsp");

			if ("menu".equals(request.getParameter("select"))) {
				request.setAttribute("root", "menu");
			}

			session.setAttribute("page", "insert");



			if ("登録".equals(request.getParameter("submit"))) {
				dream =  request.getParameter("dream");
				age = Integer.parseInt(request.getParameter("age"));
				user_id = dao.searchByName((String)session.getAttribute("current_user"));


				if (e.Test(dream, age)) {
					session.setAttribute("dream", dream);
					session.setAttribute("age", age);
					session.setAttribute("user_id", user_id);

					session.setAttribute("action", "insert");

					rd = request.getRequestDispatcher("/web/completed/completed_kakunin.jsp");
				} else {
					if(!e.CompletedNotTooLong(dream)) {
						error = "30文字以内で入力してください";
					} else if (!e.CompletedNotNull(dream, age)) {
						error = "入力されていない項目があります";
					}

					request.setAttribute("error", error);
					session.setAttribute("page", "insert");
					rd = request.getRequestDispatcher("/web/completed/completed_mod.jsp");
				}

			}
			rd.forward(request, response);

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}