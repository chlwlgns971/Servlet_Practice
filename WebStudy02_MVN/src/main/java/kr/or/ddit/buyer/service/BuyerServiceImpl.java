package kr.or.ddit.buyer.service;

import java.util.List;

import kr.or.ddit.buyer.dao.BuyerDAO;
import kr.or.ddit.buyer.dao.BuyerDAOImpl;
import kr.or.ddit.member.service.ServiceResult;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.PagingVO;

public class BuyerServiceImpl implements BuyerService {
	private BuyerDAO dao = new BuyerDAOImpl();

	@Override
	public ServiceResult createBuyer(BuyerVO buyer) {
		ServiceResult result = null;
		int rowcnt = dao.insertBuyer(buyer);
		if(rowcnt > 0) result = ServiceResult.OK;
		else result = ServiceResult.FAIL;
		
		return result;
	}

	@Override
	public BuyerVO retrieveBuyer(String buyerId) {
		BuyerVO buyer = dao.selectBuyer(buyerId);
		return buyer;
	}

	@Override
	public List<BuyerVO> retrieveBuyerList(PagingVO pagingVO) {
		return dao.selectBuyerList(pagingVO);
	}

	@Override
	public ServiceResult modifyBuyer(BuyerVO buyer) {
		ServiceResult result = null;
		int rowcnt = dao.updateBuyer(buyer);
		if(rowcnt > 0) result = ServiceResult.OK;
		else result = ServiceResult.FAIL;
		
		return result;
	}

	@Override
	public int retrieveBuyerCount(PagingVO pagingVO) {
		return dao.selectTotalRecord(pagingVO);
	}
	
}
