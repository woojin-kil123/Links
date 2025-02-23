package kr.co.iei.admin.vo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class StatsRowMapper implements RowMapper<Stats> {

	@Override
	public Stats mapRow(ResultSet rs, int rowNum) throws SQLException {
		Stats s= new Stats();
		s.setMemberCount(rs.getInt("member_count"));
		s.setAdClickCount(rs.getInt("adclick_count"));
		s.setLinkCount(rs.getInt("link_count"));
		s.setReportCount(rs.getInt("report_count"));
		return s;
	}

}
