
package user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/UserInformationServlet")
public class UserInformationServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		HttpSession session = request.getSession(true);
		int id;
		String name;
		String email;
		String password;
		UserDAO dao = new UserDAO();

		name = (String)session.getAttribute("current_user");
		id = dao.searchByName(name);
		email = dao.getEmail(id);
		password = dao.password(id);

		session.setAttribute("id", id);
		session.setAttribute("name", name);
		session.setAttribute("email", email);
		session.setAttribute("password", password);


		RequestDispatcher rd = request.getRequestDispatcher("/web/user/user_information.jsp");
		rd.forward(request, response);

	}

}
