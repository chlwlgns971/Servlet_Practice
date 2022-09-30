package kr.or.ddit.buyer.service;

import java.util.List;

import kr.or.ddit.member.service.ServiceResult;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.PagingVO;

public interface BuyerService {
	public ServiceResult createBuyer(BuyerVO buyer);
	
	public BuyerVO retrieveBuyer(String buyerId);
	
	public int retrieveBuyerCount(PagingVO pagingVO);
	
	public List<BuyerVO> retrieveBuyerList(PagingVO pagingVO);
	
	public ServiceResult modifyBuyer(BuyerVO buyer);
}
