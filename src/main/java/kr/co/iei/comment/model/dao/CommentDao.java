package kr.co.iei.comment.model.dao;

<<<<<<< HEAD
import java.util.List;

=======
>>>>>>> parent of f8e864b (2.18)
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

<<<<<<< HEAD
import kr.co.iei.comment.model.vo.Comment;
import kr.co.iei.comment.model.vo.CommentRowMapper;
import kr.co.iei.member.vo.Member;
=======
import kr.co.iei.comment.model.vo.CommentRowMapper;
>>>>>>> parent of f8e864b (2.18)

@Repository
public class CommentDao {
	
	@Autowired
	private JdbcTemplate jdbc;
	
	@Autowired
	private CommentRowMapper commentRowMapper;
<<<<<<< HEAD

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
=======
}
>>>>>>> parent of f8e864b (2.18)
