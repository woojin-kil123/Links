package kr.co.iei.comment.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.iei.comment.model.dao.CommentDao;
import kr.co.iei.comment.model.vo.Comment;

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
		List list = commentDao.mCommentMemberList();
		return list;
	}

	
	public List mCommentList(String contentNo) {		
		List list = commentDao.mCommentList(contentNo);
		
		return list;
	}

	public String selectMovieTitle(String contentNo) {
		String partOne = contentNo.substring(0,1);
		String partTwo = contentNo.substring(1);		
		if(partOne.equals("m")) {		
			String result = commentDao.movieTitle(partTwo);
			System.out.println(result);
		}		
		return partTwo;
	}
	
	// 코멘트 개수 반환
    public int getTotalCommentCount() {
        return commentDao.getCommentCount();
    }
	
}
