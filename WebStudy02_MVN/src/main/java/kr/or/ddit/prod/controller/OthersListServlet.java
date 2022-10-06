package kr.or.ddit.prod.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import kr.or.ddit.prod.dao.OthersDAO;
import kr.or.ddit.prod.dao.OthersDAOImpl;
import kr.or.ddit.vo.BuyerVO;

@WebServlet({"/prod/getLprodList.do","/prod/getBuyerList.do"})
public class OthersListServlet extends HttpServlet{
	private OthersDAO othersDAO = new OthersDAOImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String servletPath = req.getServletPath();
		if("/prod/getLprodList.do".equals(servletPath)) {
			processLprodList(req,resp);
		}
		else if("/prod/getBuyerList.do".equals(servletPath)) {
			processBuyerList(req, resp);
		}
	}
	public void processLprodList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Map<String, Object>> lprodList = othersDAO.selectLprodList();
		req.setAttribute("model", lprodList);
		String viewName = "/jsonView.do";
		req.getRequestDispatcher(viewName).forward(req, resp);
	}
	public void processBuyerList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String lprodGu = req.getParameter("lprodGu");
		List<BuyerVO> buyerList = othersDAO.selectBuyerList(lprodGu);
		req.setAttribute("model", buyerList);
		String viewName = "/jsonView.do";
		req.getRequestDispatcher(viewName).forward(req, resp);
	}
}
