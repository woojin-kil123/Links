package kr.co.iei.comment.model.service;

<<<<<<< HEAD
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
=======
>>>>>>> parent of f8e864b (2.18)
import org.springframework.stereotype.Service;

import kr.co.iei.comment.model.dao.CommentDao;

@Service
public class CommentService {
<<<<<<< HEAD
	
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
	
=======
	public CommentDao commentDao;
>>>>>>> parent of f8e864b (2.18)
}
