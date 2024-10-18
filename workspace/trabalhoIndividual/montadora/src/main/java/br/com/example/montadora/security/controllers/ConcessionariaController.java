package br.com.example.montadora.security.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.example.montadora.security.dto.EnderecoRequestDTO;
import br.com.example.montadora.security.dto.EnderecoResponseDTO;
import br.com.example.montadora.security.services.EnderecoService;

@RestController
@RequestMapping("/concessionaria")
public class ConcessionariaController {
	
	@Autowired
	EnderecoService enderecoService;
	
//	@PostMapping("/teste-cep")
//	public EnderecoResponseDTO testeEndereco(@RequestParam	String cep, @RequestParam String complemento, @RequestParam int numero) {
//		return enderecoService.consultarEndereco(cep, complemento, numero);
//	}
	
	@PostMapping("/teste-cep")
	public EnderecoResponseDTO testeEndereco(@RequestBody EnderecoRequestDTO enderecoRequestDTO) {
		return enderecoService.consultarEndereco(enderecoRequestDTO);
	}
}
