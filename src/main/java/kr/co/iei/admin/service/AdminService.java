package kr.co.iei.admin.service;

import java.beans.Transient;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.iei.admin.model.dao.AdminDao;
import kr.co.iei.admin.vo.Report;
import kr.co.iei.admin.vo.Stats;

@Service
public class AdminService {
	@Autowired
	private AdminDao adminDao;

	public Stats loadStats() {
		Stats s = adminDao.loadStats(); 
		return s;
	}
	@Transactional
	public int insertReport(Report report) {
		int result = adminDao.insertReport(report);
		return result;
	}

	public List businessView() {
		List list = adminDao.businessView();
		return list;
	}
	public List newReport() {
		List list = adminDao.newReport();
		return list;
	}
	public List adView() {
		List list = adminDao.adView();
		return list;
	}
}
