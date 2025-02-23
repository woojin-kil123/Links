package kr.co.iei.admin.vo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class BusinessRowMapper implements RowMapper<Business> {

	@Override
	public Business mapRow(ResultSet rs, int rowNum) throws SQLException {
		Business b = new Business();
		b.setCompanyName(rs.getString("company_name"));
		b.setInquiryCategory(rs.getString("inquiry_category"));
		b.setMemberPhone(rs.getString("member_phone"));
		b.setInquiryDate(rs.getString("inquiry_date"));
		b.setInquiryProgress(rs.getString("inquiry_progress"));
		return b;
	}

}
