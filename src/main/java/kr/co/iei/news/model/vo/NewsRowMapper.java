package kr.co.iei.news.model.vo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class NewsRowMapper implements RowMapper<News> {

	@Override
	public News mapRow(ResultSet rs, int rowNum) throws SQLException {
		News n = new News();
		n.setNewsContent(rs.getString("news_content"));
		n.setNewsNo(rs.getInt("news_no"));
		n.setNewsTitle(rs.getString("news_title"));
		n.setMemberId(rs.getString("member_id"));
		n.setNewsReadCount(rs.getInt("news_read_count"));
		n.setNewsRegDate(rs.getString("news_reg_date"));
		return n;
	}
}
