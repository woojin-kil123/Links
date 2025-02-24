package kr.co.iei.member.model.vo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
@Component
public class BusinessMemberRowMapper implements RowMapper<BuMember> {

	@Override
	public BuMember mapRow(ResultSet rs, int rowNum) throws SQLException {
		BuMember b= new BuMember();
		b.setCompanyNo(rs.getString("company_no"));
		b.setBusinessName(rs.getString("company_name"));
		b.setMemberNo(rs.getInt("member_no"));
		return b;
	}

}
