package kr.co.iei.member.vo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;



@Component
public class MemberRowMapping implements RowMapper<Member>{
	@Override
	public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
		Member m = new Member();
		m.setDel(rs.getString("del_yn"));
		m.setMemberEmail(rs.getString("member_email"));
		m.setMemberId(rs.getString("member_id"));
		m.setMemberLevel(rs.getInt("member_level"));
		m.setMemberName(rs.getString("member_name"));
		m.setMemberNo(rs.getInt("member_no"));
		m.setMemberPhone(rs.getString("member_phone"));
		m.setMemberPW("member_pw");
		m.setMemberRole("member_role");
		return m;
		
	}

}
