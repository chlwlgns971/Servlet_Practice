package kr.or.ddit.servlet02;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/gugudan.do")
public class GugudanServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String minDan = req.getParameter("minDan");
		String maxDan = req.getParameter("maxDan");

		int min;
		int max;

		if ((minDan != null && !minDan.isEmpty()) && (maxDan != null && !maxDan.isEmpty())) {
			if (!minDan.matches("[1-9]") || !maxDan.matches("[1-9]")) {
				resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "1단부터 9단까지 범위 내의 파라미터만 가능."); // 오류 코드랑 메시지 보내기 (ex: 404)
				return;
			} else {
				min = Integer.parseInt(minDan);
				max = Integer.parseInt(maxDan);
			}
		} else {
			min = 1;
			max = 9;
		}

		String Pattern = "<option value = %d>%d</option>";

		StringBuffer Options = new StringBuffer();
		for (int i = 1; i <= 9; i++) {
			Options.append(String.format(Pattern, i, i));
		}

		StringBuffer gugudanText = new StringBuffer();
		for (int i = min; i <= max; i++) {
			gugudanText.append(String.format("<tr><th> %d%s </th>", i, "단"));
			for (int j = 1; j <= 9; j++) {
				gugudanText.append(String.format("<td> %d * %d = %d </td>", i, j, i * j));
			}
			gugudanText.append("<tr>");
		}

		// X-Requested-With : XMLHttpRequest, AJAX (Async Javascript And XMLHttpRequest)
		String header = req.getHeader("X-Requested-With");
		if ("XMLHttpRequest".equals(header)) {
			resp.setCharacterEncoding("utf-8");
			try (PrintWriter out = resp.getWriter();) {
				out.println(gugudanText); // out.write()는 Buffer로 쌓아서 보내는 것이다.
				resp.flushBuffer();
				//out.close(); try 다음에 () 안에 객체를 선언했을 때 close()를 할 필요가 없다.
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			req.setAttribute("options", Options);
			req.setAttribute("gugudanText", gugudanText);
			req.setAttribute("minDan", min);
			req.setAttribute("maxDan", max);
			String viewLayer = "/WEB-INF/views/tmpl/gugudan.tmpl";
			req.getRequestDispatcher(viewLayer).forward(req, resp); // TMPLServlet
		}

	}

}
