package kr.or.ddit.login.service;

import kr.or.ddit.commons.exception.UserNotFoundException;
import kr.or.ddit.login.BadCredentialException;
import kr.or.ddit.member.dao.MemberDAO;
import kr.or.ddit.member.dao.MemberDAOImpl;
import kr.or.ddit.vo.MemberVO;

public class AuthentiationServiceImpl implements AutheticationService {
	
	private MemberDAO dao = new MemberDAOImpl();

	@Override
	public MemberVO authenticate(MemberVO inputData) {
		MemberVO saved = dao.selectMember(inputData.getMemId());
		if(saved == null) throw new UserNotFoundException(inputData.getMemId());
		String inputPass = inputData.getMemPass();
		String savedPass = saved.getMemPass();
		if(savedPass.equals(inputPass)) {
			return saved;
		}else {
			throw new BadCredentialException("비밀번호 오류");
		}
	}
}
