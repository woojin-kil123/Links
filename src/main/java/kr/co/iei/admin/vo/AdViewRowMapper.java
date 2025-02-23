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
		ad.setAdPosition(rs.getString("ad_position"));
		ad.setAdUrl(rs.getString("AD_URL"));
		ad.setExpireDate(rs.getDate("expire_date"));
		return ad;
	}

}
