package kr.co.iei.member.model.service;

import java.util.List;

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
	public int deleteMember(String memberId) {
		int result=memberDao.deleteMember(memberId);
		int result2 =memberDao.insertdeleteMember(memberId);
		return result;
	}	
	
	public Member selectOneMemberId(Member m) {
		Member member= memberDao.selectOneMemberId(m);
		return member;
	}
	public Member selectOneMemberPw(Member m) {
		Member member= memberDao.selectOneMemberPw(m);
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
	//사업자 정보 읽어오는 서비스
	public BuMember selectBusiness(int memberNo) {
		BuMember bm = memberDao.selectBusiness(memberNo);
		return bm;
	}
	
}
