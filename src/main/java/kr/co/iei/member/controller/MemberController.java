package kr.co.iei.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import kr.co.iei.member.model.service.MemberSerivce;
import kr.co.iei.member.model.vo.Member;

@Controller
@RequestMapping(value="/member")
public class MemberController {
 @Autowired
 private MemberSerivce memberService;
 @GetMapping(value="/loginFrm")
 public String  loginFrm() {
	 return "member/login";
 }
 @PostMapping(value="/login")
 public String login(Member m,Model model, HttpSession session) {
	 Member member = memberService.selectOneMember(m);

	 if(member== null) {
		 model.addAttribute("title","로그인 실페");
		 model.addAttribute("text","아이디 또는 비밀번호를 확인하세요.");
		 model.addAttribute("icon","error");
		 model.addAttribute("loc","/member/loginFrm");
		 return "common/msg";
	 }else {
		 session.setAttribute("member", member);
		 return "redirect:/";
	 }
 }
 @GetMapping(value="/logout")
 public String logout(HttpSession session) {
	 session.invalidate();
	 return "redirect:/";
 }
 @GetMapping(value="/agree")
 public String agree() {
	 return "/member/agree";
 }
 @RequestMapping(value="/joinFrm")
 public String joinFrm() {
	 return "/member/joinFrm";
 }
 @PostMapping(value="/join")
 	public String join(Member m, Model model) {
	 int result = memberService.insertMember(m);
	 model.addAttribute("title","회원 가입 완료");
	 model.addAttribute("text","회원 가입을 환영합니다");
	 model.addAttribute("icon","success");
	 model.addAttribute("loc","/member/loginFrm");
	 return "common/msg";
 }
}
