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

import br.com.example.montadora.security.dto.ConcessionariaRequestDTO;
import br.com.example.montadora.security.dto.EnderecoRequestDTO;
import br.com.example.montadora.security.dto.EnderecoResponseDTO;
import br.com.example.montadora.security.dto.MessageResponseDTO;
import br.com.example.montadora.security.entities.Concessionaria;
import br.com.example.montadora.security.entities.Endereco;
import br.com.example.montadora.security.repositories.ConcessionariaRepository;
import br.com.example.montadora.security.repositories.EnderecoRepository;
import br.com.example.montadora.security.services.ConcessionariaServices;
import br.com.example.montadora.security.services.EmailService;
import br.com.example.montadora.security.services.EnderecoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/concessionaria")
public class ConcessionariaController {

	@Autowired
	EnderecoService enderecoService;

	@Autowired
	EmailService emailService;

	@Autowired
	ConcessionariaServices concessionariaService;
	
	@Autowired 
	ConcessionariaRepository concessionariaRepository;
	
	@Autowired
	EnderecoRepository enderecoRepository;

//	@PostMapping("/teste-cep")
//	public EnderecoResponseDTO testeEndereco(@RequestParam	String cep, @RequestParam String complemento, @RequestParam int numero) {
//		return enderecoService.consultarEndereco(cep, complemento, numero);
//	}

	@SecurityRequirement(name = "Bearer Auth")
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping
	@Operation(summary = "Obter a lista de todas as concessionárias.")
	public List<ConcessionariaRequestDTO> listarConce() {
		return concessionariaService.listarConce();
	}

	@SecurityRequirement(name = "Bearer Auth")
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping
	@Operation(summary = "Cadastrar concessionária.")
	public ResponseEntity<?> cadastrarConce(@RequestBody ConcessionariaRequestDTO concessionariaRequestDTO) {
		List<Concessionaria> concessionarias = concessionariaRepository.findAll();
		List<Endereco> enderecos = enderecoRepository.findAll();
		
		if(enderecos.isEmpty()) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Erro ao cadastrar a concessionária. Endereço não cadastrado.");
			
		} else {
			if (!concessionarias.isEmpty()) {
				return ResponseEntity.status(HttpStatus.CONFLICT)
						.body("Já existe uma concessionária cadastrada. Não é possível cadastrar outra.");
			} else {
				concessionariaService.cadastrarConcessionaria(concessionariaRequestDTO);
				
				return ResponseEntity.status(HttpStatus.CREATED).body("Concessionária cadastrada com sucesso.");
			}
		}

	}

	@SecurityRequirement(name = "Bearer Auth")
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/")
	@Operation(summary = "Adicionar o endereço da concessionária.")
	public ResponseEntity<?> testeEndereco(@RequestBody EnderecoRequestDTO enderecoRequestDTO) {
		List<Endereco> enderecos = enderecoRepository.findAll();
		
		if(!enderecos.isEmpty()) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Já existe um endereço cadastrado. Não é possível cadastrar outro.");
		} else {
			enderecoService.consultarEndereco(enderecoRequestDTO);
			return ResponseEntity.status(HttpStatus.CREATED).body("Endereço cadastrado com sucesso.");
		}
		
	}

	@SecurityRequirement(name = "Bearer Auth")
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/{id}")
	@Operation(summary = "Atualizar o endereço da concessionária.")
	public ResponseEntity<?> atualizarEndereco(@PathVariable Integer id,
			@RequestBody EnderecoRequestDTO enderecoRequestDTO) {
		Endereco conceEnderecoAtt = enderecoService.atualizarEndereco(id, enderecoRequestDTO);
		if (conceEnderecoAtt != null) {
			return ResponseEntity.status(HttpStatus.OK).body("Endereço da concessionária atualizado com sucesso.");
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Err: Falha ao atualizar o endereço.");
		}
	}

	@SecurityRequirement(name = "Bearer Auth")
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{id}")
	@Operation(summary = "Deletar concessionária.")
	public ResponseEntity<?> deletarId(@PathVariable Integer id){
		boolean resultDelete = concessionariaService.concessionariaDelete(id);
		if(resultDelete) {
			return ResponseEntity.status(HttpStatus.OK).body("Concessionaria deletada com sucesso!");
		}else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Err: Falha ao deletar o objeto.");
		}
	}

	@SecurityRequirement(name = "Bearer Auth")
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{id}/")
	@Operation(summary = "Deletar endereco.")
	public ResponseEntity<?> deletarEndereco(@PathVariable Integer id) {
		boolean resultDelete = enderecoService.deletarEndereco(id);
		if(resultDelete) {
			return ResponseEntity.status(HttpStatus.OK).body("Endereço deletado com sucesso!");
		}else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Err: Falha ao deletar o objeto.");
		}
		
	}

	@SecurityRequirement(name = "Bearer Auth")
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/")
	@Operation(summary = "Disparar e-mail para o comprador.")
	public String mailSend() {
		emailService.mailSend();
		return "Email enviado com sucesso!";
	}

	@SecurityRequirement(name = "Bearer Auth")
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping
	@Operation(summary = "Atualizar concessionária.")
	public ResponseEntity<?> atualizarConcessionaria(@RequestBody ConcessionariaRequestDTO conce) {
		concessionariaService.atualizarConcessionaria(conce);
		return ResponseEntity.ok(new MessageResponseDTO("Concessionaria atualizada com sucesso!"));
	}
}
