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


@WebServlet("/DUControlServlet")
public class DUControlServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		HttpSession session = request.getSession(true);

		int id = 0;
		String tdbd = null;
		int priority = 0;
		int difficulty = 0;
		Errcheck e = new Errcheck();
		String error = null;
		RequestDispatcher rd = request.getRequestDispatcher("/web/sogo/error.jsp");
		TDBDDAO dao = new TDBDDAO();

		if(request.getParameter("action") != null) {
			id =  Integer.parseInt(request.getParameter("radio"));
			tdbd = dao.getTDBD(id);
			priority = dao.getPriority(id);
			difficulty = dao.getDifficulty(id);
		}

		if ("削除".equals(request.getParameter("action"))) {
			session.setAttribute("action", "delete");


				session.setAttribute("id", id);
				session.setAttribute("tdbd", tdbd);
				session.setAttribute("priority", priority);
				session.setAttribute("difficulty", difficulty);

				rd = request.getRequestDispatcher("/web/tdbd/kakunin.jsp");
		}

		if ("削除".equals(request.getParameter("submit"))) {
			id = (Integer)session.getAttribute("id");
			dao.delete(id);
			request.setAttribute("message", "削除しました");
			rd = request.getRequestDispatcher("/web/sogo/menu.jsp");
		}



		if ("変更".equals(request.getParameter("action"))) {
			session.setAttribute("action", "update");


				session.setAttribute("id", id);
				session.setAttribute("tdbd", tdbd);
				session.setAttribute("priority", priority);
				session.setAttribute("difficulty", difficulty);

				session.setAttribute("page", "update");
				rd = request.getRequestDispatcher("/web/tdbd/mod.jsp");

		}  else if ("変更".equals(request.getParameter("modsubmit"))) {
			tdbd = request.getParameter("tdbd");
			priority = Integer.parseInt(request.getParameter("priority"));
			difficulty  = Integer.parseInt(request.getParameter("difficulty"));


			if (e.Test(tdbd, priority, difficulty)) {
				session.setAttribute("tdbd", tdbd);
				session.setAttribute("priority", priority);
				session.setAttribute("difficulty", difficulty);
				rd = request.getRequestDispatcher("/web/tdbd/kakunin.jsp");

			} else {
				if(!e.notTooLong(tdbd)) {
					error = "死ぬまでにやりたいことは30文字以内で入力してください";
				} else if (!e.notNull(tdbd, priority, difficulty)) {
					error = "入力されていない項目があります";
				}
				request.setAttribute("error", error);
				rd = request.getRequestDispatcher("/web/tdbd/mod.jsp");
			}
		} else if ("変更".equals(request.getParameter("submit"))) {
			id = (Integer)session.getAttribute("id");
			tdbd = (String)session.getAttribute("tdbd");
			priority  = (Integer)session.getAttribute("priority");
			difficulty = (Integer)session.getAttribute("difficulty");

			dao.update(id, tdbd, priority, difficulty);
			request.setAttribute("message", "更新しました");
			rd = request.getRequestDispatcher("/web/sogo/menu.jsp");
		}

		if ("やるべき事を見る".equals(request.getParameter("action"))){
			request.setAttribute("tdbd_id", request.getParameter("radio"));
			rd = request.getRequestDispatcher("/ProcessForTDBDServlet");
		}

		rd.forward(request, response);
	}
}
