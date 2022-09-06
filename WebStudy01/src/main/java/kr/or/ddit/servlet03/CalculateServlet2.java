package kr.or.ddit.servlet03;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.filters.ExpiresFilter.XHttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.enumpkg.OperatorType;
import kr.or.ddit.vo.CalculateVO;


@WebServlet("/calculate2")
public class CalculateServlet2 extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json;charset=UTF-8");
		try {
			InputStream is = request.getInputStream();
			PrintWriter out=response.getWriter();
			
			ObjectMapper mapper = new ObjectMapper();
			CalculateVO vo = mapper.readValue(is, CalculateVO.class);
			
			mapper.writeValue(out, vo);
		} catch (IOException e) {
			// TODO: handle exception
		}	
	}
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
