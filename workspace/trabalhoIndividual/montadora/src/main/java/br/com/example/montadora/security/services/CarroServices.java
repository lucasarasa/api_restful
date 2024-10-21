package br.com.example.montadora.security.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.example.montadora.security.dto.CarroResponseDTO;
import br.com.example.montadora.security.entities.Carro;
import br.com.example.montadora.security.entities.Concessionaria;
import br.com.example.montadora.security.repositories.CarroRepository;
import br.com.example.montadora.security.repositories.ConcessionariaRepository;

@Service
public class CarroServices {

	@Autowired
	private CarroRepository carroRepository;
	
	@Autowired
	private ConcessionariaRepository concessionariaRepository;

	public List<Carro> listarCarros() {
		return carroRepository.findAll();
	}

	public void cadastrarCarro(CarroResponseDTO carroResponseDTO) {
		Carro newCarro = new Carro();
		newCarro.setMarca(carroResponseDTO.getMarca());
		newCarro.setModelo(carroResponseDTO.getModelo());
		newCarro.setAno(carroResponseDTO.getAno());

		Concessionaria concessionaria = concessionariaRepository.buscarConce(carroResponseDTO.getNomeConcessionaria());
		newCarro.setFkConcessionaria(concessionaria);
		
		carroRepository.save(newCarro);
		
	}

	public CarroResponseDTO buscarPorId(Integer id) {
		Optional<Carro> carro = carroRepository.findById(id);
		CarroResponseDTO carroResponseDTO = new CarroResponseDTO();
		carroResponseDTO.setMarca(carro.get().getMarca());
		carroResponseDTO.setModelo(carro.get().getModelo());
		carroResponseDTO.setAno(carro.get().getAno());
		return carroResponseDTO;
	}

	public boolean deletarCarro(Integer id) {
		if(carroRepository.existsById(id)) {
			carroRepository.deleteById(id);
			return true;
		} else {
			return false;
		}
	}

	public Carro atualizarCarro(Integer id, CarroResponseDTO carroResponseDTO) {
		Carro carroExistente = carroRepository.findById(id).orElse(null);
		
		if(carroExistente != null) {
			carroExistente.setMarca(carroResponseDTO.getMarca());
			carroExistente.setModelo(carroResponseDTO.getModelo());
			carroExistente.setAno(carroResponseDTO.getAno());
			
			return carroRepository.save(carroExistente);
		} else {
			return null;
		}
	}
}
