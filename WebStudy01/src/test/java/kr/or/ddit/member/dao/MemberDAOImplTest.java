package kr.or.ddit.member.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import kr.or.ddit.vo.MemberVO;

public class MemberDAOImplTest {
	private MemberDAO dao = new MemberDAOImpl();

	@Test
	public void testInsertMember() {
		MemberVO vo = new MemberVO();
		vo.setMemId("z001");
		vo.setMemPass("123456");

		int cnt = dao.insertMember(vo);
		System.out.println(cnt);
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
		// assertNotNull(list);
		// assertNotEquals(0, list.size());
		// assertNotNull(list.get(0).getMemName());
	}

	@Test
	public void testUpdateMember() {
		MemberVO vo = dao.selectMember("a001");
		int cnt = dao.updateMember(vo);
		System.out.println(cnt);
	}

	@Test
	public void testDeleteMember() {
		String id = "null";
		int cnt = dao.deleteMember(id);
		System.out.println(cnt);
	}

}
