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
import kr.co.iei.comment.model.vo.ReComment;
import kr.co.iei.contents.model.vo.DbMovie;
import kr.co.iei.member.model.vo.Member;



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
	
	
	
	@GetMapping(value="/mCommentList")
	public String mCommentList(String contentNo, Model model) {
		DbMovie m = new DbMovie();
		m = commentService.selectMovieInfo(contentNo);
		List list = commentService.mCommentList(contentNo );
		String movieTitle = commentService.selectMovieTitle(contentNo);
				
		model.addAttribute("list", list);
		model.addAttribute("movieTitle", movieTitle);
		model.addAttribute("m", m);
						
		return "comment/mCommentList";
	}
		
		
	
	// 푸터에 코멘트 개수 전달
    @ResponseBody
    @GetMapping("/count")
    public int getCommentCount() {
    	return commentService.getTotalCommentCount();
    }
	@GetMapping(value="/myCommentList")
	public String myCommentList(String contentNo, Model model) {
		List list = commentService.mCommentList(contentNo );		
		model.addAttribute("list", list);		
		return "comment/myCommentList";
	}
	
	
	@GetMapping(value="/commDelete")
	public String commDelete(int commentNo, Model model) {		
		int result = commentService.deleteComm(commentNo);
		
		model.addAttribute("title", "댓글 삭제 완료");
		model.addAttribute("text", "삭제되었습니다.");
		model.addAttribute("icon", "success");
		model.addAttribute("loc", "/comment/mCommentMemberList?reqPage=1");
		return "common/msg";
	}
	
	@GetMapping(value="/updateFrm")
	public String updateFrm(int commentNo,Model model) {
		
		Comment c = commentService.selectOneComm(commentNo);
		model.addAttribute("c", c);
		
		return "comment/updateFrm";
	}
	
	@PostMapping(value="/update")
	public String updateComm(Comment c) {
		int result = commentService.updateComm(c);
		return "redirect:/comment/mCommentMemberList";
	}
	
	@PostMapping(value="/reComm")
	public String reCommInsert(ReComment rc, String contentNo) {
		//commentNo 이걸로 contentNo 조회 할수 있어요?
		System.out.println(rc);
		Member member = new Member();
		int result = commentService.reCommInsert(rc);
		return "redirect:/comment/mCommentList?contentNo="+contentNo;
	}
	
	/*
	@GetMapping(value="/commReport")
	public String commReport() {
		int report = commentService.commReport();
		
		return "redirect:/comment/mCommentList?reqpage=1";
	}
	*/
}
