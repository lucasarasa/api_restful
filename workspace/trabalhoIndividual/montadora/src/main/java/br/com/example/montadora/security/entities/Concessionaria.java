package br.com.example.montadora.security.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "concessionaria")
public class Concessionaria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "con_cd_id")
	private Integer id;

	@Column(name = "con_tx_nome")
	private String nome;

	@Column(name = "con_tx_telefone")
	private String telefone;

	@Column(name = "con_tx_email")
	private String email;

	@OneToMany(mappedBy = "fkConcessionaria", cascade = CascadeType.ALL, orphanRemoval = true)
	@Column(name = "con_fk_carro")
	private List<Carro> fkCarro;

	@OneToMany(mappedBy = "fkConcessionaria", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference
	private List<User> fkuser;

	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(unique = true, name = "fk_endereco")
	private Endereco fkEndereco;

	public Concessionaria() {
	}

	public Concessionaria(Integer id, String nome, String telefone, String email, List<Carro> fkCarro,
			List<User> fkuser, Endereco fkEndereco) {
		this.id = id;
		this.nome = nome;
		this.telefone = telefone;
		this.email = email;
		this.fkCarro = fkCarro;
		this.fkuser = fkuser;
		this.fkEndereco = fkEndereco;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public List<Carro> getFkCarro() {
		return fkCarro;
	}

	public void setFkCarro(List<Carro> fkCarro) {
		this.fkCarro = fkCarro;
	}

	public List<User> getFkuser() {
		return fkuser;
	}

	public void setFkuser(List<User> fkuser) {
		this.fkuser = fkuser;
	}

	public Endereco getFkEndereco(Endereco fkEndereco) {
		return fkEndereco;
	}

	public void setFkEndereco(Endereco fkEndereco) {
		this.fkEndereco = fkEndereco;
	}

	@Override
	public String toString() {
		return "Concessionaria [id=" + id + ", nome=" + nome + ", telefone=" + telefone + ", email=" + email
				+ ", fkCarro=" + fkCarro + ", fkuser=" + fkuser + ", fkEndereco=" + fkEndereco + "]";
	}

}
