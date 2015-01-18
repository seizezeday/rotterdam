package com.rotterdam.tools;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class EmailSender {

    public static final String COMPANY_MAIL_ADDRESS = "noreply.drivertools@gmail.com";
    public static final String COMPANY_MAIL_PASSWORD = "rotterdam2014";

    public static boolean sendForgotPassword(String name, String toAddress, String password) {
		try {
			String host = "smtp.gmail.com";
			String from = COMPANY_MAIL_ADDRESS;
			String pass = COMPANY_MAIL_PASSWORD;
			Properties props = System.getProperties();
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.host", host);
			props.put("mail.smtp.port", "587");
			props.put("mail.smtp.auth", "true");
			props.put("mail.debug", "true");
			SmtpAuthenticator authentication = new SmtpAuthenticator(from, pass);
			Session session = Session.getDefaultInstance(props, authentication);
			MimeMessage message = new MimeMessage(session);
			Address fromAddress = new InternetAddress(from);
			Address to = new InternetAddress(toAddress);
			message.setFrom(fromAddress);
			message.setRecipient(Message.RecipientType.TO, to);
			message.setSubject("Forget Password");
			message.setText("Hi " + name + ", " +"\n" + "Your password is "
					+ password + "\n\n" + "Best regards," + "\n" + "Comes Solution");
			Transport transport = session.getTransport("smtp");
			transport.connect(host, from, pass);
			message.saveChanges();
			Transport.send(message);
			transport.close();
			return true;
		} catch (Exception ex) {
			System.out.println("<html><head></head><body>");
			System.out.println("ERROR: " + ex);
			System.out.println("</body></html>");
			return false;
		}
	}

}

class SmtpAuthenticator extends Authenticator {

	String username;
	String password;

	public SmtpAuthenticator(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	@Override
	public PasswordAuthentication getPasswordAuthentication() {

		if ((username != null) && (username.length() > 0) && (password != null)
				&& (password.length() > 0)) {

			return new PasswordAuthentication(username, password);
		}

		return null;
	}
}
