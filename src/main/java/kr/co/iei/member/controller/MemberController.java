package kr.co.iei.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.iei.member.model.service.MemberSerivce;
import kr.co.iei.member.vo.Member;

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
 public String login(Member m) {
	 Member member = memberService.selectOneMember(m);
	 System.out.println(member);
	 return "redirect:/";
 }
}
