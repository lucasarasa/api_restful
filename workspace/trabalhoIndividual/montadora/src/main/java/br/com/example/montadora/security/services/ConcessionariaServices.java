package br.com.example.montadora.security.services;

import java.util.List;
import java.util.stream.Collectors;

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
	
	public List<ConcessionariaRequestDTO> listarConce(){
		List<Concessionaria> concessionarias = concessionariaRepository.findAll();
		return concessionarias.stream().map(concessionaria -> new ConcessionariaRequestDTO(concessionaria.getNome(),
				concessionaria.getTelefone(),
				concessionaria.getEmail())).collect(Collectors.toList());
	}
	
	public void cadastrarConcessionaria(ConcessionariaRequestDTO concessionariaRequestDTO) {
		Concessionaria newConce = new Concessionaria();
		newConce.setNome(concessionariaRequestDTO.getNome());
		newConce.setTelefone(concessionariaRequestDTO.getTelefone());
		newConce.setEmail(concessionariaRequestDTO.getEmail());
		
//		Endereco endereco = enderecoRepository.buscarEndereco(concessionariaRequestDTO.getNomeEndereco());
		Endereco endereco = enderecoRepository.buscarEnderecoUnico();

		newConce.setFkEndereco(endereco);
		
		concessionariaRepository.save(newConce);
	}
	
	public void atualizarConcessionaria(ConcessionariaRequestDTO concessionariaAtualizada) {
	Concessionaria concessionaria = concessionariaRepository.buscarConceUnica();
	if(concessionariaAtualizada.getNome()!=null) {
		concessionaria.setNome(concessionariaAtualizada.getNome());		
	}
	if(concessionariaAtualizada.getEmail()!=null) {
		concessionaria.setEmail(concessionariaAtualizada.getEmail());		
	}
	if(concessionariaAtualizada.getTelefone()!=null) {
		concessionaria.setTelefone(concessionariaAtualizada.getTelefone());
	}

	concessionariaRepository.save(concessionaria);

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
