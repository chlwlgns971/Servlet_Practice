package kr.or.ddit.prod.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.vo.ProdVO;

/**
 * /prod/prodList.do(GET)  
 * /prod/prodView.do?what=P101000001(GET)
 * /prod/prodInsert.do(GET)
 * /prod/prodInsert.do(POST)
 * /prod/prodUpdate.do?what=P101000001(GET)
 * /prod/prodUpdate.do(POST)
 */
@WebServlet("/prod/prodList.do")
public class ProdListServlet extends HttpServlet{
	ProdService service = new ProdServiceImpl();
	
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
		List<ProdVO> prodList = service.retrieveProdList();
		req.setAttribute("prodList", prodList);
		String logicalViewName = "prod/prodList";
		viewResolve(logicalViewName, req, resp);
	}
}
