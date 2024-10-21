package br.com.example.montadora.security.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.example.montadora.security.dto.ConcessionariaRequestDTO;
import br.com.example.montadora.security.entities.Concessionaria;
import br.com.example.montadora.security.entities.Endereco;
import br.com.example.montadora.security.repositories.ConcessionariaRepository;
import br.com.example.montadora.security.repositories.EnderecoRepository;

@Service
public class ConcessionariaServices {
	
	@Autowired
	private ConcessionariaRepository concessionariaRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	public List<Concessionaria> listarConce(){
		return concessionariaRepository.findAll();
	}
	
	public void cadastrarConcessionaria(ConcessionariaRequestDTO concessionariaRequestDTO) {
		Concessionaria newConce = new Concessionaria();
		newConce.setNome(concessionariaRequestDTO.getNome());
		newConce.setTelefone(concessionariaRequestDTO.getTelefone());
		newConce.setEmail(concessionariaRequestDTO.getEmail());
		
		Endereco endereco = enderecoRepository.buscarEndereco(concessionariaRequestDTO.getNomeEndereco());
		newConce.setFkEndereco(endereco);
		
		concessionariaRepository.save(newConce);
	}
	
	public Concessionaria atualizarConcessionaria(Integer id, Concessionaria concessionaria) {
		Concessionaria conceExistente = concessionariaRepository.findById(id).orElse(null);
		
		if(conceExistente != null) {
			conceExistente.setNome(concessionaria.getNome());
			conceExistente.setTelefone(concessionaria.getTelefone());
			
			return concessionariaRepository.save(conceExistente);
		} else {
			return null;
		}
	}
	
	public boolean concessionariaDelete(Integer id) {
		if(concessionariaRepository.existsById(id)) {
			concessionariaRepository.deleteById(id);
			return true;
		}else {
			return false;
		}
	}
}
