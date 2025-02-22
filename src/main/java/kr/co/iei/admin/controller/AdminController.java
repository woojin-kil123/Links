package kr.co.iei.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
	@GetMapping("/stats")
	public Stats stats() {
		Stats stats  = adminService.stats();
		return stats;
	}
	
	@ResponseBody
	@PostMapping("/insertReport")
	public int insertReport(Report report) {
		int result = adminService.insertReport(report);
		return result;
	}
}
