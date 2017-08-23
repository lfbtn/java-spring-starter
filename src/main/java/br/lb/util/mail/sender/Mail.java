package br.lb.util.mail.sender;

import java.io.File;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import br.lb.admin.entity.AppConfiguration;

public class Mail {
    
    	AppConfiguration appConfiguration = new AppConfiguration();

	private final String username = appConfiguration.getAppemail();
	/* private final String username = "email@realjuridica.com.br"; */

	private final String password = appConfiguration.getAppmailpass();
	/* private final String password = ""; */

	private String from = appConfiguration.getAppemail();

	/* private String host = "mail.realjuridica.com.br"; */

	private String host = appConfiguration.getAppmailhostserver();

	private String port = appConfiguration.getAppmailhostport();

	private String to;

	public void sendMailPasswordRecovery(String to, String randomToken) {

		Properties props = getProperties(this.getHost(), this.getHost());

		Session session = getSession(props, this.getUsername(), this.getPassword());

		String subject = "Recuperação de senha.";
		String bodyMessage = "Esta é uma mensagem automática. Não responda a este e-mail. "
				+ "\n\nCaso não solicitou redefinição de senha, favor desconsiderar esta mensagem." + "\n\n" + "Token: "
				+ randomToken + "\n\n" + "Insira o token na página em que solicitou a recuperação de senha." + "\n\n\n"
				+ appConfiguration.getAppName() + "\u00a9";

		try {
			// Create a default MimeMessage object.
			Message message = new MimeMessage(session);
			message = constructMessageHeader(message, from, to, subject);
			try {
				message = construckBodyMessagePasswordRecovery(message, bodyMessage);
			} catch (Exception e) {
				System.out.println(e);
			}
			// Send message
			Transport.send(message);
			System.out.println("SENT");
		} catch (MessagingException e) {
			System.out.println(e);
		}
	}

	public void sendMailwithAttachment(String to, File file) {
		Properties props = getProperties(this.getHost(), this.getHost());

		Session session = getSession(props, this.getUsername(), this.getPassword());

		String subject = "Real Jurídica Holerite";
		String bodyMessage = "Esta é uma mensagem automática. Não responda a este e-mail. "
				+ "\n\nArquivo do holerite em anexo no formato PDF." + "\n\nUtilize a sua senha do portal JSI para abrir o arquivo."
						+ "\n\n" + appConfiguration.getAppName() + "\u00a9";
		try {
			// Create a default MimeMessage object.
			Message message = new MimeMessage(session);
			message = constructMessageHeader(message, from, to, subject);
			try {
				message = construckBodyMessageMailwithAttachment(message, bodyMessage, file);
			} catch (Exception e) {
				System.out.println(e);
			}
			// Send message
			Transport.send(message);
			System.out.println("SENT");
		} catch (MessagingException e) {
			System.out.println(e);
		}
	}

	private Message constructMessageHeader(Message message, final String from, final String to, String subject) {
		try {
			// Set From: header field of the header.
			message.setFrom(new InternetAddress(from));
		} catch (Exception e) {
			System.out.println(e);
		}
		try {
			// Set To: header field of the header.
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
		} catch (Exception e) {
			System.out.println(e);
		}
		try {
			// Set Subject: header field
			message.setSubject(subject);
		} catch (Exception e) {
			System.out.println(e);
		}
		return message;
	}

	private Message construckBodyMessagePasswordRecovery(Message message, String bodyMessage) {
		// Create the message part
		BodyPart messageBodyPart = new MimeBodyPart();

		// Now set the actual message
		try {
			messageBodyPart.setText(bodyMessage);
		} catch (Exception e) {
			System.out.println(e);
		}

		// Create a multipar message
		Multipart multipart = new MimeMultipart();

		// Set text message part
		try {
			multipart.addBodyPart(messageBodyPart);
		} catch (Exception e) {
			System.out.println(e);
		}
		try {
			message.setContent(multipart);
		} catch (Exception e) {
			System.out.println(e);
		}
		// Send the complete message parts
		return message;
	}

	private Message construckBodyMessageMailwithAttachment(Message message, String bodyMessage, File file) {
		// Create the message part
		BodyPart messageBodyPart = new MimeBodyPart();
		BodyPart messageBodyPart2 = new MimeBodyPart();
		// Now set the actual message
		try {
			messageBodyPart2.setText(bodyMessage);
		} catch (Exception e) {
			System.out.println(e);
		}

		// Create a multipart message
		Multipart multipart = new MimeMultipart();

		// Set text message part
		try {
			multipart.addBodyPart(messageBodyPart);
			multipart.addBodyPart(messageBodyPart2);
		} catch (Exception e) {
			System.out.println(e);
		}
		DataSource source = new FileDataSource(file);

		try {
			messageBodyPart.setDataHandler(new DataHandler(source));
			messageBodyPart.setFileName("holerite.pdf");
		} catch (MessagingException e1) {
			System.out.println(e1);
		}
		try {
			message.setContent(multipart);
		} catch (Exception e) {
			System.out.println(e);
		}
		return message;
	}

	/*
	 * public BodyPart addFileAttachment(BodyPart messageBodyPart, String
	 * filename) { DataSource source = new FileDataSource(filename); try {
	 * messageBodyPart.setDataHandler(new DataHandler(source));
	 * messageBodyPart.setFileName(filename); } catch (Exception e) {
	 * System.out.println(e); } return messageBodyPart; }
	 */
	private Properties getProperties(String host, String port) {
		Properties props = new Properties();
		props.setProperty("mail.smtp.ssl.trust", appConfiguration.getAppmailhostport());
		/*
		 * props.setProperty("mail.smtp.ssl.trust", "mail.realjuridica.com.br");
		 */
		props.setProperty("mail.smtp.auth", "true");
		props.setProperty("mail.smtp.starttls.enable", "true");
		props.setProperty("mail.smtp.host", host);
		props.setProperty("mail.smtp.port", appConfiguration.getAppmailhostport());
		return props;
	};

	private Session getSession(Properties props, final String username, final String password) {
		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});
		return session;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

}
