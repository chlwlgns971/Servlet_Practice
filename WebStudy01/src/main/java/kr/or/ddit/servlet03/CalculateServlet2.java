package kr.or.ddit.servlet03;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.filters.ExpiresFilter.XHttpServletResponse;

import kr.or.ddit.enumpkg.OperatorType;


@WebServlet("/calculate2")
public class CalculateServlet2 extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String left = request.getParameter("leftOp");
		String right = request.getParameter("rightOp");
		
		String opParam = request.getParameter("operator");
		int statusCode = XHttpServletResponse.SC_OK;
		OperatorType operatorType = null;
		try {
			operatorType = OperatorType.valueOf(opParam);
		} catch (Exception e) {
			statusCode = HttpServletResponse.SC_BAD_REQUEST;
		}
		
		if(left==null || left.isEmpty() || !left.matches("\\d+")
				|| right==null || right.isEmpty() || !right.matches("\\d+")) {
			statusCode = HttpServletResponse.SC_BAD_REQUEST;
		}
		
		if(statusCode==HttpServletResponse.SC_OK) {
			int leftOp = Integer.parseInt(left);
			int rightOp = Integer.parseInt(right);
			String expression = operatorType.getExpression(leftOp, rightOp);
			
			try {
				PrintWriter out = response.getWriter();
				out.print(expression);
			} catch (Exception e) {
				
			}
		}
		else {
			response.sendError(statusCode);
		}
	}

}
