package kr.co.iei.member.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
<<<<<<< HEAD
import org.springframework.transaction.annotation.Transactional;
=======
>>>>>>> parent of f8e864b (2.18)

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
<<<<<<< HEAD
	@Transactional
	public int insertMember(Member m) {
		int result =memberDao.insertMember(m);
		return result;
	}
	public Member selectOneMember(String checkId) {
		Member member =memberDao.selectOneMember(checkId);
		return member;
	}
	@Transactional
	public int updateMember(Member m) {
		int result=memberDao.updateMember(m);
		return result;
	}
	@Transactional
	public int deleteMember(int memberNo) {
		int result1=memberDao.deleteMember(memberNo);
		return result1;
	}
	public int insertdeleteMember(int memberNo) {
		int result2 =memberDao.insertdeleteMember(memberNo);
		return result2;
	}
 
=======

>>>>>>> parent of f8e864b (2.18)
}
