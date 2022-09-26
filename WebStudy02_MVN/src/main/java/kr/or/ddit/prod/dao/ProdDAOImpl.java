package kr.or.ddit.prod.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.mybatis.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.vo.ProdVO;

public class ProdDAOImpl implements ProdDAO {
	
	private SqlSessionFactory sqlSessionFactory =
			CustomSqlSessionFactoryBuilder.getSqlSessionFactory();

	@Override
	public int insertProd(ProdVO prod) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ProdVO selectProd(String prodId) {
		// 상품 전체 정보 + 분류명, 거래처명, 상품 구매자 목록(아이디, 구매자명, 지역, 이메일)
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession();
		){
			ProdDAO mapper = sqlSession.getMapper(ProdDAO.class);
			return mapper.selectProd(prodId);
		}
	}

	@Override
	public List<ProdVO> selectProdList() {
		// 상품아이디, 상품명, 판매가, 구매가, 마일리지. + 분류명, 거래처명, 해당 상품의 구매자수
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession();
		){
			ProdDAO mapper = sqlSession.getMapper(ProdDAO.class);
			return mapper.selectProdList();
		}
	}

	@Override
	public int updateProd(ProdVO prod) {
		// TODO Auto-generated method stub
		return 0;
	}

}
