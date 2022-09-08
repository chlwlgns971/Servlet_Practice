package kr.or.ddit.servlet03;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.filters.ExpiresFilter.XHttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import kr.or.ddit.enumpkg.OperatorType;
import kr.or.ddit.vo.CalculateVO;

@WebServlet("/calculate2")
public class CalculateServlet2 extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//415를 발생시켜야할지 말지 결정해야함. -> Body에 content가 있는지부터 확인
		int statusCode = HttpServletResponse.SC_OK;
		String bodyContentType = request.getContentType();
		if(!bodyContentType.contains("json")) {
			statusCode = HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE; //415에러코드
		}
		String accept = request.getHeader("Accept");
		if(statusCode==200 && !accept.contains("json")) {
			//statusCode = HttpServletResponse.SC_NOT_ACCEPTABLE; //406에러코드
			try (
				InputStream is = request.getInputStream(); PrintWriter out = response.getWriter();

			){
				//xml데이터에 대한 처리
				ObjectMapper mapper = new XmlMapper();
				CalculateVO vo = mapper.readValue(is, CalculateVO.class);

				mapper.writeValue(out, vo);
			}
		}
		
		if(statusCode == HttpServletResponse.SC_OK) {
			response.setContentType("application/json;charset=UTF-8");
			try (InputStream is = request.getInputStream(); PrintWriter out = response.getWriter();

			) {
				//json데이터에 대한 처리
				ObjectMapper mapper = new ObjectMapper();
				CalculateVO vo = mapper.readValue(is, CalculateVO.class);

				mapper.writeValue(out, vo);
			}
		}
		else {
			response.sendError(statusCode);
		}
		
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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

		if (left == null || left.isEmpty() || !left.matches("\\d+") || right == null || right.isEmpty()
				|| !right.matches("\\d+")) {
			statusCode = HttpServletResponse.SC_BAD_REQUEST;
		}

		if (statusCode == HttpServletResponse.SC_OK) {
			int leftOp = Integer.parseInt(left);
			int rightOp = Integer.parseInt(right);
			String expression = operatorType.getExpression(leftOp, rightOp);

			try {
				PrintWriter out = response.getWriter();
				out.print(expression);
			} catch (Exception e) {

			}
		} else {
			response.sendError(statusCode);
		}
	}

}
