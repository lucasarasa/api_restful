package br.com.example.montadora.security.services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

@Component
public class EmailServiceClon {

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
		int emailsEnviados = 0; // contador
		String destinatario = "diogoportelladantas1234@gmail.com";
		
		for(int i = 0; i<1000; i++) {
			SimpleMailMessage message = new SimpleMailMessage();
			message.setTo(destinatario);
			message.setSubject("teste api" + (i+1));
			message.setText("mensagem do email" + localDateTime.format(dateForm));
			
			try {
				javaMailSender.send(message);
				emailsEnviados++;
			} catch (Exception e) {
				return "Erro ao enviar o email" + e.getMessage();
			}
		}
		return "Emails enviados";
	}
}
