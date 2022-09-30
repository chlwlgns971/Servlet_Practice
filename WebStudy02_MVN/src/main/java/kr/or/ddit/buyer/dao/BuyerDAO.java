package kr.or.ddit.buyer.dao;

import java.util.List;

import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.PagingVO;

public interface BuyerDAO {
	//거래처 정보 추가
	public int insertBuyer(BuyerVO buyer);
	
	//특정 거래처 조회
	public BuyerVO selectBuyer(String buyerId);
	
	//페이징처리를 위한 전체 컬럼갯수 구하기
	public int selectTotalRecord(PagingVO pagingVO);
	
	//거래처 정보 전체 조회
	public List<BuyerVO> selectBuyerList(PagingVO pagingVO);
	
	//거래처 정보 수정
	public int updateBuyer(BuyerVO buyer);
}
