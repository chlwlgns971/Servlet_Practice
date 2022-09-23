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

@WebServlet("/member/memberInsert.do")
public class MemberInsertServlet extends HttpServlet {
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
		req.setAttribute("command", "INSERT");
		String commandPage = "/WEB-INF/views/member/memberForm.jsp";
		req.setAttribute("commandPage", commandPage);
		String viewName = "/WEB-INF/views/template.jsp";
		req.getRequestDispatcher(viewName).forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		MemberVO vo = new MemberVO();
		req.setAttribute("member", vo);
		
//		vo.setMemId(req.getParameter("memId"));
//		vo.setMemPass(req.getParameter("memPass"));
//		vo.setMemName(req.getParameter("memName"));
//		vo.setMemRegno1(req.getParameter("memRegno1"));
//		vo.setMemRegno2(req.getParameter("memRegno2"));
//		vo.setMemBir(req.getParameter("memBir"));
//		vo.setMemZip(req.getParameter("memZip"));
//		vo.setMemAdd1(req.getParameter("memAdd1"));
//		vo.setMemAdd2(req.getParameter("memAdd2"));
//		vo.setMemHometel(req.getParameter("memHometel"));
//		vo.setMemComtel(req.getParameter("memComtel"));
//		vo.setMemHp(req.getParameter("memHp"));
//		vo.setMemMail(req.getParameter("memMail"));
//		vo.setMemJob(req.getParameter("memJob"));
//		vo.setMemLike(req.getParameter("memLike"));
//		vo.setMemMemorial(req.getParameter("memMemorial"));
//		vo.setMemMemorialday(req.getParameter("memMemorialday"));
//		vo.setMemMileage(0);
		
		try {
			BeanUtils.populate(vo, req.getParameterMap()); //위에 주석 처리 한 부분을 vo변수명과 받는 파라미터값 이름이 동일하면 자동으로 매칭시켜 넣어줌.
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new RuntimeException();
		}
	
		Map<String, String> errors = new HashMap<String, String>();
		req.setAttribute("errors", errors);
		boolean valid = validate(vo, errors);
		
		String commandPage = null;
		if(valid) {
			ServiceResult result = service.createMember(vo);
			switch (result) {
			case PKDUPLICATED:
				req.setAttribute("message", "아이디중복");
				commandPage = "/WEB-INF/views/member/memberForm.jsp";	
				break;	
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
