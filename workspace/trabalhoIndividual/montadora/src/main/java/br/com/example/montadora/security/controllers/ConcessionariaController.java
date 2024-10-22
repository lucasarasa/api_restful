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

import br.com.example.montadora.security.dto.ConcessionariaRequestDTO;
import br.com.example.montadora.security.dto.EnderecoRequestDTO;
import br.com.example.montadora.security.dto.EnderecoResponseDTO;
import br.com.example.montadora.security.dto.MessageResponseDTO;
import br.com.example.montadora.security.entities.Endereco;
import br.com.example.montadora.security.services.ConcessionariaServices;
import br.com.example.montadora.security.services.EmailService;
import br.com.example.montadora.security.services.EnderecoService;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/concessionaria")
public class ConcessionariaController {
	
	@Autowired
	EnderecoService enderecoService;
	
	@Autowired
	EmailService emailService;
	
	@Autowired
	ConcessionariaServices concessionariaService;
	
//	@PostMapping("/teste-cep")
//	public EnderecoResponseDTO testeEndereco(@RequestParam	String cep, @RequestParam String complemento, @RequestParam int numero) {
//		return enderecoService.consultarEndereco(cep, complemento, numero);
//	}
	
	@GetMapping
	@Operation(summary = "Obter a lista de todas as concessionárias.")
	public List<ConcessionariaRequestDTO> listarConce(){
		return concessionariaService.listarConce();
	}
	
	@PostMapping
	@Operation(summary = "Cadastrar concessionária.")
		public ResponseEntity<?> cadastrarConce(@RequestBody ConcessionariaRequestDTO concessionariaRequestDTO){
			concessionariaService.cadastrarConcessionaria(concessionariaRequestDTO);
			return ResponseEntity.ok(new MessageResponseDTO("Concessionaria cadastrada com sucesso!"));
		}
	
	@PostMapping("/")
	@Operation(summary = "Adicionar o endereço da concessionária.")
	public EnderecoResponseDTO testeEndereco(@RequestBody EnderecoRequestDTO enderecoRequestDTO) {
		return enderecoService.consultarEndereco(enderecoRequestDTO);
	}
	
	@PutMapping("/")
	@Operation(summary = "Atualizar o endereço da concessionária.")
	public ResponseEntity<Endereco> atualizarEndereco(@PathVariable Integer id, @RequestBody EnderecoRequestDTO enderecoRequestDTO){
		Endereco conceEnderecoAtt = enderecoService.atualizarEndereco(id, enderecoRequestDTO);
		return ResponseEntity.ok(conceEnderecoAtt);
	}
	
	@DeleteMapping("/{id}")
	@Operation(summary = "Deletar concessionária.")
	public ResponseEntity<String> deletarId(@PathVariable Integer id){
		boolean resultDelete = concessionariaService.concessionariaDelete(id);
		if(resultDelete) {
			return ResponseEntity.status(HttpStatus.OK).body("Concessionaria deletada com sucesso!");
		}else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Err: Falha ao deletar o objeto.");
		}
	}

	@GetMapping("/")
	@Operation(summary = "Disparar e-mail para o comprador.")
	public String mailSend() {
		emailService.mailSend();
		return "Email enviado com sucesso!";
	}
	
	@PutMapping
	@Operation(summary = "Atualizar concessionária.")
	public ResponseEntity<?> atualizarConcessionaria(@RequestBody ConcessionariaRequestDTO conce){
		concessionariaService.atualizarConcessionaria(conce);
		return ResponseEntity.ok(new MessageResponseDTO("Concessionaria atualizada com sucesso!"));		
	}
}
