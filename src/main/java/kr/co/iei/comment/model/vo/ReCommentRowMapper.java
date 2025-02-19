package kr.co.iei.comment.model.vo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class ReCommentRowMapper implements RowMapper<ReComment>{

	@Override
	public ReComment mapRow(ResultSet rs, int rowNum) throws SQLException {
		ReComment rc = new ReComment();
		rc.setReCommentNo(rs.getInt("re_comment_no"));
		rc.setMemberId(rs.getString("member_id"));
		rc.setCommentNo(rs.getInt("comment_no"));
		rc.setReCommentContent(rs.getString("re_comment_content"));
		rc.setReRegDate(rs.getString("re_reg_date"));
		
		return rc;
	}

}
