package kr.co.iei.admin.model.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.co.iei.admin.vo.Ad;
import kr.co.iei.admin.vo.AdViewRowMapper;
import kr.co.iei.admin.vo.BusinessRowMapper;
import kr.co.iei.admin.vo.Report;
import kr.co.iei.admin.vo.ReportRowMapper;
import kr.co.iei.admin.vo.Stats;
import kr.co.iei.admin.vo.StatsRowMapper;

@Repository
public class AdminDao {
	@Autowired
	private JdbcTemplate jdbc;
	@Autowired
	private StatsRowMapper statsRowMapper;
	@Autowired
	private BusinessRowMapper businessRowMapper;
	@Autowired
	private ReportRowMapper reportRowMapper;
	@Autowired
	private AdViewRowMapper adViewRowMapper;
	
	public Stats loadStats() {
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
		String query = "insert into report values(?,?,?,?,null,to_char(sysdate, 'mm-dd hh:mi'))";
		Object[] params = {report.getWriteNo(), report.getReporterMemberId(), report.getReportedMemberId(), report.getReportReason()};
		int result = jdbc.update(query, params);
		return result;
	}

	public List businessView() {
		String query = "select * from business_view";
		List list = jdbc.query(query, businessRowMapper);
		return list;
	}

	public List newReport() {
		String query = "SELECT * FROM REPORT WHERE REPORT_YN IS NULL";
		List list = jdbc.query(query, reportRowMapper);
		return list;
	}

	public List adView() {
		String query = "select * from ad_view";
		List list = jdbc.query(query, adViewRowMapper);
		return list;
	}

	public int insertAd(Ad ad) {
		String query = "insert into ad values(ad_seq.nextval,?,?,?,?,0)";
		Object[] params = {ad.getInquiryNo(), ad.getAdPosition(),ad.getAdUrl(),ad.getExpireDate()};
		int result = jdbc.update(query, params);
		return result;
	}

	public int updateInquiryProgress(int progress, int inquiryNo) {
		String query = "update inquiry set inquiry_progress=? where inquiry_no = ?";
		Object[] params = {progress, inquiryNo};
		int result = jdbc.update(query, params);
		return result;
	}

	public int deleteAd(int inquiryNo) {
		String query = "delete from ad where inquiry_no = ?";
		Object[] params = {inquiryNo};
		int result = jdbc.update(query, params);
		return result;
	}

	public String getAdUrlByPosition(String position) {
		//String sql = "SELECT * FROM ad_view WHERE AD_POSITION = UPPER(TRIM(?))";
		String sql = "SELECT * FROM ad_view WHERE AD_POSITION = ?";
		Object[] params = {position};
		List<Ad> list = jdbc.query(sql, adViewRowMapper, params);
		if(list.isEmpty()) {
			return null;
		}
		return list.get(0).getAdUrl();
	}
}
