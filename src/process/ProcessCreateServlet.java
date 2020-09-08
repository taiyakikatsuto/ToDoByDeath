package process;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/ProcessCreateServlet")
public class ProcessCreateServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		HttpSession session = request.getSession(true);

			int tdbd_id = 0;
			String process = null;
			int difficulty = 0;
			int _order = 0;

			String error;
			RequestDispatcher rd = null;

			tdbd_id = (Integer)session.getAttribute("tdbd_id");
			process = (String)session.getAttribute("process");
			difficulty = (Integer)session.getAttribute("difficulty");
			_order = (Integer)session.getAttribute("_order");

			ProcessDAO dao = new ProcessDAO();

			if(dao.check(process, difficulty)) {
				dao.insert(tdbd_id, process, difficulty, _order);
				request.setAttribute("message", "登録しました");
				rd = request.getRequestDispatcher("/MenuServlet");
			} else {
				error = "このやるべきことはすでに登録されています";
				request.setAttribute("error", error);
				session.setAttribute("page", "insert");
				rd = request.getRequestDispatcher("/web/process/process_mod.jsp");
			}

			rd.forward(request, response);

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}