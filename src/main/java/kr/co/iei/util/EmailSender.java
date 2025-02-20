package kr.co.iei.util;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;


@Component
public class EmailSender {
	@Autowired
private JavaMailSender sender;

	public void sendMail(String emailTitle, String receiver, String emailContent) {
		MimeMessage message = sender.createMimeMessage();
		//MimeMessage 메일 작성해서 보내줌
		MimeMessageHelper helper = new MimeMessageHelper(message);
		try {
			//메일 전송시간
			helper.setSentDate(new Date());
			//보내는사람
			helper.setFrom(new InternetAddress("2d2v2l22","links"));
			//받는사람
			helper.setTo(receiver);
			//제목
			helper.setSubject(emailContent);
			//내용
			helper.setText(emailContent, true);  //true :html로 인식 시키기
			//이메일 전송
			sender.send(message);
			
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	}
	

