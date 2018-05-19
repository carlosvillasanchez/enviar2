package es.upm.dit.isst.wifiway.servlets;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.isst.wifiway.dao.UserDAOImplementation;

import es.upm.dit.isst.wifiway.dao.model.User;



@WebServlet("/NewUserServlet")
public class NewUserServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String password2 = req.getParameter("password2");
		
		
		User user = new User();
		user.setPassword(password);
		
		user.setEmail(email);
		
		user.setName(name);	
		
		
		UserDAOImplementation.getInstance().createUser(user);
		
		//req.getSession().setAttribute("name", user.getName());
		
		resp.sendRedirect(req.getContextPath() + "/Index.jsp");
	
	}

}
