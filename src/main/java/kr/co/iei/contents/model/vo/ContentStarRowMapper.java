package kr.co.iei.contents.model.vo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component 
public class ContentStarRowMapper implements RowMapper<ContentStar>{

	@Override
	public ContentStar mapRow(ResultSet rs, int rowNum) throws SQLException {
		ContentStar cp = new ContentStar();
		cp.setContentNo(rs.getString("content_no"));
		cp.setMemberId(rs.getString("member_id"));
		cp.setStarpoint(rs.getInt("starpoint"));
		return cp;
	}
	
}
