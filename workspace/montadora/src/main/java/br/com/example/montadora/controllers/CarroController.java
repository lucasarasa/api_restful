package br.com.example.montadora.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/carro")
public class CarroController {
	
	@GetMapping("/ola")
	public String olaMundo() {
		return "Hello, world!";
	}
}
