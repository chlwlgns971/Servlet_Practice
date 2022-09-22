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
		int cnt = 0;
		String sql = "INSERT INTO member (\r\n"
				+ "    mem_id,\r\n"
				+ "    mem_pass,\r\n"
				+ "    mem_name,\r\n"
				+ "    mem_regno1,\r\n"
				+ "    mem_regno2,\r\n"
				+ "    mem_bir,\r\n"
				+ "    mem_zip,\r\n"
				+ "    mem_add1,\r\n"
				+ "    mem_add2,\r\n"
				+ "    mem_hometel,\r\n"
				+ "    mem_comtel,\r\n"
				+ "    mem_hp,\r\n"
				+ "    mem_mail,\r\n"
				+ "    mem_job,\r\n"
				+ "    mem_like,\r\n"
				+ "    mem_memorial,\r\n"
				+ "    mem_memorialday,\r\n"
				+ "    mem_mileage \r\n"
				+ ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try (
			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
		) {
			stmt.setString(1, member.getMemId());
			stmt.setString(2, member.getMemPass());
			stmt.setString(3, member.getMemName());
			stmt.setString(4, member.getMemRegno1());
			stmt.setString(5, member.getMemRegno2());
			stmt.setString(6, member.getMemBir());
			stmt.setString(7, member.getMemZip());
			stmt.setString(8, member.getMemAdd1());
			stmt.setString(9, member.getMemAdd2());
			stmt.setString(10, member.getMemHometel());
			stmt.setString(11, member.getMemComtel());
			stmt.setString(12, member.getMemHp());
			stmt.setString(13, member.getMemMail());
			stmt.setString(14, member.getMemJob());
			stmt.setString(15, member.getMemLike());
			stmt.setString(16, member.getMemMemorial());
			stmt.setString(17, member.getMemMemorialday());
			stmt.setInt(18, 0);
			
			cnt = stmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return cnt;
	}

	@Override
	public MemberVO selectMember(String memId) {
		String sql = "SELECT* FROM MEMBER WHERE MEM_ID = ?";
		MemberVO vo =null;
		try (
			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
		) {
			stmt.setString(1, memId);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				vo = new MemberVO();
				vo.setMemId(rs.getString("MEM_ID"));
				vo.setMemPass(rs.getString("MEM_PASS"));
				vo.setMemName(rs.getString("MEM_NAME"));
				vo.setMemRegno1(rs.getString("MEM_REGNO1"));
				vo.setMemRegno2(rs.getString("MEM_REGNO2"));
				vo.setMemBir(rs.getString("MEM_BIR"));
				vo.setMemZip(rs.getString("MEM_ZIP"));
				vo.setMemMail(rs.getString("MEM_MAIL"));
				vo.setMemHometel(rs.getString("MEM_HOMETEL"));
				vo.setMemComtel(rs.getString("MEM_COMTEL"));
				vo.setMemHp(rs.getString("MEM_HP"));
				vo.setMemJob(rs.getString("MEM_JOB"));
				vo.setMemLike(rs.getString("MEM_LIKE"));
				vo.setMemAdd1(rs.getString("MEM_ADD1"));
				vo.setMemAdd2(rs.getString("MEM_ADD2"));
				vo.setMemMemorial(rs.getString("MEM_MEMORIAL"));
				vo.setMemMemorialday(rs.getString("MEM_MEMORIALDAY"));
				vo.setMemMileage(rs.getInt("MEM_MILEAGE"));
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return vo;
	}

	@Override
	public List<MemberVO> selectMemberList() {
		String sql = "SELECT MEM_ID, MEM_NAME, MEM_MAIL, MEM_HP, MEM_ADD1, MEM_MILEAGE FROM MEMBER WHERE MEM_DELETE IS NULL";
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
		int cnt = 0;
		String sql = "UPDATE MEMBER SET(\r\n"
				+ "    mem_pass=?,\r\n"
				+ "    mem_name=?,\r\n"
				+ "    mem_regno1=?,\r\n"
				+ "    mem_regno2=?,\r\n"
				+ "    mem_bir=?,\r\n"
				+ "    mem_zip=?,\r\n"
				+ "    mem_add1=?,\r\n"
				+ "    mem_add2=?,\r\n"
				+ "    mem_hometel=?,\r\n"
				+ "    mem_comtel=?,\r\n"
				+ "    mem_hp=?,\r\n"
				+ "    mem_mail=?,\r\n"
				+ "    mem_job=?,\r\n"
				+ "    mem_like=?,\r\n"
				+ "    mem_memorial=?,\r\n"
				+ "    mem_memorialday=?,\r\n"
				+ "    mem_mileage=? \r\n"
				+ ") WHERE MEM_ID=?";
		
		try (
			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
		) {
			stmt.setString(18, member.getMemId());
			stmt.setString(1, member.getMemPass());
			stmt.setString(2, member.getMemName());
			stmt.setString(3, member.getMemRegno1());
			stmt.setString(4, member.getMemRegno2());
			stmt.setString(5, member.getMemBir());
			stmt.setString(6, member.getMemZip());
			stmt.setString(7, member.getMemAdd1());
			stmt.setString(8, member.getMemAdd2());
			stmt.setString(9, member.getMemHometel());
			stmt.setString(10, member.getMemComtel());
			stmt.setString(11, member.getMemHp());
			stmt.setString(12, member.getMemMail());
			stmt.setString(13, member.getMemJob());
			stmt.setString(14, member.getMemLike());
			stmt.setString(15, member.getMemMemorial());
			stmt.setString(16, member.getMemMemorialday());
			stmt.setInt(17, member.getMemMileage());
			
			cnt = stmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return cnt;
	}

	@Override
	public int deleteMember(String memId) {
		int cnt = 0;
		String sql = "UPDATE MEMBER SET MEM_DELETE='1' WHERE MEM_ID = ?";
		try (
			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
		) {
			stmt.setString(1, memId);
			cnt = stmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return cnt;
	}

}
