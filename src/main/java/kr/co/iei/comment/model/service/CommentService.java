package kr.co.iei.comment.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.iei.comment.model.dao.CommentDao;

@Service
public class CommentService {
	
	@Autowired
	public CommentDao commentDao;

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
	
}
