package br.com.example.montadora.utils;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import br.com.example.montadora.security.dto.EnderecoResponseDTO;

@Component
public class Util {
	
	public EnderecoResponseDTO	buscarEndereco(String cep) {
		RestTemplate template = new RestTemplate();
		return template.getForObject("https://viacep.com.br/ws/{cep}/json", EnderecoResponseDTO.class, cep);
	}
}
