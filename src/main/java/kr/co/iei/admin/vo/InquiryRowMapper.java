package kr.co.iei.admin.vo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class InquiryRowMapper implements RowMapper<Inquiry>{

	@Override
	public Inquiry mapRow(ResultSet rs, int rowNum) throws SQLException {
		Inquiry i = new Inquiry();
		i.setInquiryNo(rs.getInt("inquiry_no"));
		i.setCompanyName(rs.getString("company_name"));
		i.setCompanyNo(rs.getString("company_no"));
		i.setInquiryCategory(rs.getString("inquiry_category"));
		i.setInquiryContent(rs.getString("inquiry_content"));
		i.setInquiryDate(rs.getString("inquiry_date"));
		i.setMemberEmail(rs.getString("member_email"));
		i.setMemberId(rs.getString("member_id"));
		i.setMemberPhone(rs.getString("member_phone"));
		return i;
	}
	

}
