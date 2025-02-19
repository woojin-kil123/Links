package kr.co.iei.contents.model.vo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class DbMovieRowMapper implements RowMapper<DbMovie>{

	@Override
	public DbMovie mapRow(ResultSet rs, int rowNum) throws SQLException {
		DbMovie m = new DbMovie();
		m.setMovieId(rs.getString("movie_id"));
		m.setMovieTitle(rs.getString("movie_title"));
		m.setMovieAvgPoint(rs.getString("movie_avgpoint"));
		m.setMoviePlatform(rs.getString("movie_platform"));
		m.setLinkClick(rs.getInt("link_click"));
		m.setPosterPath("https://image.tmdb.org/t/p/w342"+rs.getString("poster_path"));
		return m;
	}

}
