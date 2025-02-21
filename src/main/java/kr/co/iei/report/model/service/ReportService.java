package kr.co.iei.report.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.iei.member.model.vo.Member;
import kr.co.iei.report.model.dao.ReportDao;
import kr.co.iei.report.vo.Report;

@Service
public class ReportService {

    @Autowired
    private ReportDao reportDao;

    public String getReportReason(int memberNo) {
        List<Report> reportReason = reportDao.selectReportReason(memberNo);
        if(reportReason.isEmpty()) {
        	return null;
        }else {
        	Report report = reportReason.get(0);
        	String reason = report.getReportReason();
        	return reason;
        }
        
    }
}