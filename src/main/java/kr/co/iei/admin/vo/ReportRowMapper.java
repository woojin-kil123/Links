package kr.co.iei.admin.vo;


import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class ReportRowMapper implements RowMapper<Report>{
	@Override
	public Report mapRow(ResultSet rs, int rowNum) throws SQLException {
		Report r = new Report();
		r.setWriteNo(rs.getString("write_no"));
		r.setReportedMemberId(rs.getString("reported_member_id"));
		r.setReporterMemberId(rs.getString("reporter_member_id"));
		r.setReportReason(rs.getString("report_reason"));
		r.setReportYn(rs.getString("report_yn"));
		r.setReg_date(rs.getString("reg_date"));
		return r;
	}

}
