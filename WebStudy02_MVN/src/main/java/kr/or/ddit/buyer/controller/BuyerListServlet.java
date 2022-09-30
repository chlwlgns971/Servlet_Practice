package kr.or.ddit.buyer.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.buyer.service.BuyerService;
import kr.or.ddit.buyer.service.BuyerServiceImpl;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProdVO;

/**
 * /buyer/buyerList.do(GET)
 * /buyer.buyerView.do?what=P10101(GET)
 * /buyer/buyerInsert.do(GET)
 * /buyer/buyerInsert.do(POST)
 * /buyer/buyerUpdate.do?what=P10101(GET)
 * /buyer/buyerUpdate.do?what=P10101(POST)
 *	전자제품 카테고리의 삼성전자를 검색.
 */
@WebServlet("/buyer/buyerList")
public class BuyerListServlet extends HttpServlet{
	private BuyerService service = new BuyerServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ProdVO detailCondition = new ProdVO();
		//detailCondition.setProdLgu(prodLgu);
		try {
			BeanUtils.populate(detailCondition, req.getParameterMap());
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new RuntimeException(e);
		}
		String pageParam = req.getParameter("page");
		int currentPage = 1;
		if(StringUtils.isNumeric(pageParam)) {
			//여기들ㄹ어오면 안전하게 파싱 가능!
			currentPage = Integer.parseInt(pageParam);
		}
		PagingVO<BuyerVO> pagingVO = new PagingVO<>(5,2);
		pagingVO.setCurrentPage(currentPage);
		//pagingVO.setSimpleCondition(simpleCondition);
		//pagingVO.setDetailCondition(detailCondition);
		
		int totalRecord = service.retrieveBuyerCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		List<BuyerVO> buyerList = service.retrieveBuyerList(pagingVO);
		pagingVO.setDataList(buyerList);
		req.setAttribute("pagingVO", pagingVO);
		String viewName = "/buyer/buyerList.tiles";
		req.getRequestDispatcher(viewName).forward(req, resp);
	}
}
