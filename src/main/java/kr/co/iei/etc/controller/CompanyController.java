package kr.co.iei.etc.controller;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.iei.member.model.service.MemberSerivce;

@Controller
@RequestMapping(value = "/compnay")
public class CompanyController {
 @Autowired
 private MemberSerivce memberService;
 @GetMapping(value = "/check")
 public List check() {
	 List list=new ArrayList<Check>();
	 String url ="https://bizno.net/api/fapi";
	 String  serviceKey="dGtkYWxzOTkwOUBuYXZlci5jb20g";
	
	String result =Jsoup.conn ect(url)
	 return null ;
 }
}
