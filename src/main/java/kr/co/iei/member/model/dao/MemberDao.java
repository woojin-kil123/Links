package kr.co.iei.member.model.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.co.iei.contents.model.vo.ContentStarRowMapper;
import kr.co.iei.member.model.vo.BuMember;
import kr.co.iei.member.model.vo.Member;
import kr.co.iei.member.model.vo.MemberRowMapper;

@Repository
public class MemberDao {
	@Autowired
	private JdbcTemplate jdbc;
	@Autowired
	private MemberRowMapper memberRowMapper;
	@Autowired
	private ContentStarRowMapper contentStarRowMapper;

	public Member selectOneMember(Member m) {
		String query = "select * from member where member_id=? and member_pw=?";
		Object[] params = { m.getMemberId(), m.getMemberPw() };
		List list = jdbc.query(query, memberRowMapper, params);
		if (list.isEmpty()) {
			return null;
		} else {
			Member member = (Member) list.get(0);
			return member;
		}
	}

	public int insertMember(Member m) {
		String query = "insert into member values(member_seq.nextval,?,?,?,?,?,'normal',1,'N')";
		Object[] params = { m.getMemberName(), m.getMemberId(), m.getMemberPw(), m.getMemberEmail(),
				m.getMemberPhone() };
		int result = jdbc.update(query, params);
		return result;
	}

	public Member selectOneMember(String checkId) {
		String query = "select * from member where member_id=?";
		Object[] params = { checkId };
		List list = jdbc.query(query, memberRowMapper, params);
		if (list.isEmpty()) {
			return null;
		} else {
			Member member = (Member) list.get(0);
			return member;
		}

	}

	public int updateMember(Member m) {
		String query = "update member set member_pw=?, member_phone=?, member_email=? where member_no=?";
		Object[] params = { m.getMemberPw(), m.getMemberPhone(), m.getMemberEmail(), m.getMemberNo() };
		int result = jdbc.update(query, params);
		return result;
	}

	public int deleteMember(int memberNo) {
		String query = "update member set del_yn ='Y' where member_no=?";
		Object[] params = { memberNo };
		int result = jdbc.update(query, params);
		return result;
	}

	public int insertdeleteMember(int memberNo) {
		String query = "insert into del_member values(del_member_seq.nextval,?,to_char(sysdate,'yyyy-mm-dd'),'N')";
		Object[] params = { memberNo };
		int result2 = jdbc.update(query, params);
		return result2;
	}

	public Member selectOneMemberId(Member m) {
		String query = "select * from member where member_name=? and member_phone=?";
		Object[] params = { m.getMemberName(), m.getMemberPhone() };
		List list = jdbc.query(query, memberRowMapper, params);
		if (list.isEmpty()) {
			return null;
		} else {
			Member member = (Member) list.get(0);
			return member;
		}

	}

	public Member selectOneMemberPw(Member m) {
		String query = "select * from member where member_id=? and member_name=? ";
		Object[] params = { m.getMemberId(), m.getMemberName() };
		List list = jdbc.query(query, memberRowMapper, params);
		if (list.isEmpty()) {
			return null;
		} else {
			Member member = (Member) list.get(0);
			return member;
		}

	}

	public int pwChange(Member m) {
		String query = "update member set member_pw =? where member_id=?";
		Object[] params = { m.getMemberPw(), m.getMemberId() };
		int result = jdbc.update(query, params);
		;
		return result;
	}

	public int insertBuMember(BuMember bum) {
		String query = "insert into member values(?,?,?,?,?,?,'business',1,'N')";
		Object[] params = { bum.getMemberNo(), bum.getMemberName(), bum.getMemberId(), bum.getMemberPw(),
				bum.getMemberEmail(), bum.getMemberPhone() };
		int result1 = jdbc.update(query, params);
		return result1;
	}

	public int newMemberNo() {
		String query = "select member_seq.nextval from dual";
		int memberNo = jdbc.queryForObject(query, Integer.class);
		return memberNo;
	}

	public int insertBusiness(BuMember bum) {
		String query = "insert into business values(?,?,?)";
		Object[] params = { bum.getMemberNo(), bum.getBusinessName(), bum.getBusinessNo() };
		int result2 = jdbc.update(query, params);
		return result2;
	}

	public int ajaxcommNo(String MemberId) {
		String query = "select count(*) from comm where member_id = ?";
		Object[] params = { MemberId };
		int result = jdbc.queryForObject(query, Integer.class, params);
		return result;
	}

	public int ajaxscoreNo(String MemberId) {
		String query = "select count(*) from content_star where member_id = ?";
		Object[] params = { MemberId };
		int result = jdbc.queryForObject(query, Integer.class, params);
		return result;
	}
}
