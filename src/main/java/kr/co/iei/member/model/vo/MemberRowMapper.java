package kr.co.iei.member.model.vo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;



@Component
public class MemberRowMapper implements RowMapper<Member>{
	@Override
	public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
		Member m = new Member();
		m.setDel(rs.getString("del_yn"));
		m.setMemberEmail(rs.getString("member_email"));
		m.setMemberId(rs.getString("member_id"));
<<<<<<< HEAD
		m.setWarningLevel(rs.getInt("warning_level"));
		m.setMemberName(rs.getString("member_name"));
		m.setMemberNo(rs.getInt("member_no"));
		m.setMemberPhone(rs.getString("member_phone"));
		m.setMemberPw(rs.getString("member_pw"));
		m.setMemberRole(rs.getString("member_role"));
		m.setWaringLevel(rs.getInt("warning_level"));
		m.setMemberName(rs.getString("member_name"));
		m.setMemberNo(rs.getInt("member_no"));
		m.setMemberPhone(rs.getString("member_phone"));
		m.setMemberPw(rs.getString("member_pw"));
		m.setMemberRole(rs.getString("member_role"));
=======
		m.setMemberLevel(rs.getInt("member_level"));
		m.setMemberName(rs.getString("member_name"));
		m.setMemberNo(rs.getInt("member_no"));
		m.setMemberPhone(rs.getString("member_phone"));
		m.setMemberPW("member_pw");
		m.setMemberRole("member_role");
>>>>>>> parent of f8e864b (2.18)
		return m;
		
	}

}
