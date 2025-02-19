package kr.co.iei.comment.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import kr.co.iei.comment.model.dao.CommentDao;
import kr.co.iei.comment.model.vo.Comment;

@Service
public class CommentService {
	
	@Autowired
	private CommentDao commentDao;

	public List mCommentMemberList() {
		List list = commentDao.mCommentMemberList();
		return list;
	}

	
	public List mCommentList(String contentNo) {		
		List list = commentDao.mCommentList(contentNo);
		
		return list;
	}
	
	@Transactional
	public String selectMovieTitle(String contentNo) {
		String partOne = contentNo.substring(0,1);
		String partTwo = contentNo.substring(1);		
		if(partOne.equals("m")) {		
			String result = commentDao.movieTitle(partTwo);
			System.out.println(result);
		}		
		return partTwo;
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



	
}
