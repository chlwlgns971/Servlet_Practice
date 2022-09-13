package kr.or.ddit.servlet06;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DataTruncation;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

/**
 * Servlet implementation class MessageBundleReadServlet
 */
@WebServlet("/getMessage")
public class MessageBundleReadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//      2. response 생성 (back-end)
//     		1) 메세지번들 로딩(ResourceBundle)
//      	2) "hello" 코드 메시지 확보
//      	3) 확보한 메세지를 컨텐츠화(Accept 헤더에 따라 Content-Type 결정).

		
		//요청을 분석해서 필요한 데이터 만든다??
	    Locale locale = request.getLocale();
	    request.setCharacterEncoding("UTF-8");
	    String languageTag = request.getParameter("language");
	    if(languageTag!=null && !languageTag.isEmpty()) {
	    	locale = Locale.forLanguageTag(languageTag);
	    }

		String baseName = "egovframework.message.com.message-common"; //properties라고 가정하고 돌리기 때문에 쓰지 않는다. + class처럼 접근
		ResourceBundle bundle = ResourceBundle.getBundle(baseName,locale);
		//데이터
	    String value = bundle.getString("hellos");
	    String accept = request.getHeader("Accept");
	    request.setAttribute("hello", value);

		
//		String value = bundle.getString(key);
//		Map<Object, Object> map = new HashMap<Object, Object>();
//		map.put(key, value);
//		Enumeration <String> keys = bundle.getKeys();
//		while (keys.hasMoreElements()) {
//			String key = keys.nextElement();
//			String value = bundle.getString(key);
//			map.put(key, value);
//		}
		//String result = "";
		String viewName = null;
		if(accept.contains("json")) {
//			try {
//				ObjectMapper mapper = new ObjectMapper();
//				result = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(map);
//				System.out.println(result);
//				response.getWriter().write(result);
//			} catch (IOException e) {
//				throw new RuntimeException(e);
//			}
			viewName = "/jsonView.do";
		}
		else if(accept.contains("xml")) {
//			try {
//				ObjectMapper mapper = new XmlMapper();
//				result = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(map);
//				System.out.println(result);
//				response.getWriter().write(result);
//			} catch (IOException e) {
//				throw new RuntimeException(e);
//			}
			viewName = "/xmlView.do";
		}
		else {
			viewName = "/WEB-INF/views/messageView.jsp";
		}
		request.getRequestDispatcher(viewName).forward(request, response);
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
