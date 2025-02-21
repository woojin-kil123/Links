package kr.co.iei.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.iei.admin.model.dao.AdminDao;

@Service
public class AdminService {
	@Autowired
	private AdminDao adminDao;
}
