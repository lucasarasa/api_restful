package br.com.example.montadora.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.example.montadora.services.EmailService;

@RestController
@RequestMapping("/carro")
public class CarroController {
	
	@Autowired
	EmailService emailService;
	
	@GetMapping
	public String olaMundo() {
		emailService.writerTeste();
		return "Email enviado com sucesso!";
	}
	
	@GetMapping("/emaildois")
	public String olaMundo2() {
		emailService.writerTeste2();
		return "Email enviado com sucesso!";
	}
	
	@GetMapping("/emailtres")
	public String olaMundo3() {
		emailService.mailSend();
		return "Email enviado com sucesso!";
	}
}
