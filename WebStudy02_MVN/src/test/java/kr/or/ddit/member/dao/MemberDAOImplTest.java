package kr.or.ddit.member.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import kr.or.ddit.vo.MemberVO;

public class MemberDAOImplTest {
	MemberDAO dao = new MemberDAOImpl();
	@Test
	public void testInsertMember() {
		MemberVO member = new MemberVO();
		member.setMemId("b003");
		member.setMemPass("java");
		member.setMemZip("000");
		member.setMemName("신규");
		System.out.println(dao.insertMember(member));
	}

	@Test
	public void testSelectMember() {
		MemberVO member = dao.selectMember("b003");
		System.out.println(member);
	}

	@Test
	public void testSelectMemberList() {
		List<MemberVO> memberList = dao.selectMemberList();
		System.out.println(memberList);
	}

	@Test
	public void testUpdateMember() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteMember() {
		fail("Not yet implemented");
	}

}
