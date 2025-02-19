package kr.co.iei.member.model.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.co.iei.member.model.vo.Member;
import kr.co.iei.member.model.vo.MemberRowMapper;

@Repository
public class MemberDao {
	@Autowired
	private JdbcTemplate jdbc;
	@Autowired
	private MemberRowMapper memberRowMapper;

	public Member selectOneMember(Member m) {
		String query ="select * from member where member_id=? and member_pw=?";
		Object[] params = {m.getMemberId(),m.getMemberPw()};
		List list=jdbc.query(query,memberRowMapper, params);
		if(list.isEmpty()) {
			return null;
		}else {
			Member member =(Member)list.get(0);
			return member;
		}
	}

	public int insertMember(Member m) {
		String query= "insert into member values(member_seq.nextval,?,?,?,?,?,normal,0,N)";
		Object[] params= {m.getMemberName(), m.getMemberId(), m.getMemberPw(),m.getMemberEmail(),m.getMemberPhone()};
		int result= jdbc.update(query,params);
		return result;
	}

	public Member selectOneMember(String checkId) {
		String query ="select * from member where member_id=?";
		Object[] params = {checkId};
		List list=jdbc.query(query,memberRowMapper, params);
		if(list.isEmpty()) {
			return null;
		}else {
			Member member =(Member)list.get(0);
			return member;
		}
	
	}

	public int updateMember(Member m) {
		String query ="update member set member_pw=?, member_phone=?, member_email=? where member_no=?";
		Object[] params = {m.getMemberPw(),m.getMemberPhone(),m.getMemberEmail(),m.getMemberNo()};
		int result= jdbc.update(query,params);
		return result;
	}

	public int deleteMember(int memberNo) {
		String query ="delete from member where member_no=?";
		Object[] params = {memberNo};
		int result= jdbc.update(query,params);
		return result;
	}

}
