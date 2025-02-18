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
}
