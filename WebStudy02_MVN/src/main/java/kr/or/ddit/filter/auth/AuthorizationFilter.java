package kr.or.ddit.filter.auth;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.MemberVOWrapper;

/**
 * 보호차원에 대한 요청에서 인증된 사용자가 해당 자원에 대한 권한을 소유하고 있는지 확인하는 필터
 *
 */
public class AuthorizationFilter implements Filter{
	private Map<String, String[]> securedResources;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		if(securedResources==null) {
 			securedResources = 
 					(Map<String, String[]>) request.getServletContext().getAttribute(AuthencationFilter.ATTRNAME);
		}
		
//		보호자원 여부
//		여
//			인증객체 확보
//			허가 여부(사용자의 role, 자원에 설정된 role 비교)
//			여 : 통과 및 해당 자원 서비스
//			부 : 403 에러 응답
//		부 : 통과 및 해당 자원 서비스
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		
		String uri = req.getServletPath();
		
		String[] roles = securedResources.get(uri);
//		HttpSession session = req.getSession();
//		MemberVO member = (MemberVO)session.getAttribute("authMember");
		
		boolean pass = false;
		
		if(roles == null) {
			pass = true;
		}else {
 			MemberVO authMember = ((MemberVOWrapper)req.getUserPrincipal()).getRealUser();
 			
 			if(Arrays.binarySearch(roles, authMember.getMemRole())<0) {
 				pass = false;
 			}else {
 				pass = true;
 			}
		}
		
		if(pass) {
			chain.doFilter(request, response);
		}else {
			resp.sendError(HttpServletResponse.SC_FORBIDDEN);
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
