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


@WebServlet("/CompletedDUControlServlet")
public class CompletedDUControlServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		HttpSession session = request.getSession(true);

		int id = 0;
		String dream = null;
		int age = 0;
		int user_id = 0;

		Errcheck e = new Errcheck();
		String error = null;
		RequestDispatcher rd = request.getRequestDispatcher("/web/sogo/error.jsp");
		CompletedDAO dao = new CompletedDAO();


		if(request.getParameter("action") != null) { 	//変更、削除ボタンが押されたとき
			id =  Integer.parseInt(request.getParameter("radio"));
			dream = dao.getDream(id);
			age = dao.getAge(id);
			user_id = dao.getUser_id(id);

		}

		if ("削除".equals(request.getParameter("action"))) {
			session.setAttribute("action", "delete");


				session.setAttribute("id", id);
				session.setAttribute("dream", dream);
				session.setAttribute("age", age);
				session.setAttribute("user_id", user_id);

				rd = request.getRequestDispatcher("/web/completed/completed_kakunin.jsp");
		}

		if ("削除".equals(request.getParameter("submit"))) {
			id = (Integer)session.getAttribute("id");
			dao.delete(id);
			request.setAttribute("message", "削除しました");
			rd = request.getRequestDispatcher("/MenuServlet");
		}



		if ("変更".equals(request.getParameter("action"))) {
			session.setAttribute("action", "update");

			session.setAttribute("id", id);
			session.setAttribute("dream", dream);
			session.setAttribute("age", age);
			session.setAttribute("user_id", user_id);

			session.setAttribute("page", "update");
			rd = request.getRequestDispatcher("/web/completed/completed_mod.jsp");

		}  else if ("変更".equals(request.getParameter("modsubmit"))) {
			dream = request.getParameter("dream");
			age  = Integer.parseInt(request.getParameter("age"));

			if (e.Test(dream, age)) {
				session.setAttribute("dream", dream);
				session.setAttribute("age", age);
				rd = request.getRequestDispatcher("/web/completed/completed_kakunin.jsp");

			} else {
				if(!e.CompletedNotTooLong(dream)) {
					error = "30文字以内で入力してください";
				} else if (!e.CompletedNotNull(dream, age)) {
					error = "入力されていない項目があります";
				}

				request.setAttribute("error", error);
				rd = request.getRequestDispatcher("/web/completed/completed_mod.jsp");
			}
		} else if ("変更".equals(request.getParameter("submit"))) {
			id = (Integer)session.getAttribute("id");
			dream = (String)session.getAttribute("dream");
			age = (Integer)session.getAttribute("age");
			user_id = (Integer)session.getAttribute("user_id");

			dao.update(id, dream, age, user_id);
			request.setAttribute("message", "更新しました");
			rd = request.getRequestDispatcher("/MenuServlet");
		}

		rd.forward(request, response);
	}
}
