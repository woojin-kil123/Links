package kr.co.iei.comment.model.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.co.iei.comment.model.vo.Comment;
import kr.co.iei.comment.model.vo.CommentRowMapper;
import kr.co.iei.contents.model.vo.DbMovie;
import kr.co.iei.contents.model.vo.DbMovieRowMapper;

@Repository
public class CommentDao {
	
	@Autowired
	private JdbcTemplate jdbc;
	
	@Autowired
	private CommentRowMapper commentRowMapper;
	
	@Autowired
	private DbMovieRowMapper dbMovieRowMapper;

	public int insertComment(Comment comment) {
		String query = "insert into comm values(comm_seq.nextval, ?, ?, ?,0, to_char(sysdate, 'mm-dd hh:mi'))";
		Object[] params = {comment.getMemberId(), comment.getContentNo(), comment.getCommentContent()};
		int result = jdbc.update(query, params);
		return result;
	}
	
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

	public String movieCode(String partTwo) {
		String query = "select movie_title from movie where movie_id = ?";
		Object[] params= {partTwo};
		String result = jdbc.queryForObject(query,String.class,params);
		return result;
	}
	
	//총 코멘트 개수 조회
	public int getCommentCount() {
        String sql = "SELECT COUNT(*) FROM comm";
        return jdbc.queryForObject(sql, Integer.class);
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

	public DbMovie selectMovieInfo(String contentNo) {
		String query = "select * from movie where movie_id = ?";
		Object[] params = {contentNo};
		
		List<DbMovie> list = jdbc.query(query,dbMovieRowMapper,params);
		if(list.isEmpty()) {
			return null;
		}
		DbMovie movie = list.get(0);
		return movie;
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
