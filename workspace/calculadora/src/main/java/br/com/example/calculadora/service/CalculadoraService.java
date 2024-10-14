package br.com.example.calculadora.service;

import org.springframework.stereotype.Service;

@Service
public class CalculadoraService {
	
	public double somar(double a, double b) {
		return a + b;
	}
	
	public double subtrair(double a, double b) {
		return a - b;
	}
	
	public double multiplicar(double a, double b) {
		return a * b;
	}
	
	public double dividir(double a, double b) {
		if(b == 0) {
			throw new IllegalArgumentException("Divisor não pode ser zero.");
		} else {
		return a / b;
		}
	}
	
}
