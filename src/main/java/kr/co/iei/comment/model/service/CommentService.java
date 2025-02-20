package kr.co.iei.comment.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



import kr.co.iei.comment.model.dao.CommentDao;
import kr.co.iei.comment.model.vo.Comment;
import kr.co.iei.comment.model.vo.ReComment;
import kr.co.iei.contents.model.vo.DbMovie;





@Service
public class CommentService {
	
	@Autowired
	private CommentDao commentDao;
	
	@Transactional
	public int insertComment(Comment comment) {
		/*
		String category = comment.getContentNo().substring(0, 1);
		String id = comment.getContentNo().substring(1);
		*/
		int result = commentDao.insertComment(comment);
		return result;
	}

	public List mCommentMemberList() {
		List<Comment> list = commentDao.mCommentMemberList();
		for(Comment c : list ) {
			//1. contentNo 로 movie테이블을 조회해서 movie_title, poster_path 조회
			String contentNo = c.getContentNo().substring(1);
			DbMovie movie = commentDao.selectMovieInfo(contentNo);
					
			//2. 조회 결과를 c에 저장
			c.setContentTitle(movie.getMovieTitle());
			c.setPosterPath(movie.getPosterPath());		
		
		}
		//코멘트 테이블에서 데이터 읽어온 Comment 객체마다 movieTitle 변수와 posterPath 변수에 데이터 저장
		return list;
	}

	
	public List mCommentList(String contentNo) {		
		List<Comment> list = commentDao.mCommentList(contentNo);
		return list;
	}
	
	@Transactional
	public String selectMovieTitle(String contentNo) {
		
		String partOne = contentNo.substring(0,1);
		String partTwo = contentNo.substring(1);
		String partTwoPh = contentNo.substring(1);
		
		if(partOne.equals("m")) {		
			String result = commentDao.movieCode(partTwo);
			String result2 = commentDao.movieCode(partTwo);
			
			return result;
		}		
		return null;
	}
	
	// 코멘트 개수 반환
    public int getTotalCommentCount() {
        return commentDao.getCommentCount();
    }

	@Transactional
	public int deleteComm(int commentNo) {
		int result = commentDao.deleteComm(commentNo);
		return result;
	}


	@Transactional
	public Comment selectOneComm(int commentNo) {
		Comment c = commentDao.selectOneComm(commentNo);
		return c;
	}
//	n.getNoticeTitle(), n.getNoticeContent(), n.getNoticeNo()
	@Transactional
	public int updateComm(Comment c) {
		int result = commentDao.updateComm(c);
		
		return result;
	}

	
	@Transactional
	public int reCommInsert(ReComment rc) {
		int result = commentDao.reCommInsert(rc);
		System.out.println(rc);
		return result;
	}

	

	
}
