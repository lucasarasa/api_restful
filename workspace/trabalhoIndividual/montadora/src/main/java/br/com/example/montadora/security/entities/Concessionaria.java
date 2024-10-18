package br.com.example.montadora.security.entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

	@Column(name = "con_tx_endereco")
	private String endereco;

	@Column(name = "con_int_numero")
	private int numero;

	@OneToMany(mappedBy = "fkConcessionaria")
	@Column(name = "con_fk_carro")
	private List<Carro> fkCarro;

	@OneToMany(mappedBy = "fkConcessionaria")
	@Column(name = "con_fk_user")
	private List<User> fkuser;

	@OneToOne(mappedBy = "fkConcessionaria")
	private Endereco fkEndereco;

	public Concessionaria() {
	}

	public Concessionaria(Integer id, String nome, String endereco, int numero, List<Carro> fkCarro, List<User> fkuser,
			Endereco fkEndereco) {
		this.id = id;
		this.nome = nome;
		this.endereco = endereco;
		this.numero = numero;
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

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
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

	public Endereco getFkEndereco() {
		return fkEndereco;
	}

	public void setFkEndereco(Endereco fkEndereco) {
		this.fkEndereco = fkEndereco;
	}

	@Override
	public String toString() {
		return "Concessionaria [id=" + id + ", nome=" + nome + ", endereco=" + endereco + ", numero=" + numero
				+ ", fkCarro=" + fkCarro + ", fkuser=" + fkuser + ", fkEndereco=" + fkEndereco + "]";
	}

}
