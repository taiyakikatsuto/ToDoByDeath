package completed;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/CompletedCreateServlet")
public class CompletedCreateServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		HttpSession session = request.getSession(true);

			String dream = null;
			int age = 0;
			int user_id = 0;

			String error;
			RequestDispatcher rd = request.getRequestDispatcher("/web/sogo/error.jsp");;

			dream = (String)session.getAttribute("dream");
			age = (Integer)session.getAttribute("age");
			user_id = (Integer)session.getAttribute("user_id");

			CompletedDAO dao = new CompletedDAO();

			if(dao.check(dream, age, user_id)) {
				dao.insert(dream, age, user_id);
				request.setAttribute("message", "登録しました");
				rd = request.getRequestDispatcher("/MenuServlet");
			} else {
				error = "この夢はもう叶えています";
				request.setAttribute("error", error);
				session.setAttribute("page", "insert");
				rd = request.getRequestDispatcher("/web/completed/completed_mod.jsp");
			}

			rd.forward(request, response);

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}