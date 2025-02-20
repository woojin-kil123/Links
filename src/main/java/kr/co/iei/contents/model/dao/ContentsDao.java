package kr.co.iei.contents.model.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.co.iei.contents.model.vo.ContentStar;
import kr.co.iei.contents.model.vo.DbMovie;
import kr.co.iei.contents.model.vo.DbMovieRowMapper;

@Repository
public class ContentsDao {
	
	private String posterUrl = "https://image.tmdb.org/t/p/w342";
	@Autowired
	private DbMovieRowMapper dbMovieRowMapper;
	
	@Autowired
	private JdbcTemplate jdbc;

	public int insertMovie(DbMovie movie) {
		String query = "insert into movie values(?,?,null,null,0,?)";
		Object[] params = {movie.getMovieId(),movie.getMovieTitle(),posterUrl + movie.getPosterPath()};
		int result = jdbc.update(query, params);
		return result;
	}
	public DbMovie selectMovie(String movieId) {
		String query = "select * from movie where movie_id= ?";
		Object[] params = {movieId};
		List list = jdbc.query(query,dbMovieRowMapper,params);
		if(list.isEmpty()) {
			return null;
		}
		DbMovie movie = (DbMovie)list.get(0);
		return movie;
	}
	public int insertRating(ContentStar cs) {
		String query = "insert into content_star values(?,?,?)";
		Object[] params = {cs.getContentNo(),cs.getMemberId(),cs.getStarpoint()};
		int result = jdbc.update(query, params);
		return result;
	}
}
