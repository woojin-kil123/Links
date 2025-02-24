package kr.co.iei.admin.vo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class AdViewRowMapper implements RowMapper<Ad>{

	@Override
	public Ad mapRow(ResultSet rs, int rowNum) throws SQLException {
		Ad ad = new Ad();
		ad.setCompanyName(rs.getString("company_name"));
		ad.setInquiryNo(rs.getInt("inquiry_no"));
		ad.setAdPosition(rs.getString("ad_position"));
		ad.setAdUrl(rs.getString("ad_url"));
		ad.setExpireDate(rs.getString("expire_date"));
		ad.setInquiryProgress(rs.getInt("inquiry_progress"));
		ad.setAdUrl(rs.getString("AD_URL"));
		ad.setExpireDate(rs.getString("expire_date"));
		return ad;
	}

}
