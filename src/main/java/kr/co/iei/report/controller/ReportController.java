package kr.co.iei.report.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;


import kr.co.iei.member.model.vo.Member;
import kr.co.iei.report.model.service.ReportService;

@Controller
@RequestMapping("/report")
public class ReportController {
	@Autowired
    private ReportService reportService;
	
	@GetMapping(value="/check", produces = "plain/text;charset=utf-8")
	@ResponseBody
	public String checkReportReason(@SessionAttribute Member member) {
	    
	    String reportReason = reportService.getReportReason(member.getMemberNo());

	    
	    
	    if (reportReason != null && !reportReason.isEmpty()) {
	        return "🔔 " + reportReason;
	    }
	    
	    return "😊 좋은 하루 되세요.";
	}
}

