package es.upm.dit.isst.wifiway.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.isst.wifiway.dao.UserDAOImplementation;
import es.upm.dit.isst.wifiway.dao.model.*;
import es.upm.dit.isst.wifiway.dao.*;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet{
	
	private final String USER_ADMIN = "root";
	private final String PASS_ADMIN = "toor";
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		
		User user = UserDAOImplementation.getInstance().loginUser(email, password);
		
		
		
		if (this.USER_ADMIN.equals(email) && this.PASS_ADMIN.equals(password)) {
			//req.getSession().setAttribute("", );
			resp.sendRedirect(req.getContextPath() + "/LoginAdmin.jsp");
		}
		else if (null != user) {
			
			req.getSession().setAttribute("name", user.getName());
			resp.sendRedirect(req.getContextPath() + "/Index.jsp");
		}

		else {
			resp.sendRedirect(req.getContextPath() + "/Index.jsp");
		}
	}
	
}