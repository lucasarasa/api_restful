package br.com.example.montadora.services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Component
public class EmailService {

	@Autowired
	public JavaMailSender javaMailSender;
	
	@Value("${spring.mail.username}")
	private String emailRemetente;
	
	@Value("${spring.mail.host}")
	private String emailServerHost;
	
	@Value("${spring.mail.port}")
	private Integer emailServerPort;
	
	@Value("${spring.mail.password}")
	private String emailPassword;
	
	public JavaMailSender javaMailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setUsername(emailRemetente);
		mailSender.setHost(emailServerHost);
		mailSender.setPort(emailServerPort);
		mailSender.setPassword(emailPassword);
		
		Properties props = mailSender.getJavaMailProperties();
		props.put("mail.smtp.auth","true");
		props.put("mail.smtp.starttls.enable","true");
		
		return mailSender;
	}
	
	public String writerTeste() {
		LocalDateTime localDateTime = LocalDateTime.now();
		DateTimeFormatter dateForm = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo("diogoportelladantas1234@gmail.com");
		message.setSubject("teste api");
		message.setText("mensagem do email" + localDateTime.format(dateForm));
		
		try {
			javaMailSender.send(message);
			return "Email enviado com sucesso";
		} catch (Exception e) {
			return "Erro ao enviar o email" + e.getMessage();
		}
	}
	
	public String writerTeste2() {
		LocalDateTime localDateTime = LocalDateTime.now();
		DateTimeFormatter dateForm = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		MimeMessage message = javaMailSender.createMimeMessage();
		
		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setSubject("Assunto do e-mail");
			helper.setTo("diogoportelladantas1234@gmail.com");
			
			String emailText = "<h1>Olá</h1>"
								+"<p>Diogo é um pato</p>"
								+ "<p>Email enviado dia: " + localDateTime.format(dateForm) + "</p>"
										+ "<br>";
			helper.setText(emailText, true);
			javaMailSender.send(message);
			return "Email enviado com sucesso";
		} catch (MessagingException e) {
			return "Erro ao enviar o email" + e.getMessage();
		}
	}
}
