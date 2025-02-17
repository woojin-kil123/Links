package kr.co.iei.comment.controller;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping(value="/comment")
public class CommentController {

	
	
	
	
	@GetMapping(value="/mCommentMemberList")
	public String mCommentMemberList() {
		return "comment/mCommentMemberList";
	}
}
