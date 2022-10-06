package kr.or.ddit.filter;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 특정 사용자를 대상으로 blind처리. -> 규칙 위배시 접근 불가능하게 
 *
 */
public class BlindFilter implements Filter{
	/**
	 * key : 사용자 IP, value : 사유
	 */
	private Map<String, String> blindUsers;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		blindUsers = new LinkedHashMap<String, String>();
		blindUsers.put("127.0.0.1", "본인차단");
		blindUsers.put("0:0:0:0:0:0:0:1", "그냥 차단");
		blindUsers.put("192.168.143.20", "역시 차단");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		
		String ipAddr = req.getRemoteAddr(); //요청자 ip주소 가져오기
		String uri = req.getServletPath(); //요청목적지 주소 가져오기
		boolean pass = true;
		
		if(!uri.equals("/errors/errorMessage.jsp")) {
			if(blindUsers.containsKey(ipAddr)) {
				pass = false;
			}
			else pass = true;
		}
		if(pass) {
			chain.doFilter(request, response);
		}
		else {
			//HttpSession session = req.getSession();
			String message = blindUsers.get(ipAddr);
			req.getSession().setAttribute("reason", message);
			resp.sendRedirect(req.getContextPath()+"/errors/errorMessage.jsp");
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
