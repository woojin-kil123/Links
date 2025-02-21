package kr.co.iei.member.model.vo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;



@Component
public class BuMemberRowMapper implements RowMapper<BuMember>{
	@Override
	public BuMember mapRow(ResultSet rs, int rowNum) throws SQLException {
		BuMember bm = new BuMember();
		bm.setDel(rs.getString("del_yn"));
		bm.setMemberEmail(rs.getString("member_email"));
		bm.setMemberId(rs.getString("member_id"));
		bm.setWarningLevel(rs.getInt("warning_level"));
		bm.setMemberName(rs.getString("member_name"));
		bm.setMemberNo(rs.getInt("member_no"));
		bm.setMemberPhone(rs.getString("member_phone"));
		bm.setMemberPw(rs.getString("member_pw"));
		bm.setMemberRole(rs.getString("member_role"));
		bm.setWarningLevel(rs.getInt("warning_level"));
		bm.setBusinessName(rs.getString("company_name"));
		bm.setBusinessNo(rs.getInt("company_no"));
		return bm;
		
	}

}
