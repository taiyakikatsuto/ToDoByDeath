
package sogo;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet("/TopServlet")
public class TopServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		RequestDispatcher rd = request.getRequestDispatcher("/web/sogo/top.jsp");
		String email = null;
		String password = null;
		Cookie cookies[] = request.getCookies();
		boolean flg1 = false;
		boolean flg2 = false;

		if(cookies != null) {

			for (Cookie cookie : cookies){

				if (cookie.getName().equals("email") && !cookie.getValue().equals("") && cookie.getValue() != null){
					email = cookie.getValue();
					flg1 = true;
				}
				if (cookie.getName().equals("password") && !cookie.getValue().equals("") && cookie.getValue() != null){
					password = cookie.getValue();
					flg2 = true;
				}

				if (flg1 && flg2) {
					request.setAttribute("email", email);
				    request.setAttribute("password", password);
				    rd = request.getRequestDispatcher("/LoginServletConf");

				}
			}
		}
		rd.forward(request, response);

	}

}
