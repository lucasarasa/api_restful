package br.com.example.montadora.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "concessionaria")
public class Concessionaria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "con_cd_id")
	private Integer id;

	@Column(name = "con_cd_nome")
	private String nome;

	@Column(name = "con_cd_endereco")
	private String endereco;

	@Column(name = "con_cd_numero")
	private int numero;

	public Concessionaria() {
	}

	public Concessionaria(Integer id, String nome, String endereco, int numero) {
		super();
		this.id = id;
		this.nome = nome;
		this.endereco = endereco;
		this.numero = numero;
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

	public Integer getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Concessionaria [id=" + id + ", nome=" + nome + ", endereco=" + endereco + ", numero=" + numero + "]";
	}

}
