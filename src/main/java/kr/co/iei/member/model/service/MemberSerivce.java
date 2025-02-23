package kr.co.iei.member.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import kr.co.iei.member.model.dao.MemberDao;
import kr.co.iei.member.model.vo.BuMember;
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
	@Transactional
	public int updateMember(Member m) {
		int result=memberDao.updateMember(m);
		return result;
	}
	@Transactional
	public int deleteMember(int memberNo) {
		int result=memberDao.deleteMember(memberNo);
		int result2 =memberDao.insertdeleteMember(memberNo);
		return result;
	}	
	
	public Member selectOneMember2(Member m) {
		Member member= memberDao.selectOneMember2(m);
		return member;
	}
	public Member selectOneMember3(Member m) {
		Member member= memberDao.selectOneMember3(m);
		return member;
	}
	@Transactional
	public int pwChange(Member m) {
		int result= memberDao.pwChange(m);
		return result;
	}
	public int insertBuMember(BuMember bum) {
		int memberNo= memberDao.newMemberNo();
		bum.setMemberNo(memberNo);
		int result1= memberDao.insertBuMember(bum);
		int result2= memberDao.insertBusiness(bum);
		return result1;
	}
	public int ajaxcommNo(String MemberId) {
		int result =memberDao.ajaxcommNo(MemberId);
		return result;
	}
	public int ajaxscoreNo(String MemberId) {
		int result =memberDao.ajaxscoreNo(MemberId);
		return result;
		
	}
}
