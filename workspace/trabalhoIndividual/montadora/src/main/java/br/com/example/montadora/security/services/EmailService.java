package br.com.example.montadora.security.services;

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

import br.com.example.montadora.security.dto.SignupRequestDTO;
import br.com.example.montadora.security.dto.UserRequestDTO;
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
		message.setTo("emailcomprador@gmail.com");
		message.setSubject("Compra Automóvel");
		message.setText("Parabéns! Compra realizada com sucesso." + localDateTime.format(dateForm));
		
		try {
			javaMailSender.send(message);
			return "Email enviado com sucesso";
		} catch (Exception e) {
			return "Erro ao enviar o email" + e.getMessage();
		}
	}
	
	public String writerTeste2(SignupRequestDTO signUpRequest) {
		LocalDateTime localDateTime = LocalDateTime.now();
		DateTimeFormatter dateForm = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		MimeMessage message = javaMailSender.createMimeMessage();
		
		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setSubject("Cadastro efetuado com sucesso!");
			helper.setTo(signUpRequest.getEmail());
			
			String emailText = "<h1>Bem-vindo(a), " + signUpRequest.getUsername() + "!</h1>"
	                 + "<p>É um grande prazer tê-lo(a) como cliente em nossa concessionária.</p>"
	                 + "<p>Estamos prontos para oferecer o melhor atendimento e garantir que você tenha uma excelente experiência conosco.</p>"
	                 + "<p>Se precisar de qualquer suporte, nossa equipe estará sempre à disposição para ajudar.</p>"
	                 + "<p>Este e-mail foi enviado em: " + localDateTime.format(dateForm) + "</p>"
	                 + "<br>"
	                 + "<p>Atenciosamente,</p>"
	                 + "<p><strong>Equipe Montadora Automóveis</strong></p>"
	                 + "<p><i>Aguardamos sua visita em breve!</i></p>";
			
			helper.setText(emailText, true);
			javaMailSender.send(message);
			return "Email enviado com sucesso";
		} catch (MessagingException e) {
			return "Erro ao enviar o email" + e.getMessage();
		}
	}
	
	public void mailSend() {
		LocalDateTime localDateTime = LocalDateTime.now();
		DateTimeFormatter dateForm = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//		DecimalFormat dec = new DecimalFormat("R$ #,##0.00");
		MimeMessage message = javaMailSender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setSubject("Parabéns pela sua nova conquista!");
			helper.setTo("emailcomprador@gmail.com");
			
			StringBuilder sBuilder = new StringBuilder();
			sBuilder.append("<html>\r\n");
			sBuilder.append("	<body>\r\n");
			sBuilder.append("		<div>"+ localDateTime.format(dateForm) +"</div>");
			sBuilder.append("		<div align=\"center\">\r\n");
			sBuilder.append("			<h3>Olá, [Cliente]!</h3>\r\n");
			sBuilder.append("			<p>Estamos muito felizes em saber que você escolheu o seu novo carro conosco!</p>\r\n");
			sBuilder.append("			<p>Agradecemos pela confiança em nossa equipe e estamos aqui para garantir que sua experiência <br> "
					+ "continue sendo excepcional. Se precisar de qualquer assistência, seja sobre o funcionamento do veículo ou serviços <br>"
					+ " adicionais, não hesite em nos contatar.</p>\r\n");
			sBuilder.append("			<p>Desejamos muitas aventuras e momentos incríveis ao volante do seu novo carro!</p>\r\n");
			sBuilder.append("			<p>Atenciosamente,</p>\r\n");
			sBuilder.append("		<br>\r\n");
			sBuilder.append("			<p>Montadora Automóveis</p>\r\n");
			sBuilder.append("		</div>\r\n");
			sBuilder.append("		<br>\r\n");
			sBuilder.append("	</body>\r\n");
			sBuilder.append("</html>");
			
			helper.setText(sBuilder.toString(), true);
			javaMailSender.send(message);
		} catch (MessagingException e) {
			System.out.println("Erro ao enviar email" + e.getMessage());
		}
	}
	
}
