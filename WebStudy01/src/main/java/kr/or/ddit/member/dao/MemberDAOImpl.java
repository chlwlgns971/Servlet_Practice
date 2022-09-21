package kr.or.ddit.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.or.ddit.db.ConnectionFactory;
import kr.or.ddit.vo.MemberVO;

public class MemberDAOImpl implements MemberDAO {

	@Override
	public int insertMember(MemberVO member) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public MemberVO selectMember(String memId) {
		String sql = "SELECT MEM_ID, MEM_NAME, MEM_MAIL, MEM_HP, MEM_ADD1, MEM_MILEAGE FROM MEMBER WHERE MEM_ID = ?";
		MemberVO vo = new MemberVO();
		try (
			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
		) {
			stmt.setString(1, memId);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				vo.setMemId(rs.getString("MEM_ID"));
				vo.setMemName(rs.getString("MEM_NAME"));
				vo.setMemMail(rs.getString("MEM_MAIL"));
				vo.setMemHp(rs.getString("MEM_HP"));
				vo.setMemAdd1(rs.getString("MEM_ADD1"));
				vo.setMemMileage(rs.getInt("MEM_MILEAGE"));
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return vo;
	}

	@Override
	public List<MemberVO> selectMemberList() {
		String sql = "SELECT MEM_ID, MEM_NAME, MEM_MAIL, MEM_HP, MEM_ADD1, MEM_MILEAGE FROM MEMBER";
		List<MemberVO> list = new ArrayList<>();
		String[] colName = null;
		try(
			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
		){
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				/*
				<td>회원아이디</td>
				<td>회원명</td>
				<td>이메일</td>
				<td>휴대폰</td>
				<td>지역</td>
				<td>마일리지</td>
				*/
				MemberVO vo = new MemberVO();
				vo.setMemId(rs.getString("MEM_ID"));
				vo.setMemName(rs.getString("MEM_NAME"));
				vo.setMemMail(rs.getString("MEM_MAIL"));
				vo.setMemHp(rs.getString("MEM_HP"));
				vo.setMemAdd1(rs.getString("MEM_ADD1"));
				vo.setMemMileage(rs.getInt("MEM_MILEAGE"));
				
				list.add(vo);
			}
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return list;
	}

	@Override
	public int updateMember(MemberVO member) {
		return 0;
	}

	@Override
	public int deleteMember(String memId) {
		// TODO Auto-generated method stub
		return 0;
	}

}
