package kr.or.ddit.login.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login/logout.do")
public class LogoutProcessServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		if(session.isNew()){// 갓 만들어진 세션인지 확인 = 기존에 세션이 없어서 만들어졌다는 의미(정상적인 프로세스가 아니다)
			resp.sendError(400);
			return;
		}
		session.invalidate();
		String viewName = "redirect:/";
		if(viewName.startsWith("redirect:")) {
			viewName = viewName.substring("redirect:".length());
			resp.sendRedirect(req.getContextPath() + viewName);
		}else {
			req.getRequestDispatcher(viewName).forward(req, resp);
		}
	}
}
