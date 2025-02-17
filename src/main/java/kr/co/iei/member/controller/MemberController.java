package kr.co.iei.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.iei.member.model.service.MemberSerivce;

@Controller
@RequestMapping(value="/member")
public class MemberController {
 @Autowired
 private MemberSerivce memberservice;
 @GetMapping(value="/loginFrm")
 public String  loginFrm() {
	 return "member.login";
 }
}
