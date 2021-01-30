package com.minmall.service;

import com.minmall.DTO.EmailDTO;

public interface EmailService {

	public void sendMail(EmailDTO dto, String authcode);
}
