package br.com.example.montadora.security.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.example.montadora.security.entities.Carro;
import br.com.example.montadora.security.repositories.CarroRepository;

@Service
public class CarroServices {

	@Autowired
	private CarroRepository carroRepository;

	public List<Carro> listarCarros() {
		return carroRepository.findAll();
	}

	public Carro salvar(Carro carro) {
		return carroRepository.save(carro);
	}

	public Optional<Carro> buscarPorId(Integer id) {
		return carroRepository.findById(id);
	}

	public void deletarPorId(Integer id) {
		carroRepository.deleteById(id);
	}

	public Carro atualizar(Carro carro) {
		return carroRepository.save(carro);
	}
}
