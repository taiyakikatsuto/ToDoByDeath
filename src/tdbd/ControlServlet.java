package tdbd;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import sogo.Errcheck;


@WebServlet("/ControlServlet")
public class ControlServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

			HttpSession session = request.getSession(true);

			int id = 0;
			String tdbd = null;
			int priority = 0;
			int difficulty = 0;
			Errcheck e = new Errcheck();
			String error = null;
			RequestDispatcher rd = request.getRequestDispatcher("/web/tdbd/mod.jsp");

			if ("menu".equals(request.getParameter("select"))) {
				request.setAttribute("root", "menu");
			}

			session.setAttribute("page", "insert");



			if ("登録".equals(request.getParameter("submit"))) {
				tdbd =  request.getParameter("tdbd");
				priority = Integer.parseInt(request.getParameter("priority"));
				difficulty = Integer.parseInt(request.getParameter("difficulty"));


				if (e.Test(tdbd, priority, difficulty)) {
					session.setAttribute("id", id);
					session.setAttribute("tdbd", tdbd);
					session.setAttribute("priority", priority);
					session.setAttribute("difficulty", difficulty);
					session.setAttribute("action", "insert");

					rd = request.getRequestDispatcher("/web/tdbd/kakunin.jsp");
				} else {
					if(!e.notTooLong(tdbd)) {
						error = "死ぬまでにやりたいことは30文字以内で入力してください";
					} else if (!e.notNull(tdbd, priority, difficulty)) {
						error = "入力されていない項目があります";
					}

					request.setAttribute("error", error);
					session.setAttribute("page", "insert");
					rd = request.getRequestDispatcher("/web/tdbd/mod.jsp");
				}

			}
			rd.forward(request, response);

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}