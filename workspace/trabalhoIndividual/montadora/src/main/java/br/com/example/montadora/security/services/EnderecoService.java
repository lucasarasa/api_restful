package br.com.example.montadora.security.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.example.montadora.security.dto.EnderecoRequestDTO;
import br.com.example.montadora.security.dto.EnderecoResponseDTO;
import br.com.example.montadora.security.entities.Endereco;
import br.com.example.montadora.security.repositories.EnderecoRepository;
import br.com.example.montadora.utils.Util;

@Service
public class EnderecoService {
	
	@Autowired
	Util util;

	@Autowired
	EnderecoRepository enderecoRepository;
	
//	public EnderecoResponseDTO consultarEndereco(String cep, String complemento, int numero) {
//		EnderecoResponseDTO viaCep = util.buscarEndereco(cep);
//		
//		EnderecoResponseDTO endereco = new EnderecoResponseDTO();
//		endereco.setCep(viaCep.getCep());
//		endereco.setBairro(viaCep.getBairro());
//		endereco.setComplemento(complemento);
//		endereco.setNumero(numero);
//		endereco.setDdd(viaCep.getDdd());
//		endereco.setEstado(viaCep.getEstado());
//		endereco.setGia(viaCep.getGia());
//		endereco.setIbge(viaCep.getIbge());
//		endereco.setLocalidade(viaCep.getLocalidade());
//		endereco.setLogradouro(viaCep.getLogradouro());
//		endereco.setRegiao(viaCep.getRegiao());
//		endereco.setSiafi(viaCep.getSiafi());
//		endereco.setUf(viaCep.getUf());
//		endereco.setUnidade(viaCep.getUnidade());
//		
//		return endereco;
//	}
	public EnderecoResponseDTO consultarEndereco(EnderecoRequestDTO enderecoRequestDTO) {
		EnderecoResponseDTO viaCep = util.buscarEndereco(enderecoRequestDTO.getCep());
		
		EnderecoResponseDTO endereco = new EnderecoResponseDTO();
		endereco.setCep(viaCep.getCep());
		endereco.setBairro(viaCep.getBairro());
		endereco.setComplemento(enderecoRequestDTO.getComplemento());
		endereco.setNumero(enderecoRequestDTO.getNumero());
		endereco.setDdd(viaCep.getDdd());
		endereco.setEstado(viaCep.getEstado());
		endereco.setGia(viaCep.getGia());
		endereco.setIbge(viaCep.getIbge());
		endereco.setLocalidade(viaCep.getLocalidade());
		endereco.setLogradouro(viaCep.getLogradouro());
		endereco.setRegiao(viaCep.getRegiao());
		endereco.setSiafi(viaCep.getSiafi());
		endereco.setUf(viaCep.getUf());
		endereco.setUnidade(viaCep.getUnidade());
		
		Endereco enderecoConvertido = endereco.toEndereco();
		enderecoRepository.save(enderecoConvertido);
		
		return endereco;
	}
	
	public Endereco atualizarEndereco(Integer id, EnderecoRequestDTO enderecoRequestDTO) {
		EnderecoResponseDTO viaCep = util.buscarEndereco(enderecoRequestDTO.getCep());
		Endereco enderecoExistente = enderecoRepository.findById(id).orElse(null);
		
		if(enderecoExistente!=null) {
			enderecoExistente.setCep(viaCep.getCep());
			enderecoExistente.setBairro(viaCep.getBairro());
			enderecoExistente.setComplemento(enderecoRequestDTO.getComplemento());
			enderecoExistente.setNumero(enderecoRequestDTO.getNumero());
			enderecoExistente.setDdd(viaCep.getDdd());
			enderecoExistente.setEstado(viaCep.getEstado());
			enderecoExistente.setGia(viaCep.getGia());
			enderecoExistente.setIbge(viaCep.getIbge());
			enderecoExistente.setLocalidade(viaCep.getLocalidade());
			enderecoExistente.setLogradouro(viaCep.getLogradouro());
			enderecoExistente.setRegiao(viaCep.getRegiao());
			enderecoExistente.setSiafi(viaCep.getSiafi());
			enderecoExistente.setUf(viaCep.getUf());
			enderecoExistente.setUnidade(viaCep.getUnidade());
			
			return enderecoRepository.save(enderecoExistente);
		} else {
			return null;
		}
		
	}

//	public EnderecoResponseDTO buscarEndereco(Integer id) {
//		Endereco endereco = enderecoRepository.findById(id).get();
//		Optional<Endereco> endereco = enderecoRepository.findById(id);
//		EnderecoResponseDTO enderecoResponseDTO = new EnderecoResponseDTO();
//		enderecoResponseDTO.setBairro(endereco.get().getBairro());
//		enderecoResponseDTO.setCep(endereco.get().getCep());
//		enderecoResponseDTO.setComplemento(endereco.get().getComplemento());
//		enderecoResponseDTO.setDdd(endereco.get().getDdd());
//		enderecoResponseDTO.setEstado(endereco.get().getEstado());
//		enderecoResponseDTO.setGia(endereco.get().getGia());
//		enderecoResponseDTO.setIbge(endereco.get().getIbge());
//		enderecoResponseDTO.setLocalidade(endereco.get().getLocalidade());
//		enderecoResponseDTO.setLogradouro(endereco.get().getLogradouro());
//		enderecoResponseDTO.setNumero(endereco.get().getNumero());
//		enderecoResponseDTO.setRegiao(endereco.get().getRegiao());
//		enderecoResponseDTO.setSiafi(endereco.get().getSiafi());
//		enderecoResponseDTO.setUf(endereco.get().getUf());
//		enderecoResponseDTO.setUnidade(endereco.get().getUnidade());
//		return enderecoResponseDTO;
//	}
	
	public void deletarEndereco(Integer id) {
		enderecoRepository.deleteById(id);
	}
}
