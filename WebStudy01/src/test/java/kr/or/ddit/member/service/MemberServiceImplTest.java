package kr.or.ddit.member.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import kr.or.ddit.vo.MemberVO;

public class MemberServiceImplTest {
	private MemberService service = new MemberServiceImpl();
	
	@Test
	public void testCreateMember() {
		MemberVO vo =new MemberVO();
		vo.setMemId("askfhaklfhaslkf");
		vo.setMemPass("1234");
		ServiceResult result = service.createMember(vo);
		//System.out.println(result);
	}

	@Test
	public void testRetrieveMember() {
		MemberVO vo = service.retrieveMember("asflasfj;aasl;");
		//System.out.println(vo);
	}

	@Test
	public void testRetrieveMemberList() {
		List<MemberVO> list = service.retrieveMemberList();
		//System.out.println(list);
	}

	@Test
	public void testModifyMember() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveMember() {
		MemberVO vo = new MemberVO();
		vo.setMemId("   ㅁㄴㅇㅁ");
		ServiceResult result = service.removeMember(vo);
		System.out.println(result);
	}

}
