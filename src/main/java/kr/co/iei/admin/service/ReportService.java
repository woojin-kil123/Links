package kr.co.iei.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.iei.admin.model.dao.ReportDao;
import kr.co.iei.admin.vo.Report;
import kr.co.iei.member.model.vo.Member;

@Service
public class ReportService {

    @Autowired
    private ReportDao reportDao;

    public String getReportReason(String memberId) {
        List<Report> reportReason = reportDao.selectReportReason(memberId);
        if(reportReason.isEmpty()) {
        	return null;
        }else {
        	Report report = reportReason.get(0);
        	String reason = report.getReportReason();
        	return reason;
        }
        
    }

	public Member getWarningLevel(String memberId) {
		Member m = reportDao.selectWarningLevel(memberId);
		return m;
	}
}