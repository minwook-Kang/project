package com.minmall.service;

import javax.inject.Inject;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.minmall.DTO.EmailDTO;

@Service
public class EmailServiceImpl implements EmailService {

	@Inject
	JavaMailSender mailSender;	
	
	//이메일 인증코드 전송
	@Override
	public void sendMail(EmailDTO dto, String authcode) {
		
		MimeMessage msg = mailSender.createMimeMessage();
		
		try {
			//받는 사람 설정
			msg.addRecipient(RecipientType.TO, new InternetAddress(dto.getReceiveMail()));
			//보내는 사람 설정
			msg.addFrom(new InternetAddress[] { new InternetAddress(dto.getSenderMail(), dto.getSenderName())});
			//이메일 제목
			msg.setSubject(dto.getSubject(), "utf-8");
			//이메일 본문(인증코드 추가)
			msg.setText(dto.getMessage() + authcode , "utf-8");
			
			//메일 보내기
			mailSender.send(msg);
			}catch(Exception e) {
				e.printStackTrace();
		}
		
		
	}

}
