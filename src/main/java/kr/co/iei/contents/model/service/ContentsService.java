package kr.co.iei.contents.model.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.iei.contents.model.dao.ContentsDao;
import kr.co.iei.contents.model.vo.ContentStar;
import kr.co.iei.contents.model.vo.DbMovie;

@Service
public class ContentsService {
	
	@Autowired
	private ContentsDao contentsDao;
	
	public DbMovie selectMovie(String movieId) {
		DbMovie movie = contentsDao.selectMovie(movieId);
		return movie;
	}
	@Transactional
	public int insertMovie(DbMovie movie) {
		int result = contentsDao.insertMovie(movie);
		return result;
	}
	@Transactional
	public int insertRating(ContentStar cs) {
		int result = contentsDao.insertRating(cs);
		double avgStar = contentsDao.selectAvgPoint(cs.getContentNo());
		System.out.println(avgStar);
		String movieId = cs.getContentNo().substring(1);
		result += contentsDao.updateMovieStar(avgStar,movieId);
		return result;
	}
	public int selectMemberStar(ContentStar cs) {
		int result = contentsDao.selectMemberStar(cs);
		return result;
	}
	public int insertContentLike(ContentStar cs) {
		int result = contentsDao.insertContentLike(cs);
		return result;
	}
	public int deleteContentLike(ContentStar cs) {
		int result = contentsDao.deleteContentLike(cs);
		return result;
	}
	public int selectMemberLike(ContentStar cs) {
		int result = contentsDao.selectMemberLike(cs);
		return result;
	}
	public int plusLinkClick(int movieId) {
		int result = contentsDao.plusLinkClick(movieId);
		return result;
	}
	public List myContents(String memberId) {
		List<DbMovie> movieList = new ArrayList<>();
		List<String> contentsList = contentsDao.selectContentLike(memberId);
		for(String c : contentsList) {
			String movieId = c.substring(1);
			DbMovie movie = contentsDao.selectMovie(movieId);
			movieList.add(movie);
		}
		return movieList;
	}

}
