package kr.co.iei.etc.controller;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.iei.util.EmailSender;

@Controller
@RequestMapping(value="/etc")
public class EtcController {
 @Autowired
 private EmailSender emailSender;
 @GetMapping(value="/email")
	public String mail() {
		return "member/joinFrm";
	}
 @PostMapping(value="/sendMail")
	public String sendMail(String emailTitle, String receiver, String emailContent) {
		System.out.println("제목: "+emailTitle);
		System.out.println("받는사람: "+receiver);
		System.out.println("내용: "+emailContent);
		emailSender.sendMail(emailTitle,receiver,emailContent);
		return "redirect:/api/email";
	}
	
	
	@ResponseBody  //비동기에 해줌
	@GetMapping(value="/sendCode")
	public String sendCode(String receiver) {
		//인증메일 제목 생성
		String emailTitle = "links 인증메일입니다.";
		//인증 메일용 인증코드 생성
		Random r = new Random();
		StringBuffer sb= new StringBuffer(); //문자열 처리해줌 (가변문자열)
		for(int i=0; i<6; i++) {
			//숫자(0~9) : r.nextInt(10)
			//대문자(A~Z) : r.nextInt(26)+65
			//소문자(a~z) : r.nextInt(26)+97
			
			int flag = r.nextInt(3);  //0,1,2 >> 숫자,대,소 어떤걸 사용할지 조차 랜덤으로 결정
			
			if(flag == 0) {
				int randomCode = r.nextInt(10);
				sb.append(randomCode);
			}else if(flag == 1) {
				char randomCode = (char)(r.nextInt(26)+65);
				sb.append(randomCode);
				
			}else {
				char randomCode = (char)(r.nextInt(26)+97);
				sb.append(randomCode);
			}
		}//for
		String emailContent = "<h1>안녕하세요. links 입니다.</h1>"
								+"<h3>인증번호는 "
								+"[<span style='color:red;'>"
								+sb.toString()
								+"</span>]"
								+"입니다.</h3>";
		emailSender.sendMail(emailTitle, receiver, emailContent);
		return sb.toString();
	}
}
