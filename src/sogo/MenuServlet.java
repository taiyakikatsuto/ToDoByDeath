
package sogo;

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
import process.ProcessDAO;
import user.UserDAO;


@WebServlet("/MenuServlet")
public class MenuServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		HttpSession session = request.getSession(true);
		UserDAO userdao = new UserDAO();
		ProcessDAO dao = new ProcessDAO();
		ArrayList<ProcessBean> list = new ArrayList<ProcessBean>();

		int user_id = userdao.searchByName((String)session.getAttribute("current_user"));

		list = dao.getToDo(user_id);
		session.setAttribute("ToDo", list);

		RequestDispatcher rd = request.getRequestDispatcher("/web/sogo/menu.jsp");
		rd.forward(request, response);

	}

}
