package process;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import sogo.Errcheck;


@WebServlet("/ProcessDUControlServlet")
public class ProcessDUControlServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		HttpSession session = request.getSession(true);

		int id = 0;
		int tdbd_id = 0;
		String process = null;
		int difficulty = 0;
		int _order = 0;

		Errcheck e = new Errcheck();
		String error = null;
		RequestDispatcher rd = request.getRequestDispatcher("/web/sogo/error.jsp");
		ProcessDAO dao = new ProcessDAO();


		if(request.getParameter("action") != null) { 	//変更、削除ボタンが押されたとき
			id =  Integer.parseInt(request.getParameter("radio"));
			process = dao.getProcess(id);
			tdbd_id = dao.getTdbd_id(process);
			difficulty = dao.getDifficulty(id);
			_order = dao.get_order(id);

		}

		if ("削除".equals(request.getParameter("action"))) {
			session.setAttribute("action", "delete");


				session.setAttribute("id", id);
				session.setAttribute("tdbd_id", tdbd_id);
				session.setAttribute("process", process);
				session.setAttribute("difficulty", difficulty);
				session.setAttribute("_order", _order);

				rd = request.getRequestDispatcher("/web/process/process_kakunin.jsp");
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
			session.setAttribute("tdbd_id", tdbd_id);
			session.setAttribute("process", process);
			session.setAttribute("difficulty", difficulty);
			session.setAttribute("_order", _order);

			session.setAttribute("page", "update");
			rd = request.getRequestDispatcher("/web/process/process_mod.jsp");

		}  else if ("変更".equals(request.getParameter("modsubmit"))) {
			process = request.getParameter("process");
			int befor_difficulty = (Integer)session.getAttribute("difficulty");
			difficulty  = Integer.parseInt(request.getParameter("difficulty"));

			if (e.udTest(process, difficulty, befor_difficulty)) {
				session.setAttribute("process", process);
				session.setAttribute("difficulty", difficulty);
				rd = request.getRequestDispatcher("/web/process/process_kakunin.jsp");

			} else {
				if(!e.processNotTooLong(process)) {
					error = "やるべきことは30文字以内で入力してください";
				} else if (!e.processNotNull(process, difficulty)) {
					error = "入力されていない項目があります";
				} else if (!e.difficultyTest(difficulty, befor_difficulty)) {
					error = "前のやることよりも易しい難易度にしましょう";
				}
				request.setAttribute("error", error);
				rd = request.getRequestDispatcher("/web/process/process_mod.jsp");
			}
		} else if ("変更".equals(request.getParameter("submit"))) {
			id = (Integer)session.getAttribute("id");
			tdbd_id = (Integer)session.getAttribute("tdbd_id");
			process = (String)session.getAttribute("process");
			difficulty = (Integer)session.getAttribute("difficulty");
			_order = (Integer)session.getAttribute("_order");

			dao.update(id, process, difficulty);
			request.setAttribute("message", "更新しました");
			rd = request.getRequestDispatcher("/MenuServlet");
		}

		if ("やるべき事を見る".equals(request.getParameter("action"))){
			request.setAttribute("tdbd_id", request.getParameter("radio"));
			rd = request.getRequestDispatcher("/ProcessForTDBDServlet");
		}

		rd.forward(request, response);
	}
}
