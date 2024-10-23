package br.com.example.montadora.security.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.example.montadora.security.dto.CarroRequestDTO;
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

	public List<CarroResponseDTO> listarCarros() {
		List<Carro> carros = carroRepository.findAll();

		return carros.stream().map(carro -> {

			String nomeConcessionaria = carro.getFkConcessionaria().getNome();

			return new CarroResponseDTO(carro.getModelo(), carro.getMarca(), carro.getAno(), nomeConcessionaria);
		}).collect(Collectors.toList());
	}

	public void cadastrarCarro(CarroRequestDTO carroRequestDTO) {
		Carro newCarro = new Carro();
		newCarro.setMarca(carroRequestDTO.getMarca());
		newCarro.setModelo(carroRequestDTO.getModelo());
		newCarro.setAno(carroRequestDTO.getAno());

//		Concessionaria concessionaria = concessionariaRepository.buscarConce(carroResponseDTO.getNomeConcessionaria());
		Concessionaria concessionaria = concessionariaRepository.buscarConceUnica();
		newCarro.setFkConcessionaria(concessionaria);

		carroRepository.save(newCarro);

	}

	public CarroResponseDTO buscarPorId(Integer id) {
		Optional<Carro> carro = carroRepository.findById(id);

		CarroResponseDTO carroResponseDTO = new CarroResponseDTO();
		carroResponseDTO.setMarca(carro.get().getMarca());
		carroResponseDTO.setModelo(carro.get().getModelo());
		carroResponseDTO.setAno(carro.get().getAno());
		carroResponseDTO.setNomeConcessionaria(carro.get().getFkConcessionaria().getNome());
		return carroResponseDTO;
	}

	public boolean deletarCarro(Integer id) {
		if (carroRepository.existsById(id)) {
			carroRepository.deleteById(id);
			return true;
		} else {
			return false;
		}
	}

	public Carro atualizarCarro(Integer id, CarroRequestDTO carroRequestDTO) {
		Carro carroExistente = carroRepository.findById(id).orElse(null);

		if (carroRequestDTO.getMarca() != null) {
			carroExistente.setMarca(carroRequestDTO.getMarca());
		}
		if (carroRequestDTO.getModelo() != null) {
			carroExistente.setModelo(carroRequestDTO.getModelo());
		}
		if (carroRequestDTO.getAno() != null) {
			carroExistente.setAno(carroRequestDTO.getAno());			
		}

		return carroRepository.save(carroExistente);

	}
}
