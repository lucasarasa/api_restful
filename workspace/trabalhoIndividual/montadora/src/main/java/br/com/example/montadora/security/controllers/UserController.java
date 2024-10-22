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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.example.montadora.security.dto.MessageResponseDTO;
import br.com.example.montadora.security.dto.UserResponseDTO;
import br.com.example.montadora.security.entities.User;
import br.com.example.montadora.security.services.UserServices;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserServices userServices;

	@GetMapping
	@Operation(summary = "Obter a lista de todos os usuários cadastrados.")
	public List<UserResponseDTO> listarUsuarios() {
		return userServices.listarUsuarios();
	}

	@PostMapping
	@Operation(summary = "Cadastrar usuário.")
	public ResponseEntity<?> cadastrarUsuario(@RequestParam String username, @RequestParam String email,
			@RequestParam String password) {
		userServices.cadastrarUsuario(username, email, password);
		return ResponseEntity.ok(new MessageResponseDTO("Usuário cadastrado com sucesso!"));
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Deletar usuário.")
	public ResponseEntity<String> deletarUsuario(@PathVariable Integer id) {
		boolean resultDelete = userServices.deletarUsuario(id);
		if (resultDelete) {
			return ResponseEntity.status(HttpStatus.OK).body("Usuário deletado com sucesso.");
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Err: Falha ao deletar o objeto.");
		}
	}

	@PutMapping("/{id}")
	@Operation(summary = "Atualizar usuário.")
	public ResponseEntity<?> atualizarUsuario(@PathVariable Integer id, @RequestBody UserResponseDTO user) {
		User userAtt = userServices.atualizarUsuario(id, user);

		if (userAtt != null) {
			return ResponseEntity.status(HttpStatus.OK).body("Usuário atualizado com sucesso!");
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Err: Falha ao atualizar o usuário.");
		}
	}
}
