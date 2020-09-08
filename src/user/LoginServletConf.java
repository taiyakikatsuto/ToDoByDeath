package user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/LoginServletConf")
public class LoginServletConf extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		String email = request.getParameter("email");
		String password = request.getParameter("password");
		System.out.println(email + password);
		RequestDispatcher rd = null;
		HttpSession session = request.getSession(true);
		Cookie cookies[] = request.getCookies();

		UserDAO dao = new UserDAO();

		 for (Cookie cookie : cookies){
			 //logout -> top -> loginの時にif文に入ってしまう 値を持って入るvalueが復活が原因
		      if (cookie.getName().equals("email") && !cookie.getValue().equals("") && cookie.getValue() != null){
		    	  email = (String)request.getAttribute("email");
		      }
		      if (cookie.getName().equals("password") && !cookie.getValue().equals("") && cookie.getValue() != null){
		    	  password = (String)request.getAttribute("password");
		      }

		 }


		if (dao.login(email, password)) {
			request.setAttribute("message", "いらっしゃい　" + dao.getName(dao.searchByEmail(email)) + "さん");
			rd = request.getRequestDispatcher("/MenuServlet");
			session.setAttribute("current_user", dao.getName(dao.searchByEmail(email)));
		} else {
			request.setAttribute("error", "メールアドレスまたはパスワードが違います");
			rd = request.getRequestDispatcher("/web/user/login.jsp");
		}

		if ("on".equals(request.getParameter("cookie"))) {
			Cookie cookie = new Cookie("email",email);
			Cookie cookie2 = new Cookie("password",password);
			cookie.setMaxAge(Integer.MAX_VALUE);
			cookie2.setMaxAge(Integer.MAX_VALUE);
			response.addCookie(cookie);
			response.addCookie(cookie2);

		}

		rd.forward(request, response);
	}
}
