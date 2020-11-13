package br.com.salon.carine.lima.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import br.com.salon.carine.lima.dto.EmailDTO;

@Service
public class EmailService {
	
	@Autowired
	private JavaMailSender sender;
	
	public void enviarEmail(EmailDTO email) {
		
		SimpleMailMessage emailSender = new SimpleMailMessage();
		emailSender.setSubject(email.getAssunto());
		emailSender.setFrom(email.getRemetente());
		emailSender.setTo(email.getDestinatario());
		emailSender.setText(email.getMensagem());
		
		sender.send(emailSender);
		
	}
}
