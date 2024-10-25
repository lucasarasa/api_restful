package br.com.example.montadora.security.controllers;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.example.montadora.security.dto.MessageResponseDTO;
import br.com.example.montadora.security.dto.SignupRequestDTO;
import br.com.example.montadora.security.dto.UserRequestDTO;
import br.com.example.montadora.security.dto.UserResponseDTO;
import br.com.example.montadora.security.entities.Concessionaria;
import br.com.example.montadora.security.entities.Role;
import br.com.example.montadora.security.entities.User;
import br.com.example.montadora.security.enums.RoleEnum;
import br.com.example.montadora.security.repositories.ConcessionariaRepository;
import br.com.example.montadora.security.repositories.RoleRepository;
import br.com.example.montadora.security.repositories.UserRepository;
import br.com.example.montadora.security.services.EmailService;
import br.com.example.montadora.security.services.FotoService;
import br.com.example.montadora.security.services.UserServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserServices userServices;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PasswordEncoder encoder;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	ConcessionariaRepository concessionariaRepository;

	@Autowired
	FotoService fotoService;
	
	@Autowired
	EmailService emailService;
	
	
	@SecurityRequirement(name = "Bearer Auth")
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping
	@Operation(summary = "Obter a lista de todos os usuários cadastrados.")
	public List<UserResponseDTO> listarUsuarios() {
		return userServices.listarUsuarios();
	}

//	@PostMapping
//	@Operation(summary = "Cadastrar usuário.")
//	public ResponseEntity<?> cadastrarUsuario(@RequestParam String username, @RequestParam String email,
//			@RequestParam String password) {
//		userServices.cadastrarUsuario(username, email, password);
//		return ResponseEntity.ok(new MessageResponseDTO("Usuário cadastrado com sucesso!"));
//	}
	
	@PostMapping("/signup")
	@Operation(summary = "Cadastrar usuário.")
	public ResponseEntity<?> cadastrarUsuario(@Valid @RequestPart SignupRequestDTO signUpRequest, @RequestPart MultipartFile foto) throws IOException {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity.badRequest().body(new MessageResponseDTO("Erro: Username já utilizado!"));
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity.badRequest().body(new MessageResponseDTO("Erro: Email já utilizado!"));
		}

		User user = new User(signUpRequest.getUsername(), signUpRequest.getEmail(),
				encoder.encode(signUpRequest.getPassword()));

		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();

		if (strRoles == null) {
			Role userRole = roleRepository.findByName(RoleEnum.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Erro: Role não encontrada."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":
					Role adminRole = roleRepository.findByName(RoleEnum.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Erro: Role não encontrada."));
					roles.add(adminRole);

					break;
				case "mod":
					Role modRole = roleRepository.findByName(RoleEnum.ROLE_MODERATOR)
					.orElseThrow(() -> new RuntimeException("Erro: Role não encontrada."));
					roles.add(modRole);
					
					break;
				default:
					Role userRole = roleRepository.findByName(RoleEnum.ROLE_USER)
							.orElseThrow(() -> new RuntimeException("Erro: Role não encontrada."));
					roles.add(userRole);
				}
			});
		}
		
		Concessionaria concessionaria = concessionariaRepository.buscarConceUnica();
		user.setFkConcessionaria(concessionaria);

		user.setRoles(roles);
		userRepository.save(user);
		fotoService.cadastrarFoto(foto, user);
		emailService.writerTeste2(signUpRequest);
		return ResponseEntity.ok(new MessageResponseDTO("Usuário registrado com sucesso!"));
	}

	@SecurityRequirement(name = "Bearer Auth")
	@PreAuthorize("hasRole('USER')")
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

	@SecurityRequirement(name = "Bearer Auth")
	@PreAuthorize("hasRole('USER')")
	@PutMapping("/{id}")
	@Operation(summary = "Atualizar usuário.")
	public ResponseEntity<?> atualizarUsuario(@PathVariable Integer id, @RequestBody UserRequestDTO user) {
		User userAtt = userServices.atualizarUsuario(id, user);

		if (userAtt != null) {	
			return ResponseEntity.status(HttpStatus.OK).body("Usuário atualizado com sucesso!");
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Err: Falha ao atualizar o usuário.");
		}
	}
}
