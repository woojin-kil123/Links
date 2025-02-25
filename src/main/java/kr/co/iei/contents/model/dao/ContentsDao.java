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
	public int selectAvgPoint(String contentNo) {
		String query = "select avg(starpoint) from content_star where content_no=?";
		Object[] params = {contentNo};
		int avgStar = jdbc.queryForObject(query, Integer.class, params);
 		return avgStar;
	}
	public int updateMovieStar(int avgStar, String movieId) {
		String query = "update movie set movie_avgpoint = ? where movie_id= ?";
		Object[] params = {avgStar, movieId};
		int result = jdbc.update(query,params);
		return result;
	}
	public int selectMemberStar(ContentStar cs) {
		String query = "select starpoint from content_star where content_no=? and member_id=? ";
		Object[] params = {cs.getContentNo(),cs.getMemberId()};
		int result = jdbc.queryForObject(query, Integer.class, params);
		return result;
	}
	public int insertContentLike(ContentStar cs) {
		String query = "insert into content_like values(?,?)";
		Object[] params = {cs.getContentNo(),cs.getMemberId()};
		int result = jdbc.update(query, params);
		return result;
	}
	public int deleteContentLike(ContentStar cs) {
		String query = "delete from content_like where content_no=? and member_id=?";
		Object[] params = {cs.getContentNo(),cs.getMemberId()};
		int result = jdbc.update(query, params);
		return result;
	}
	public int selectMemberLike(ContentStar cs) {
		String query = "select count(*) from content_like where content_no=? and member_id=? ";
		Object[] params = {cs.getContentNo(),cs.getMemberId()};
		int result = jdbc.queryForObject(query, Integer.class, params);
		return result;
	}
	public int plusLinkClick(int movieId) {
		String query = "update movie set link_click=link_click+1 where movie_id=?";
		Object[] params = {movieId};
		int result = jdbc.update(query, params);
		return result;
	}
}
