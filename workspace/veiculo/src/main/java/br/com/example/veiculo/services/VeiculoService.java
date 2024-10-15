package br.com.example.veiculo.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import br.com.example.veiculo.entities.Veiculo;
import br.com.example.veiculo.repositories.VeiculoRepository;

@Service
public class VeiculoService {

	@Autowired
	private VeiculoRepository veiculoRepository;
	
	public List<Veiculo> listarVeiculos(){
		return veiculoRepository.findAll();
	}
	
	public Veiculo adicionarVeiculo(Veiculo veiculo) {
		return veiculoRepository.save(veiculo);		
	}
	
	public void removerVeiculo(Integer id) {
		veiculoRepository.deleteById(id);
	}
	
	public ResponseEntity<Veiculo> atualizarVeiculo(Integer id, Veiculo veiculoAtualizado) {
		if(!veiculoRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}		
		veiculoAtualizado.setId(id);
		Veiculo veiculo = veiculoRepository.save(veiculoAtualizado);
		return ResponseEntity.ok(veiculo);
	}
	
	public Optional<Veiculo> buscarVeiculo(Integer id) {
		return veiculoRepository.findById(id);
	}
}
