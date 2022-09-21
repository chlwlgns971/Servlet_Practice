package kr.or.ddit.member.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import kr.or.ddit.vo.MemberVO;

public class MemberDAOImplTest {
	private MemberDAO dao = new MemberDAOImpl();

	@Test
	public void testInsertMember() {
		fail("Not yet implemented");
	}

	@Test
	public void testSelectMember() {
		MemberVO vo = dao.selectMember("a001");
		assertNotNull(vo);
		assertNotNull(vo.getMemName());
		System.out.println(vo);
	}

	@Test
	public void testSelectMemberList() {
		List<MemberVO> list = dao.selectMemberList();
		System.out.println(list);
		//assertNotNull(list);
		//assertNotEquals(0, list.size());
		//assertNotNull(list.get(0).getMemName());
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
