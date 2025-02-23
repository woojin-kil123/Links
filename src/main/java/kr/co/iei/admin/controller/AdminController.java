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
	public int insertReport(Report report) {
		int result = adminService.insertReport(report);
		return result;
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
		System.out.println(list);
		return list;
	}
	
	@ResponseBody
	@GetMapping("/adView")
	public List adView() {
		List list = adminService.adView();
		System.out.println(list);
		return list;
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
	
	
	
}
