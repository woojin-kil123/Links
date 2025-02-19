package kr.co.iei.comment.controller;

<<<<<<< HEAD
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.iei.comment.model.service.CommentService;

=======
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

>>>>>>> parent of f8e864b (2.18)


@Controller
@RequestMapping(value="/comment")
public class CommentController {
<<<<<<< HEAD
	
	@Autowired
	CommentService commentService;
=======

>>>>>>> parent of f8e864b (2.18)
	
	
	
	
<<<<<<< HEAD
	@GetMapping(value="/mCommentMemberList")	
	public String mCommentMemberList(Model model) {
		List list = commentService.mCommentMemberList();
		model.addAttribute("list", list);
=======
	@GetMapping(value="/mCommentMemberList")
	public String mCommentMemberList() {
>>>>>>> parent of f8e864b (2.18)
		return "comment/mCommentMemberList";
	}
	
	@GetMapping(value="/bCommentMemberList")
<<<<<<< HEAD
	public String bCommentMemberList() {		
=======
	public String bCommentMemberList() {
>>>>>>> parent of f8e864b (2.18)
		return "comment/bCommentMemberList";
	}
	
	@GetMapping(value="/mCommentList")
<<<<<<< HEAD
	public String mCommentList(String contentNo, Model model) {
		List list = commentService.mCommentList(contentNo);
		String movieTitle = commentService.selectMovieTitle(contentNo);
		
		model.addAttribute("list", list);
		model.addAttribute("movieTitle",movieTitle);
		return "comment/mCommentList";
	}
	
	 
	
=======
	public String mCommentList() {
		return "comment/mCommentList";
	}
	
>>>>>>> parent of f8e864b (2.18)
	@GetMapping(value="/bCommentList")
	public String bCommentList() {
		return "comment/bCommentList";
	}
}
