package kr.co.iei.contents.model.service;

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
		return result;
	}

}
