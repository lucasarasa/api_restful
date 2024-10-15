package br.com.example.veiculo.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.example.veiculo.entities.Veiculo;
import br.com.example.veiculo.services.VeiculoService;

@RestController
@RequestMapping("/veiculos")
public class VeiculoController {

	@Autowired
	private VeiculoService veiculoService;
	
	@GetMapping
	public List<Veiculo> listarVeiculos(){
		return veiculoService.listarVeiculos();
	}
	
	@PostMapping("/adicionarVeiculo")
	public Veiculo adicionarVeiculo(Veiculo veiculo) {
		return veiculoService.adicionarVeiculo(veiculo);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> removerVeiculo(@PathVariable Integer id) {
		veiculoService.removerVeiculo(id);
		System.out.println("Deletado com sucesso.");
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/{id}")
	public Optional<Veiculo> buscarVeiculo(@PathVariable Integer id){
		return veiculoService.buscarVeiculo(id);		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Veiculo> atualizarVeiculo(@PathVariable Integer id,@RequestBody Veiculo veiculoAtualizado){
		veiculoService.atualizarVeiculo(id, veiculoAtualizado);
		return ResponseEntity.ok(veiculoAtualizado);
	}
}
