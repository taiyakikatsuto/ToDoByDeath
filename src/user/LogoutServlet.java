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


@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}



	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		HttpSession session = request.getSession(true);
		session.invalidate();

		Cookie cookies[] = request.getCookies();
		 for (Cookie cookie : cookies){
			 if (cookie.getName().equals("email") && !cookie.getValue().equals("") && cookie.getValue() != null){
				 cookie.setValue("");
		         //cookie.setPath("/");
		         cookie.setMaxAge(0);
		         response.addCookie(cookie);
		      }
		      if (cookie.getName().equals("password") && !cookie.getValue().equals("") && cookie.getValue() != null){
		    	  cookie.setValue("");
		    	  //cookie.setPath("/");
		          cookie.setMaxAge(0);
		          response.addCookie(cookie);
		      }
		    }
		 request.setAttribute("check", "true");
		RequestDispatcher rd = request.getRequestDispatcher("/TopServlet");
		rd.forward(request, response);
	}
}
