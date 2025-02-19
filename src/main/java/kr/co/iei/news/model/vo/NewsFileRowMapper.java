package kr.co.iei.news.model.vo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class NewsFileRowMapper implements RowMapper<NewsFile>{

	@Override
	public NewsFile mapRow(ResultSet rs, int rowNum) throws SQLException {
		NewsFile file = new NewsFile();
		file.setFilename(rs.getString("filename"));
		file.setFilepath(rs.getString("filepath"));
		file.setNewsFileNo(rs.getInt("news_file_no"));
		file.setNewsNo(rs.getInt("news_no"));
		return file;
	}
}
