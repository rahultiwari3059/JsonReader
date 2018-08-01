package com.emiza.util;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.ini4j.Ini;
import org.ini4j.InvalidFileFormatException;

import com.emiza.constant.Constant;

public class GoogleMail {

	Utility util = new Utility();
	Logger logger = Logger.getLogger(this.getClass());

	public String sendMail(String to, String body, String subject) throws InvalidFileFormatException, IOException {
		Ini ini = util.getIni();
		
		String from = ini.fetch("email", "from_id");
		String pass = ini.fetch("email", "from_password");
	//	String body = ini.fetch("email", "body_message");
		//String subject = ini.fetch("email", "subject");
	//	body = body.replace(Constant.REPLACE_VERIFICATION_CODE, varificationCode);
		
		Properties props = System.getProperties();
		String host = ini.fetch("email", "host");
		// "smtp.gmail.com";
		props.put(ini.fetch(Constant.EMAIL, Constant.TTLS), "true");
		props.put(ini.fetch(Constant.EMAIL, Constant.SMTPHOST), ini.fetch(Constant.EMAIL, Constant.HOST));
		props.put(ini.fetch(Constant.EMAIL, Constant.SMTPPORT), ini.fetch(Constant.EMAIL, Constant.PORT));
		props.put(ini.fetch(Constant.EMAIL, Constant.AUTH), "true");
		// Get the default Session object.
		Session session = Session.getDefaultInstance(props, new MailAuthenticator(from, pass));
		MimeMessage msg = new MimeMessage(session);
		try {
			// Set From: header field of the header.
			msg.setFrom(new InternetAddress(from));
			// Set To: header field of the header.
			// To get the array of addresses
			InternetAddress toAddress = new InternetAddress(to);
			msg.addRecipient(Message.RecipientType.TO, toAddress);
			// Set Subject: header field
			msg.setSubject(subject);
			// Send the actual HTML message, as big as you like
			msg.setContent(body, "text/html");
			Transport transport = session.getTransport("smtp");
			transport.connect(host, from, pass);
			transport.sendMessage(msg, msg.getAllRecipients());
			transport.close();
			return Constant.SUCCESS_STATUS;
		} catch (MessagingException mex) {
			// mex.printStackTrace();
			System.out.println(mex.getMessage());
			logger.error(mex.getMessage());
		}
		return Constant.FAILURE_STATUS;
	}

	
}
