package br.com.example.montadora.security.controllers;

import java.util.List;

//import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.example.montadora.security.dto.CarroResponseDTO;
import br.com.example.montadora.security.entities.Carro;
import br.com.example.montadora.security.services.CarroServices;
//import br.com.example.montadora.security.services.EmailService;
import br.com.example.montadora.utils.Util;

@RestController
@RequestMapping("/carro")
public class CarroController {
	
	@Autowired
	Util util;
	
	@Autowired
	CarroServices carroServices;
	
//	@Autowired
//	EmailService emailService;
	
	@PutMapping
	public List<Carro> listarCarros() {
		return carroServices.listarCarros();
	}
	
	@PostMapping("/adicionarCarro")
	public CarroResponseDTO adicionarCarro(@RequestBody CarroResponseDTO carroResponseDTO) {
		return carroServices.adicionarCarro(carroResponseDTO);
	}
	
	@GetMapping("/{id}")
	public CarroResponseDTO buscarPorId(@PathVariable Integer id) {
		return carroServices.buscarPorId(id);
	}
	
	@DeleteMapping("/{id}")
	public void deletarCarro(@PathVariable Integer id) {
		carroServices.deletarPorId(id);
	}
	
//	@GetMapping
//	public String olaMundo() {
//		emailService.writerTeste();
//		return "Email enviado com sucesso!";
//	}
//	
//	@GetMapping("/emaildois")
//	public String olaMundo2() {
//		emailService.writerTeste2();
//		return "Email enviado com sucesso!";
//	}
//	
//	@GetMapping("/emailtres")
//	public String olaMundo3() {
//		emailService.mailSend();
//		return "Email enviado com sucesso!";
//	}
}
