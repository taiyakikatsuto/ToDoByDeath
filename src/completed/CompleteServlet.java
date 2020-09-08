
package completed;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import process.ProcessDAO;
import tdbd.TDBDDAO;
import user.UserDAO;


@WebServlet("/CompleteServlet")
public class CompleteServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		HttpSession session = request.getSession(true);
		TDBDDAO tdbd_dao = new TDBDDAO();
		ProcessDAO process_dao = new ProcessDAO();
		UserDAO user_dao = new UserDAO();
		CompletedDAO completed_dao = new CompletedDAO();

		String process = request.getParameter("process");
		int tdbd_id = process_dao.getTdbd_id(process);
		String dream = tdbd_dao.getTDBD(tdbd_id);
		int user_id = user_dao.searchByName((String)session.getAttribute("current_user"));
		int age = user_dao.getAge(user_id);


		//プロセスは削除
		process_dao.delete(process_dao.searchId(process));

		//もしも基TDBDにもうプロセスがなければ
		if (process_dao.getProcessList(tdbd_id).isEmpty() ) {

			//基TDBDを削除し、叶えた夢にそれを追加する
			//追加時に現在のユーザーの年齢を取得する
			completed_dao.insert(dream, age, user_id);
			tdbd_dao.delete(tdbd_id);
			request.setAttribute("message", "またひとつ夢を叶えました！がんばってえらい！お疲れ様です！");

		} else {
			request.setAttribute("message", "完了しました！お疲れ様です！次の行動に移りましょう！");
		}



		RequestDispatcher rd = request.getRequestDispatcher("/MenuServlet");
		rd.forward(request, response);

	}

}
