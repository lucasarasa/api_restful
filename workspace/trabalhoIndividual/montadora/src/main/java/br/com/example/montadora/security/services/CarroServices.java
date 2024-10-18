package br.com.example.montadora.security.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.example.montadora.security.dto.CarroResponseDTO;
import br.com.example.montadora.security.entities.Carro;
import br.com.example.montadora.security.repositories.CarroRepository;

@Service
public class CarroServices {

	@Autowired
	private CarroRepository carroRepository;

	public List<Carro> listarCarros() {
		return carroRepository.findAll();
	}

	public CarroResponseDTO adicionarCarro(CarroResponseDTO carroResponseDTO) {
		CarroResponseDTO carroDTO = new CarroResponseDTO();
		carroDTO.setMarca(carroResponseDTO.getMarca());
		carroDTO.setModelo(carroResponseDTO.getModelo());
		carroDTO.setAno(carroResponseDTO.getAno());
		
		Carro carro = carroResponseDTO.toCarro();
		carroRepository.save(carro);
		
		return carroDTO;
	}

	public CarroResponseDTO buscarPorId(Integer id) {
		Optional<Carro> carro = carroRepository.findById(id);
		CarroResponseDTO carroResponseDTO = new CarroResponseDTO();
		carroResponseDTO.setMarca(carro.get().getMarca());
		carroResponseDTO.setModelo(carro.get().getModelo());
		carroResponseDTO.setAno(carro.get().getAno());
		return carroResponseDTO;
	}

	public void deletarPorId(Integer id) {
		carroRepository.deleteById(id);
	}

	public Carro atualizar(Carro carro) {
		return carroRepository.save(carro);
	}
}
