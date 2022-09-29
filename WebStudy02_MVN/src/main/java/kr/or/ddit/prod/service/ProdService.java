package kr.or.ddit.prod.service;

import java.util.List;

import kr.or.ddit.member.service.ServiceResult;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProdVO;

/**
 * 상품관리 Business Logic Layer
 *
 */
public interface ProdService {
	public ServiceResult createProd(ProdVO prod);
	public ProdVO retrieveProd(String prodId);
	public int retrieveProdCount(PagingVO pagingVO);
	public List<ProdVO> retrieveProdList(PagingVO pagingVO);
	public ServiceResult modifyProd(ProdVO prod);
}
