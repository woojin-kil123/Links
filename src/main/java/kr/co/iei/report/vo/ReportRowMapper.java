package kr.co.iei.report.vo;


import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class ReportRowMapper implements RowMapper<Report>{
	@Override
	public Report mapRow(ResultSet rs, int rowNum) throws SQLException {
		Report r = new Report();
		r.setReportedMemberNo(rs.getInt("reported_member_no"));
		r.setReporterMemberNo(rs.getInt("reporter_member_no"));
		r.setReportReason(rs.getString("report_reason"));
		r.setReportYn(rs.getString("report_yn"));
		return r;
	}

}
