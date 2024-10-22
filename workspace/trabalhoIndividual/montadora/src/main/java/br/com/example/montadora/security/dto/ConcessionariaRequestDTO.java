package br.com.example.montadora.security.dto;

public class ConcessionariaRequestDTO {

	private String nome;
	private String telefone;
	private String email;
//	private String nomeEndereco;

	public ConcessionariaRequestDTO() {
	}

	public ConcessionariaRequestDTO(String nome, String telefone, String email) {

		this.nome = nome;
		this.telefone = telefone;
		this.email = email;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

//	public String getNomeEndereco() {
//		return nomeEndereco;
//	}
//
//	public void setNomeEndereco(String nomeEndereco) {
//		this.nomeEndereco = nomeEndereco;
//	}

}
