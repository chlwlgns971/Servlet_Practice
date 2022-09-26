package kr.or.ddit.member.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.vo.MemberVO;
import lombok.extern.java.Log;

public class MemberDAOImplTest {
	private static final Logger log = LoggerFactory.getLogger(MemberDAOImplTest.class);
	MemberDAO dao = new MemberDAOImpl();
	@Test
	public void testInsertMember() {
//		MemberVO member = new MemberVO();
//		member.setMemId("b003");
//		member.setMemPass("java");
//		member.setMemZip("000");
//		member.setMemName("신규");
//		System.out.println(dao.insertMember(member));
	}

	@Test
	public void testSelectMember() {
		MemberVO member = dao.selectMember("b003");
		log.info("memId : {}",member.getMemId());
		log.info("memBir : {}",member.getMemBir());
		log.info("prodList : {}",member.getProdList());
	}

	@Test
	public void testSelectMemberList() {
//		List<MemberVO> memberList = dao.selectMemberList();
//		System.out.println(memberList);
	}

	@Test
	public void testUpdateMember() {
		MemberVO member = dao.selectMember("b002");
		member.setMemZip("5678");
		int rowcnt = dao.updateMember(member);
		//System.out.println(dao.updateMember(member));
		//System.out.println(dao.selectMember("b002"));
		log.info("rowcnt : {}",rowcnt);
	}

	@Test
	public void testDeleteMember() {
		System.out.println(dao.deleteMember("b003"));
	}

}
