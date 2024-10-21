package br.com.example.montadora.security.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.example.montadora.security.dto.CarroResponseDTO;
import br.com.example.montadora.security.dto.MessageResponseDTO;
import br.com.example.montadora.security.entities.Carro;
import br.com.example.montadora.security.services.CarroServices;
import br.com.example.montadora.utils.Util;

@RestController
@RequestMapping("/carro")
public class CarroController {
	
	@Autowired
	Util util;
	
	@Autowired
	CarroServices carroServices;
	
	@GetMapping("/listarCarros")
	public List<Carro> listarCarros(){
		return carroServices.listarCarros();
	}
	
	@PostMapping("/cadastrarCarro")
	public ResponseEntity<?> cadastrarCarro(@RequestBody CarroResponseDTO carro) {
		carroServices.cadastrarCarro(carro);
		return ResponseEntity.ok(new MessageResponseDTO("Carro cadastrado com sucesso"));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deletarCarro(@PathVariable Integer id){
		boolean resultDelete = carroServices.deletarCarro(id);
		if(resultDelete) {
			return ResponseEntity.status(HttpStatus.OK).body("Carro deletado com sucesso!");
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Err: Falha ao deletar o objeto.");
		}
	}
	
	@GetMapping("/{id}")
	public CarroResponseDTO buscarPorId(@PathVariable Integer id) {
		return carroServices.buscarPorId(id);
	}
	
	@PutMapping("/atualizarCarro{id}")
	public ResponseEntity<Carro> atualizarCarro(@PathVariable Integer id, @RequestBody CarroResponseDTO carroResponseDTO){
		Carro carroAtt = carroServices.atualizarCarro(id, carroResponseDTO);
		return ResponseEntity.ok(carroAtt);
	}	
}
