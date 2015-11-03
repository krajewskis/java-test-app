package com.sebek.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.sebek.utils.PathsProperties;

public class MailService {

	public static void Send(String recipient, String subject, String message)
			throws IOException, AddressException, MessagingException {

		final String user;
		final String password;

		FileInputStream inputStream = new FileInputStream(
				PathsProperties.MAIL_PROPERTIES);
		Properties props = new Properties();
		props.load(inputStream);

		user = props.getProperty("mail.smtp.user");
		password = props.getProperty("mail.smtp.password");

		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(user, password);
					}
				});

		Message mimeMessage = new MimeMessage(session);
		mimeMessage.setFrom(new InternetAddress(user));
		mimeMessage.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(recipient));
		mimeMessage.setSubject(subject);
		mimeMessage.setText(message);

		Transport.send(mimeMessage);

	}

	public static void main(String[] args) throws IOException,
			AddressException, MessagingException {
		Send("sebastian.krajewski@jcommerce.pl", "Testing Subject",
				"Dear Mail Crawler,\n\n No spam to my email, please!");
	}
}