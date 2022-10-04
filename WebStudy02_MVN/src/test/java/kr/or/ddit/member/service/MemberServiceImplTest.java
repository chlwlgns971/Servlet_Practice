package kr.or.ddit.member.service;

import static org.junit.Assert.*;

import org.junit.Test;

import kr.or.ddit.member.dao.MemberDAO;
import kr.or.ddit.member.dao.MemberDAOImpl;

public class MemberServiceImplTest {
	MemberService service = new MemberServiceImpl();

	@Test
	public void testCreateMember() {
		fail("Not yet implemented");
	}

	@Test
	public void testRetrieveMember() {
		System.out.println(service.retrieveMember("sdosaslifhseilfhisefhl"));
	}

	@Test
	public void testRetrieveMemberCount() {
		fail("Not yet implemented");
	}

	@Test
	public void testRetrieveMemberList() {
		fail("Not yet implemented");
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
