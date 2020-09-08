package completed;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.CompletedBean;
import user.UserDAO;


@WebServlet("/CompletedListServlet")
public class CompletedListServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		HttpSession session = request.getSession(true);

		ArrayList<CompletedBean> list = new ArrayList<CompletedBean>();
		CompletedDAO dao = new CompletedDAO();
		UserDAO userdao = new UserDAO();

		list = dao.getList(userdao.searchByName((String)session.getAttribute("current_user")));
		session.setAttribute("list", list);

		if("show".equals(request.getParameter("select"))) {
			session.setAttribute("radio", "off");
		} else /*if("ud".equals(request.getParameter("select")))*/ {
			session.setAttribute("radio", "on");
		}

		RequestDispatcher rd = request.getRequestDispatcher("/web/completed/completed_list.jsp");
		rd.forward(request, response);

	}

}
