package kr.co.iei.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
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
		 model.addAttribute("title","로그인 실패");
		 model.addAttribute("text","아이디 또는 비밀번호를 확인하세요.");
		 model.addAttribute("icon","error");
		 model.addAttribute("loc","/member/loginFrm");
		 return "common/msg";
	 }else if(member.getDel()=="Y"){
		 model.addAttribute("title","로그인 실페");
		 model.addAttribute("text","이미 탈퇴한 회원입니다.");
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
	 return "member/joinFrm";
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
 @GetMapping(value="/checkId")
 public String checkId(String checkId, Model model) {
	 Member member =memberService.selectOneMember(checkId);
	 if(member ==null) {
		 model.addAttribute("result",0);
	 }else {
		 model.addAttribute("result",1);
	 }
	 model.addAttribute("memberId",checkId);
	 return "member/checkId";
 }
 @GetMapping(value="/mypage")
 public String mypage() {
	 return "member/mypage";
 }
 @GetMapping(value="/changeinfo")
 public String changeinfo() {
	 return "member/changeinfo";
 }
 @PostMapping(value="/update")
 public String update(Member m, @SessionAttribute Member member) {
	 int memberNo = member.getMemberNo();
	 m.setMemberNo(memberNo);
	 int result =memberService.updateMember(m);

	 if(result >0) {
		 member.setMemberPw(m.getMemberPw());
		 member.setMemberPhone(m.getMemberPhone());
		 member.setMemberEmail(m.getMemberEmail());
		 return "redirect:/member/mypage";
	 }else {
		 return "redirect:/";
	 }
 }
 @GetMapping(value="/delete")
 
 public String delete(@SessionAttribute Member member, Model model) {
	 int memberNo = member.getMemberNo();
	 int result =memberService.deleteMember(memberNo);
	 int result2 =memberService.insertdeleteMember(memberNo);
	 model.addAttribute("title","회원 탈퇴 완료");
	 model.addAttribute("text","수고하셨습니다");
	 model.addAttribute("icon","success");
	 model.addAttribute("loc","/member/logout");
	 return "common/msg";
 }
 @ResponseBody
 @GetMapping(value="/ajaxCheckId")
 public boolean ajaxCheckId(String memberId) {
	 Member m= memberService.selectOneMember(memberId);
	 return m== null;
 }
 @GetMapping(value="/find")
 public String find() {
	 return "member/find";
 }
 @GetMapping(value="/idFindFrm")
 public String ifFindFrm() {
	 return "member/idfindFrm";
 }
 @GetMapping(value="/idFind")
 public String idFind() {
	 return "common/msg";
 }
 @GetMapping(value="/pwFindFrm")
 public String pwFindFrm() {
	 return "member/pwfindFrm";
 }
 @GetMapping(value="/pwFind")
 public String pwFind() {
	 return "member/pwChange";
 }
}
