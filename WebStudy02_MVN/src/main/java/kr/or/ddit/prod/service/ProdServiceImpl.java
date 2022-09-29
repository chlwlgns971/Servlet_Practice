package kr.or.ddit.prod.service;

import java.util.List;

import kr.or.ddit.member.service.ServiceResult;
import kr.or.ddit.prod.dao.ProdDAO;
import kr.or.ddit.prod.dao.ProdDAOImpl;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProdVO;

public class ProdServiceImpl implements ProdService {
	ProdDAO dao = new ProdDAOImpl();

	@Override
	public ServiceResult createProd(ProdVO prod) {
		ServiceResult result = null;
		int rowcnt = dao.insertProd(prod);
		if(rowcnt>0) result=ServiceResult.OK;
		else result=ServiceResult.FAIL;
		
		return result;
	}

	@Override
	public ProdVO retrieveProd(String prodId) {
		return dao.selectProd(prodId);
	}

	@Override
	public List<ProdVO> retrieveProdList(PagingVO pagingVO) {
		return dao.selectProdList(pagingVO);
	}

	@Override
	public ServiceResult modifyProd(ProdVO prod) {
		ServiceResult result = null;
		int rowcnt = dao.updateProd(prod);
		if(rowcnt>0) result=ServiceResult.OK;
		else result=ServiceResult.FAIL;
		
		return result;
	}

	@Override
	public int retrieveProdCount(PagingVO pagingVO) {
		return dao.selectTotalRecord(pagingVO);
	}

}
