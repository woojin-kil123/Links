package kr.co.iei.admin.vo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import kr.co.iei.member.model.vo.Member;

@Component
public class KickedRowMapper implements RowMapper<Member>{

	@Override
	public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
		Member m = new Member();
		m.setMemberId(rs.getString("member_id"));
		m.setMemberPhone(rs.getString("member_phone"));
		m.setMemberEmail(rs.getString("member_email"));
		m.setDelDate(rs.getString("del_date"));
		return m;
	}

}
