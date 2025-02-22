package kr.co.iei.admin.model.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.co.iei.admin.vo.Report;
import kr.co.iei.admin.vo.Stats;
import kr.co.iei.admin.vo.StatsRowMapper;

@Repository
public class AdminDao {
	@Autowired
	private JdbcTemplate jdbc;
	@Autowired
	private StatsRowMapper statsRowMapper;
	
	public Stats stats() {
		String query = "select * from stats";
		List list = jdbc.query(query, statsRowMapper);
		if(list.isEmpty()) {
			return null;
		}else {
			Stats s = (Stats)list.get(0);
			return s;
		}
	}

	public int insertReport(Report report) {
		String query = "insert into report values(?,?,?,?,'n',to_char(sysdate, 'mm-dd hh:mi'))";
		Object[] params = {report.getWriteNo(), report.getReporterMemberId(), report.getReportedMemberId(), report.getReportReason()};
		int result = jdbc.update(query, params);
		return result;
	}
}
