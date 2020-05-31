package com.gabriel.projetospring.service;

import org.springframework.mail.SimpleMailMessage;

import com.gabriel.projetospring.domain.Pedido;

public interface EmailService {
	
	void sendOrderConfirmationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);

}
