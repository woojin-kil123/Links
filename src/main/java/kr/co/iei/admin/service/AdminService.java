package kr.co.iei.admin.service;

import java.beans.Transient;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.iei.admin.model.dao.AdminDao;
import kr.co.iei.admin.vo.Ad;
import kr.co.iei.admin.vo.Inquiry;
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
	@Transactional
	public int insertAd(Ad ad) {
		int result = adminDao.insertAd(ad);
		if(result>0) {
			result += adminDao.updateInquiryProgress(3, ad.getInquiryNo());
		}
		return result;
	}
	@Transactional
	public int deleteAd(int inquiryNo) {
		int result = adminDao.deleteAd(inquiryNo);
		if(result>0) {
			result += adminDao.updateInquiryProgress(4, inquiryNo);
		}
		return result;
	}
	public String getAdUrlByPosition(String position) {
		return adminDao.getAdUrlByPosition(position);
	}
	@Transactional
	public int updateInquiry(int inquiryProgress, int inquiryNo) {
		int result = adminDao.updateInquiryProgress(inquiryProgress, inquiryNo);
		return result;
	}
	@Transactional
	public int insertInquiry(Inquiry i) {
		int result = adminDao.insertInquiry(i);
		return result;
	}
	public Inquiry selectInquiry(int inquiryNo) {
		Inquiry i = adminDao.selectInquiry(inquiryNo);
		return i;
	}
	public List normalView() {
		List list = adminDao.normalView();
		return list;
	}
	public int updateReport(Report r) {
		int result = adminDao.updateReport(r);
		return result;
	}
	public List dangerUserList() {
		List list = adminDao.dangerUserList();
		return list;
	}
	@Transactional
	public void updateWarningLevel(String memberId) {
		adminDao.updateWarningLevel(memberId);
	}
	@Transactional
	public void kickMember(String memberId) {
		int result = adminDao.kickMember(memberId);
		if(result>0){
			adminDao.insertKickedMember(memberId);
		}
	}
	public List kickedMember() {
		List list = adminDao.kickedMember();
		return list;
	}
}
