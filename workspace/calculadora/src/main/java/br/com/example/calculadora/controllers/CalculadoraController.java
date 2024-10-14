package br.com.example.calculadora.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/calculadora")
public class CalculadoraController {
	
	@GetMapping("/somar")
	public double somar(double a, double b) {
		return a + b;
	}
	
	@GetMapping("/subtrair")
	public double subtrair(double a, double b) {
		return a - b;
	}
	
	@GetMapping("/multiplicar")
	public double multiplicar(double a,double b) {
		return a * b;
	}
	
	@GetMapping("/dividir")
	public double dividir(double a, double b) {
//		if(b == 0) {
//			throw new IllegalArgumentException("Divisor não pode ser zero.");
//		}
		return a / b;
	}
}
