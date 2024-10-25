package br.com.example.montadora.security.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.example.montadora.security.dto.CarroRequestDTO;
import br.com.example.montadora.security.dto.CarroResponseDTO;
import br.com.example.montadora.security.dto.MessageResponseDTO;
import br.com.example.montadora.security.entities.Carro;
import br.com.example.montadora.security.services.CarroServices;
import br.com.example.montadora.utils.Util;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/carro")
public class CarroController {

	@Autowired
	Util util;

	@Autowired
	CarroServices carroServices;

	@SecurityRequirement(name = "Bearer Auth")
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping
	@Operation(summary = "Obter a lista de todos os carros.")
	public List<CarroResponseDTO> listarCarros() {
		return carroServices.listarCarros();
	}

	@SecurityRequirement(name = "Bearer Auth")
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping
	@Operation(summary = "Cadastrar carro.")
	public ResponseEntity<?> cadastrarCarro(@RequestBody CarroRequestDTO carro) {
		carroServices.cadastrarCarro(carro);
		return ResponseEntity.ok(new MessageResponseDTO("Carro cadastrado com sucesso"));
	}

	@SecurityRequirement(name = "Bearer Auth")
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{id}")
	@Operation(summary = "Deletar carro.")
	public ResponseEntity<String> deletarCarro(@PathVariable Integer id) {
		boolean resultDelete = carroServices.deletarCarro(id);
		if (resultDelete) {
			return ResponseEntity.status(HttpStatus.OK).body("Carro deletado com sucesso!");
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Err: Falha ao deletar o objeto.");
		}
	}

	@SecurityRequirement(name = "Bearer Auth")
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/{id}")
	@Operation(summary = "Buscar carro por id.")
	public CarroResponseDTO buscarPorId(@PathVariable Integer id) {
		return carroServices.buscarPorId(id);
	}

	@SecurityRequirement(name = "Bearer Auth")
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/{id}")
	@Operation(summary = "Atualizar carro.")
	public ResponseEntity<?> atualizarCarro(@PathVariable Integer id, @RequestBody CarroRequestDTO carroRequestDTO) {
		Carro carroAtt = carroServices.atualizarCarro(id, carroRequestDTO);

		if (carroAtt != null) {
			return ResponseEntity.status(HttpStatus.OK).body("Carro atualizado com sucesso!");
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Err: Falha ao atualizar o carro.");
		}
	}
}
