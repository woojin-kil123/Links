package kr.co.iei.member.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.iei.member.model.dao.MemberDao;
import kr.co.iei.member.model.vo.Member;

@Service
public class MemberSerivce {
	@Autowired
	private MemberDao memberDao;
	
	public Member selectOneMember(Member m) {
		Member member= memberDao.selectOneMember(m);
		return member;
	}
	@Transactional
	public int insertMember(Member m) {
		int result =memberDao.insertMember(m);
		return result;
	}
	public Member selectOneMember(String checkId) {
		Member member =memberDao.selectOneMember(checkId);
		return member;
	}

}
