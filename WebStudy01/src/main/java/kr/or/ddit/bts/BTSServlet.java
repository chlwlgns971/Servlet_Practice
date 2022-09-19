package kr.or.ddit.bts;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.PageContext;

@WebServlet(value = "/bts/getContent", loadOnStartup = 1)
public class BTSServlet extends HttpServlet {
	private ServletContext application;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		application = getServletContext();
		
		Map<String, String[]> btsDB = new LinkedHashMap<String, String[]>();
		btsDB.put("B001", new String[] {"rm","/WEB-INF/views/bts/rm.jsp"});
		btsDB.put("B002", new String[] {"진","/WEB-INF/views/bts/jin.jsp"});
		btsDB.put("B003", new String[] {"슈가","/WEB-INF/views/bts/sugar.jsp"});
		btsDB.put("B004", new String[] {"제이홉","/WEB-INF/views/bts/jhope.jsp"});
		btsDB.put("B005", new String[] {"지민","/WEB-INF/views/bts/jimin.jsp"});
		btsDB.put("B006", new String[] {"뷔","/WEB-INF/views/bts/v.jsp"});
		btsDB.put("B007", new String[] {"정국","/WEB-INF/views/bts/jungKook.jsp"});
		
		//이 곳에선 request객체와 session객체가 없고 page는 존재하지 않으므로 application scope밖에 사용할 수 없다.
		application.setAttribute("btsDB", btsDB);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Map<String, String[]> btsDB = (Map)application.getAttribute("btsDB");
		req.setCharacterEncoding("UTF-8");
		String member = req.getParameter("member");
		int status = 200;
		if(member==null && member.isEmpty()) {
			status = HttpServletResponse.SC_BAD_REQUEST;
		}
		else if(!btsDB.containsKey(member)) {
			status = HttpServletResponse.SC_NOT_FOUND;
		}
		
		if(status==200) {
			String[] info = btsDB.get(member);
			String contentURL = info[1];
			req.getRequestDispatcher(contentURL).forward(req, resp);
		}else {
			resp.sendError(status);
		}
	}
}
