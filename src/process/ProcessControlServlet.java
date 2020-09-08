package process;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.ProcessBean;
import sogo.Errcheck;
import tdbd.TDBDDAO;


@WebServlet("/ProcessControlServlet")
public class ProcessControlServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

			HttpSession session = request.getSession(true);

			int tdbd_id = 0;
			String process = null;
			int difficulty = 0;
			int _order = 0;
			TDBDDAO tdbd_dao = new TDBDDAO();
			ArrayList<ProcessBean> list = new ArrayList<ProcessBean>();
			list = (ArrayList<ProcessBean>)session.getAttribute("list");

			Errcheck e = new Errcheck();
			String error = null;
			RequestDispatcher rd = request.getRequestDispatcher("/web/process/process_mod.jsp");

			if ("menu".equals(request.getParameter("select"))) {
				request.setAttribute("root", "menu");
			}

			session.setAttribute("page", "insert");



			if ("登録".equals(request.getParameter("submit"))) {
				tdbd_id = tdbd_dao.searchId((String)session.getAttribute("tdbd"));
				process =  request.getParameter("process");
				difficulty = Integer.parseInt(request.getParameter("difficulty"));
				_order = 0;			//リストの最後の要素の_orderを取得し1増やす
				if (list.isEmpty()) {
					_order = 1;
				} else {
					_order = (list.get(0).get_order() ) + 1;
				}


				if (e.Test(process, difficulty, tdbd_id,list)) {
					session.setAttribute("tdbd_id", tdbd_id);
					session.setAttribute("process", process);
					session.setAttribute("difficulty", difficulty);
					session.setAttribute("_order", _order);
					session.setAttribute("action", "insert");

					rd = request.getRequestDispatcher("/web/process/process_kakunin.jsp");
				} else {
					if(!e.processNotTooLong(process)) {
						error = "やるべきことは30文字以内で入力してください";
					} else if (!e.processNotNull(process, difficulty)) {
						error = "入力されていない項目があります";
					} else if (!e.difficultyTest(difficulty, tdbd_id ,list)) {
						error = "難易度は前よりも低くしましょう";
					}

					request.setAttribute("error", error);
					session.setAttribute("page", "insert");
					rd = request.getRequestDispatcher("/web/process/process_mod.jsp");
				}

			}
			rd.forward(request, response);

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}