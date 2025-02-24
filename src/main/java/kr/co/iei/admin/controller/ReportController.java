package kr.co.iei.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import kr.co.iei.admin.service.ReportService;
import kr.co.iei.member.model.vo.Member;

@Controller
@RequestMapping("/report")
public class ReportController {
	@Autowired
    private ReportService reportService;
	
	@GetMapping(value="/check", produces = "plain/text;charset=utf-8")
	@ResponseBody
	public String checkReportReason(@SessionAttribute Member member) {
	    
	    String reportReason = reportService.getReportReason(member.getMemberId());
	    if (reportReason != null && !reportReason.isEmpty()) {
	        return "🔔 " + reportReason + " 신고를 받았습니다.";
	    }
	    
	    return "😊 좋은 하루 되세요.";
	}
	
	@GetMapping(value="/checkWarningLv")
	@ResponseBody
	public int checkWarningLv(@SessionAttribute Member member) {
		Member m = reportService.getWarningLevel(member.getMemberId());
		return m.getWarningLevel();
	}
}

