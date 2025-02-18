package kr.co.iei.comment.model.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.co.iei.comment.model.vo.CommentRowMapper;
import kr.co.iei.member.vo.Member;

@Repository
public class CommentDao {
	
	@Autowired
	private JdbcTemplate jdbc;
	
	@Autowired
	private CommentRowMapper commentRowMapper;

	public List mCommentMemberList() {
		String query = "select * from comm";
		List list = jdbc.query(query,commentRowMapper);
		
		return list;
	}

	public List mCommentList(String contentNo) {
		String query = "select * from comm where content_no like '%'||?||'%' ";
		Object[] params= {contentNo};
		List list = jdbc.query(query,commentRowMapper,params);
		
			
		return list;
	}
}
/*
	public Member selectOneMember(Member m) {
	String query = "select * from member_tbl where member_id=? and member_pw=?";
	Object[] params= {m.getMemberId(),m.getMemberPw()};
	List list = jdbc.query(query, memberRowMapper,params);
	if(list.isEmpty()) {
		return null;
	}else {
		Member member = (Member)list.get(0);
		return member;
	}
}
*/