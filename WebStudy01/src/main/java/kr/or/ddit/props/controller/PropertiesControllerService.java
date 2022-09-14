package kr.or.ddit.props.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/propertiesControllerService")
public class PropertiesControllerService extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String viewName = "";
		if("".equals(name) || name == null) viewName = "/getAllPropertise.do";
		else viewName = "/getProperty.do";
		request.getRequestDispatcher(viewName).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String viewName = "/addProperty.do";
		request.getRequestDispatcher(viewName).forward(request, response);
	}

}
