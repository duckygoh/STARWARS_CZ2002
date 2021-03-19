package control;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * This Control Class is responsible of handling the Email Notification to students in the system
 * @author Raymond Goh
 * @version 1.2
 * @since 2020/11/08
 */
public class MailManager {
	/**
	 * Method to send email to clients
	 * @param recepient This is the student email address
	 * @param name This is the student name
	 * @param index This is the index ID
	 * @param course This is the Course ID
	 * @throws Exception To inform if email fails to send
	 */
	public static void sendMail(String recepient, String name, String index, String course) throws Exception {

		System.out.println("Sending email...");
		Properties properties = new Properties();

		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smto.port", "587");

		String myAccountEmail = "cz2002gatsby@gmail.com";
		String password = "0v9Q!9bVSZRG";

		Session session = Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(myAccountEmail, password);
			}
		});
		Message message = prepareMessage(session, myAccountEmail, recepient, name, index, course);

		Transport.send(message);
		System.out.println("Message sent successfully!");
	}
	/**
	 * Method to act as one of the email template
	 * @param session This is providing a context of the interaction with the mail host includes but not limited to debugging output from the mail host, timeouts, and authentication mechanisms.
	 * @param name This is the sender email address
	 * @param index This is the recepient email address
	 * @param course This is the name of the recepient
	 * @param index This is the index ID of the course
	 * @param index This is the course ID
	 */
	private static Message prepareMessage(Session session, String myAccountEmail, String recepient, String name, String index, String course) {

		/***
		 * This function crafts the email message to be sent to a student's email address
		 * @param session
		 * @param myAccountEmail This is email of the sender
		 * @param recepient This is the student's email
		 * @return Returns an email message to be sent
		 */
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(myAccountEmail));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));

			message.setSubject("You have been enroll into the course.");
			message.setText("Dear " + name + ", you have been added into " + index + " of " + course + ".");

			return message;
		} catch(Exception ex) {
			System.out.println("Failed");
		}
		return null;
	}
}