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

@WebServlet("/member/memberUpdate2.do")
public class MemberUpdateServlet_test extends HttpServlet{
	MemberService service = new MemberServiceImpl();
	
	private void viewResolve(
			String commandPage,
			HttpServletRequest req,
			HttpServletResponse resp
		) throws ServletException, IOException{
			if(commandPage.startsWith("redirect:")) {
				commandPage = commandPage.substring("redirect:".length());
				resp.sendRedirect(req.getContextPath() + commandPage);
			}else {
				req.setAttribute("commandPage", commandPage);
				String viewName = "/WEB-INF/views/template.jsp";
				req.getRequestDispatcher(viewName).forward(req, resp);
			}
		}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//이이디로 요청이 들어오면 해당 회원 정보를 검색하여 객체에 담고 forward방식으로 form페이지로 보내줘야 함. 그리고 form에서 post로 요청이 들어오면 update실행
		String memId = req.getParameter("who");
		MemberVO member = service.retrieveMember(memId);
		req.setAttribute("member", member);
		String commandPage = "/WEB-INF/views/member/memberForm.jsp";
		viewResolve(commandPage, req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		MemberVO member = new MemberVO();
		req.setAttribute("member", member);
		try {
			BeanUtils.populate(member, req.getParameterMap());
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new RuntimeException(e);
		}
		
		Map<String, String> errors = new HashMap<String, String>();
		req.setAttribute("errors", errors);
		boolean valid = validate(member, errors);
		
		String commandPage = null;
		if(valid) {
			ServiceResult result = service.modifyMember(member);
			switch (result) {
			case OK:
				commandPage = "redirect:/member/memberList.do";
				break;

			default:
				req.setAttribute("message", "서버오류");
				commandPage = "/WEB-INF/views/member/memberForm.jsp";
				break;
			}
		}else {
			commandPage = "/WEB-INF/views/member/memberForm.jsp";
		}
		viewResolve(commandPage, req, resp);
	}
	
	//Hibernate Validator
		private boolean validate(MemberVO vo, Map<String, String> errors) {
			boolean valid = true;
			if(StringUtils.isBlank(vo.getMemId())) {
				errors.put("memId", "아이디 누락");
				valid = false;
			}
			if(StringUtils.isBlank(vo.getMemPass())) {
				errors.put("memPass", "비밀번호 누락");
				valid = false;
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			if(StringUtils.isNotBlank(vo.getMemBir())) {
				try {
					sdf.parse(vo.getMemBir());
				} catch (ParseException e) {
					errors.put("memBir", "날짜 형식 확인");
					valid = false;
				}
			}
			return valid;
		}

}
