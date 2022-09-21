package kr.or.ddit.member.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import kr.or.ddit.vo.MemberVO;

public class MemberServiceImplTest {
	private MemberService service = new MemberServiceImpl();
	
	@Test
	public void testCreateMember() {
		fail("Not yet implemented");
	}

	@Test
	public void testRetrieveMember() {
		fail("Not yet implemented");
	}

	@Test
	public void testRetrieveMemberList() {
		List<MemberVO> list = service.retrieveMemberList();
		System.out.println(list);
	}

	@Test
	public void testModifyMember() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveMember() {
		fail("Not yet implemented");
	}

}
