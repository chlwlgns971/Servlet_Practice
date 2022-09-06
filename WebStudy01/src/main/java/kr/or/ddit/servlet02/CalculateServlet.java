package kr.or.ddit.servlet02;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.vo.CalculateVO;

@WebServlet("/calculate")
public class CalculateServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String leftOp = req.getParameter("leftOp");
		String operator = req.getParameter("operator");
		String rightOp = req.getParameter("rightOp");
		if((leftOp != null && leftOp.matches("[0-9]+")) 
				&& operator != null 
				&& (rightOp != null && rightOp.matches("[0-9]+"))) 
		{
			double leftSu = Double.parseDouble(leftOp);
			double rightSu = Double.parseDouble(rightOp);
			double result = calculate(leftSu, rightSu, operator);
			req.setAttribute("leftSu", leftSu);
		}else {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
	}
	
	private double calculate(double leftSu, double rightSu, String operator) {
		double result = 0;
			switch(operator) {
				case "PLUS":
					result = leftSu + rightSu;
					break;
				case "MINUS":
					result = leftSu - rightSu;
					break;
				case "MULTIPLY":
					result = Math.round((leftSu * rightSu)*100/100.0);
					break;
				case "DIVIDE":
					result = Math.round((leftSu / rightSu)*100/100.0);
					break;
			}
		return result;
	}
	
}
