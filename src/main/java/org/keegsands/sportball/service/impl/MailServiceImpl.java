package org.keegsands.sportball.service.impl;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import org.keegsands.sportball.service.MailService;

public class MailServiceImpl implements MailService {

	private JavaMailSender mailSender;

	public void setMailSender(final JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	@Override
	public void sendMessage(String from, String[] to, String subject, String msg) {
		final SimpleMailMessage message = new SimpleMailMessage();

		message.setFrom(from);
		message.setTo(to);
		message.setSubject(subject);
		message.setText(msg);
		mailSender.send(message);

	}

}
