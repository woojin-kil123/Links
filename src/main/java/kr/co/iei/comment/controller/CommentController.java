package kr.co.iei.comment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.iei.comment.model.service.CommentService;
import kr.co.iei.comment.model.vo.Comment;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/comment")
public class CommentController {
	
	@Autowired
	private CommentService commentService;
	
	@ResponseBody
	@PostMapping("/insertComment")
	public int insertComment(Comment comment) {
		int result = commentService.insertComment(comment);
		return result;
	}
	
	@GetMapping(value="/mCommentMemberList")	
	public String mCommentMemberList(Model model) {
		List list = commentService.mCommentMemberList();
		model.addAttribute("list", list);
		return "comment/mCommentMemberList";
	}
	
	@GetMapping(value="/bCommentMemberList")
	public String bCommentMemberList() {
		return "comment/bCommentMemberList";
	}
	
	@GetMapping(value="/mCommentList")
	public String mCommentList(String contentNo, Model model) {
		List list = commentService.mCommentList(contentNo);
		String movieTitle = commentService.selectMovieTitle(contentNo);
		
		model.addAttribute("list", list);
		model.addAttribute("movieTitle",movieTitle);
		return "comment/mCommentList";
	}
	
	public String mCommentList() {
		return "comment/mCommentList";
	}
	
	@GetMapping(value="/bCommentList")
	public String bCommentList() {
		return "comment/bCommentList";
	}
}
