package kr.co.iei.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpServletRequest;
import kr.co.iei.admin.service.AdminService;
import kr.co.iei.admin.vo.Ad;
import kr.co.iei.admin.vo.Inquiry;
import kr.co.iei.admin.vo.Report;
import kr.co.iei.admin.vo.Stats;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private AdminService adminService;
	
	@GetMapping("/management")
	public String management() {
		return "admin/management";
	}
	
	@ResponseBody
	@GetMapping("/loadStats")
	public Stats loadStats() {
		Stats stats  = adminService.loadStats();
		return stats;
	}
	
	@ResponseBody
	@PostMapping("/insertReport")
	public void insertReport(Report report) {
		int result = adminService.insertReport(report);
	}
	
	@ResponseBody
	@GetMapping("/businessView")
	public List businessView() {
		List list = adminService.businessView();
		return list;
	}
	@ResponseBody
	@GetMapping("/newReport")
	public List newReport() {
		List list = adminService.newReport();
		return list;
	}
	
	@ResponseBody
	@GetMapping("/adView")
	public List adView() {
		List list = adminService.adView();
		return list;
	}
	@ResponseBody
	@PostMapping("/insertAd")
	public void insertAd(Ad ad) {
		int result = adminService.insertAd(ad);
	}
	@ResponseBody
	@GetMapping("/deleteAd")
	public void deleteAd(int inquiryNo) {
		int result = adminService.deleteAd(inquiryNo);
	}
	@ResponseBody
	@GetMapping("/updateInquiry")
	public void updateInquiry(int inquiryProgress, int inquiryNo) {
		int result = adminService.updateInquiry(inquiryProgress,inquiryNo);
	}

	@ResponseBody
	@GetMapping("/plusAdClick")
	public void plusAdClick(String adPosition) {
		adminService.plusAdClick(adPosition);
	}
	
	@ResponseBody
	@GetMapping("/adUrl")
    public String getAdUrl(String position) {
        String adUrl = adminService.getAdUrlByPosition(position);
        if (adUrl == null || adUrl.isEmpty()) {
            adUrl = "";
            return adUrl;
        }else {
        	return adUrl;	
        }
    }
	@PostMapping("/insertInquiry")
	public String insertInquiry(Inquiry i) {
		if(i.getCompanyNo()==null) {
			i.setInquiryCategory("normal");
		}else {
			i.setInquiryCategory("business");
		}
		int result = adminService.insertInquiry(i);
		return "redirect:/";
	}
	@GetMapping("/inquiryView")
	public String inquiryView(int inquiryNo, Model model) {
		Inquiry i = adminService.selectInquiry(inquiryNo);
		model.addAttribute("i",i);
		return "admin/inquiryView";
	}
	@ResponseBody
	@GetMapping("/noramlView")
	public List noramlView() {
		List list = adminService.normalView();
		return list;
	}
	
	@ResponseBody
	@GetMapping("/updateReport")
	public void updateReport(Report r) {
		int result = adminService.updateReport(r);
	}
	
	@ResponseBody
	@GetMapping("/dangerUserList")
	public List dangerUserList() {
		List list = adminService.dangerUserList();
		return list;
	}
	@ResponseBody
	@GetMapping("/updateWarningLevel")
	public void updateWarningLevel(String memberId) {
		adminService.updateWarningLevel(memberId);
	}
	@ResponseBody
	@GetMapping("/kickMember")
	public void kickMember(String memberId) {
		adminService.kickMember(memberId);
	}	
	@ResponseBody
	@GetMapping("/kickedMember")
	public List kickedMember() {
		List list = adminService.kickedMember();
		return list;
	}
	
}
