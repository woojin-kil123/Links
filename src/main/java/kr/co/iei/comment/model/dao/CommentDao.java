package kr.co.iei.comment.model.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.co.iei.comment.model.vo.Comment;
import kr.co.iei.comment.model.vo.CommentRowMapper;

@Repository
public class CommentDao {
	
	@Autowired
	private JdbcTemplate jdbc;
	
	@Autowired
	private CommentRowMapper commentRowMapper;

	public List mCommentMemberList() {
		String query = "select * from comm";
		List list = jdbc.query(query,commentRowMapper);
		if(list.isEmpty()) {
			return null;
			
		}
		
		return list;
	}

	public List mCommentList(String contentNo) {
		String query = "select * from comm where content_no like '%'||?||'%' ";
		Object[] params= {contentNo};
		List list = jdbc.query(query,commentRowMapper,params);
		
			
		return list;
	}

	public String movieTitle(String partTwo) {
		String query = "select movie_title from movie where movie_no = ?";
		Object[] params= {partTwo};
		String result = jdbc.queryForObject(query,String.class,params);
		return result;
	}

	public int deleteComm(int commentNo) {
		String query = "delete from comm where comment_no = ?";
		Object[] params = {commentNo};
		int result = jdbc.update(query,params);
 		
		return result;
	}

	public Comment selectOneComm(int commentNo) {
		String query = "select * from comm where comment_no = ? ";
		Object[] params = {commentNo};
		List list = jdbc.query(query, commentRowMapper, params);
		if(list.isEmpty()) {
			return null;
		}else {
			Comment c = (Comment)list.get(0);
			return c;
		}
		
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
