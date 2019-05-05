package org.keegsands.sportball.service;

public interface MailService {

	void sendMessage(String from, String[] to, String subject, String message);
}
