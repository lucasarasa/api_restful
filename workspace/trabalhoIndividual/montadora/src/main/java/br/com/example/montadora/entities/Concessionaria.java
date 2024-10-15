package br.com.example.montadora.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

	@ManyToOne
	@JoinColumn(name = "con_fk_carro")
	private Carro fkCarro;

	public Concessionaria() {
	}

	public Concessionaria(Integer id, String nome, String endereco, int numero, Carro fkCarro) {
		this.id = id;
		this.nome = nome;
		this.endereco = endereco;
		this.numero = numero;
		this.fkCarro = fkCarro;
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

	public Carro getFkCarro() {
		return fkCarro;
	}

	public void setFkCarro(Carro fkCarro) {
		this.fkCarro = fkCarro;
	}

	@Override
	public String toString() {
		return "Concessionaria [id=" + id + ", nome=" + nome + ", endereco=" + endereco + ", numero=" + numero
				+ ", fkCarro=" + fkCarro + "]";
	}

}
