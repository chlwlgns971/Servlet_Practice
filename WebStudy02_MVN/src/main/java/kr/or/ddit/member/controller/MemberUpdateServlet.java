package kr.or.ddit.member.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.member.service.ServiceResult;
import kr.or.ddit.vo.MemberVO;

@WebServlet("/member/memberUpdate.do")
public class MemberUpdateServlet extends HttpServlet{
	
	private MemberService service = new MemberServiceImpl();
	
	private void viewResolve(
			String logicalViewName, 
			HttpServletRequest req, 
			HttpServletResponse resp
	) throws ServletException, IOException{
		if(logicalViewName.startsWith("redirect:")) {
			logicalViewName = logicalViewName.substring("redirect:".length());
			resp.sendRedirect(req.getContextPath() + logicalViewName);
		}else {
			String viewName = "/"+logicalViewName+".tiles";
			req.getRequestDispatcher(viewName).forward(req, resp);
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		
		String memId = req.getParameter("who");
		
		if(StringUtils.isBlank(memId)) {
			resp.sendError(400);
			return;
		}
		
		MemberVO member = service.retrieveMember(memId);
		
		req.setAttribute("member", member);
		req.setAttribute("command", "UPDATE");
		
		String logicalViewName = "member/memberForm";
		viewResolve(logicalViewName, req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		MemberVO member = new MemberVO();
		req.setAttribute("member", member);
//		member.setMemId(req.getParameter("memId"));
		try {
			BeanUtils.populate(member, req.getParameterMap()); //위에 주석 처리 한 부분을 vo변수명과 받는 파라미터값 이름이 동일하면 자동으로 매칭시켜 넣어줌.
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new RuntimeException(e);
		}
		
		Map<String, String> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		
		boolean valid = validate(member, errors);
		
		String logicalViewName = null;
		if(valid) {
			ServiceResult result = service.modifyMember(member);
			switch (result) {
			case OK:
				logicalViewName = "redirect:/member/memberList.do";
				break;

			default:
				req.setAttribute("message", "서버 오류, 쫌따 다시 하셈.");
				logicalViewName = "member/memberForm";
				break;
			}
		}else {
			logicalViewName = "member/memberForm";
		}
		viewResolve(logicalViewName, req, resp);
	}

	// Hibernate validator 
	private boolean validate(MemberVO member, Map<String, String> errors) {
		boolean valid = true;
		if(StringUtils.isBlank(member.getMemId()) ) {
			errors.put("memId", "아이디 누락");
			valid = false;
		}
		if(StringUtils.isBlank(member.getMemPass())) {
			errors.put("memPass", "비밀번호 누락");
			valid = false;
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if(StringUtils.isNotBlank(member.getMemBir())) {
			try {
				sdf.parse(member.getMemBir());
			} catch (ParseException e) {
				errors.put("memBir", "날짜 형식 확인");
				valid = false;
			}			
		}
		return valid;
		
	}
}
