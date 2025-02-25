package kr.co.iei.admin.vo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class DangerMemberRowmapper implements RowMapper<DangerMember> {

	@Override
	public DangerMember mapRow(ResultSet rs, int rowNum) throws SQLException {
		DangerMember dm = new DangerMember();
		dm.setMemberId(rs.getString("member_id"));
		dm.setMemberPhone(rs.getString("member_phone"));
		dm.setMemberEmail(rs.getString("member_email"));
		dm.setReported(rs.getInt("reported"));
		dm.setFalseReport(rs.getInt("false_report"));
		dm.setWarningLevel(rs.getInt("warning_level"));
		return dm;
	}
	
}
