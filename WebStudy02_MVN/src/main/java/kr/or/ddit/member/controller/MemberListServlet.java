package kr.or.ddit.member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.vo.MemberVO;

/**
 * /member (GET) /member/a001 (GET) /member/a001 (PUT) /member/a001 (DELETE)
 * ->이런 형태의 URL를 RESTful URI라고 한다.
 * 
 * non-RESTful URI /member/memberList.do (GET) 
 * /member/memberView.do?who=a001 (GET) 
 * /member/memberInsert.do (GET) 
 * /member/memberInsert.do (POST)
 * /member/memberUpdate.do?who=a001 (GET) 
 * /member/memberUpdate.do?who=a001 (POST) 
 * /member/memberDelete.do (POST)
 */
@WebServlet("/member/memberList.do")
public class MemberListServlet extends HttpServlet {
	private MemberService service = new MemberServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<MemberVO> list = service.retrieveMemberList();
		req.setAttribute("memberList", list);
		String commandPage = "/WEB-INF/views/member/memberList.jsp";
		req.setAttribute("commandPage", commandPage);
		String viewName = "/WEB-INF/views/template.jsp";
		req.getRequestDispatcher(viewName).forward(req, resp);
	}
}
