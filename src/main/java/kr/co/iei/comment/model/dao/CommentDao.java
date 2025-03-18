package kr.co.iei.comment.model.dao;

import java.util.EmptyStackException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;import org.springframework.beans.factory.parsing.EmptyReaderEventListener;
import org.springframework.core.NestedRuntimeException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.co.iei.comment.model.vo.Comment;
import kr.co.iei.comment.model.vo.CommentRowMapper;
import kr.co.iei.comment.model.vo.ReComment;
import kr.co.iei.comment.model.vo.ReCommentRowMapper;
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
	
	@Autowired
	private ReCommentRowMapper reCommentRowMapper; 

	public int insertComment(Comment comment) {
		String query = "insert into comm values(comm_seq.nextval, ?, ?, ?,0, to_char(sysdate, 'mm-dd hh:mi'))";
		Object[] params = {comment.getMemberId(), comment.getContentNo(), comment.getCommentContent()};
		int result = jdbc.update(query, params);
		return result;
	}
	
	public List mCommentMemberList() {
		String query = "select * from comm order by COMMENT_NO DESC";
		List list = jdbc.query(query,commentRowMapper);
		if(list.isEmpty()) {
			return null;
			
		}
		
		return list;
	}

	public List mCommentList(String memberId) {
		String query = "select * from comm where member_id= ? order by comment_no desc" ; 
		Object[] params= {memberId};
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
	
	public int reDeleteComm(ReComment rc) {
		String query = "delete from re_comm where re_comment_no = ? ";
		Object[] params = {rc.getReCommentNo()};
		int result = jdbc.update(query,params);
 		
		return result;
		
	}

	public Comment selectOneComm(int commentNo) {
		String query = "select * from comm where comment_no = ?";
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

	public int updateComm(Comment c) {
		String query = " update comm set comment_content = ? where comment_no = ? ";
		Object[] params = {c.getCommentContent(),c.getCommentNo()};
		int result = jdbc.update(query,params);
		return result;
	}

	public int reCommInsert(ReComment rc) {
		String query = "insert into re_comm values(re_comm_seq.nextval,?,?,?,to_char(sysdate,'mm-dd hh:mi'))";
		Object[] params = {rc.getMemberId(),rc.getCommentNo(),rc.getReCommentContent()};
		
		int result = jdbc.update(query,params);
		return result;
	}
	

	public List commNo(int commentNo) {
		String query = "select * from re_comm where comment_no= ?  ";
		Object[] params = {commentNo};
		List listNo = jdbc.query(query, reCommentRowMapper, params);
		
		return listNo;
	}

	

	

	public List oneMovieComment(String contentNo) {
		String query = "select * from comm where content_no=? order by COMMENT_NO DESC";
		Object[] params = {contentNo};
		List list = jdbc.query(query, commentRowMapper, params);
		return list;
	}
	
	
	public int reCommUpdate(ReComment rc) {
		String query = " update re_comm set re_comment_content = ? where re_comment_no = ? " ;
		Object[] params = {rc.getReCommentContent(),rc.getReCommentNo()};
		int result = jdbc.update(query,params);
		return result;
	}

	public List selectNewMovieCommList() {
		String query = "select * from comm order by reg_date DESC";
		List list = jdbc.query(query, commentRowMapper);
		return list;
	}

	public int insertCommentLike(int commentNo, int memberNo) {
		String query = "insert into comment_like values(? , ? )";
		Object[] params = {commentNo, memberNo};
		int result = jdbc.update(query, params);
		return result;
	}

	public int deleteCommentLike(int commentNo, int memberNo) {
		String query = "delete from comment_like where comment_no = ? and member_no = ? ";
		Object[] params = {commentNo, memberNo};
		int result = jdbc.update(query, params);
		return result;
	}

	public int selectCommentLikeCount(int commentNo) {
		String query = "select count(*) from comment_like where comment_no = ? ";
		Object[] params = {commentNo};
		int likeCount = jdbc.queryForObject(query, Integer.class,params);
		return likeCount;
		
	}

	

	public int selectReCommCount(int commentNo) {
		String query = "select count(*) from re_comm where comment_no = ?";
		Object[] param = {commentNo};
		int reCommCount = jdbc.queryForObject(query, Integer.class,param);
		return reCommCount;
	}

	
	
	//댓글의 comment번호 조회해오는 dao
	public int selectRefCommentNo(int recommentNo) {
		String query = "select comment_no from re_comm where re_comment_no=?";
		Object[] param = {recommentNo};
		int result = jdbc.queryForObject(query, Integer.class,param);
		return result;
	}

	

	public int starP(String memberId, String contentNo) {
		int starP = 0;
		String query = " select starpoint from content_star where member_id = ? and content_no = ? " ;
		Object[] param = {memberId,contentNo};
		try {
			starP = jdbc.queryForObject(query, Integer.class, param);
		}
		catch(EmptyResultDataAccessException e) {
		}
		
		return starP;
	}
	
	
	
	

	

	
		
	

}




