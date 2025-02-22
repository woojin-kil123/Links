package kr.co.iei.admin.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.iei.admin.model.dao.AdminDao;
import kr.co.iei.admin.vo.Report;
import kr.co.iei.admin.vo.Stats;

@Service
public class AdminService {
	@Autowired
	private AdminDao adminDao;

	public Stats stats() {
		Stats s = adminDao.stats(); 
		return s;
	}

	public int insertReport(Report report) {
		int result = adminDao.insertReport(report);
		return result;
	}
}
