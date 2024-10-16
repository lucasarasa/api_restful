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
@Table(name = "carro")
public class Carro {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "car_cd_id")
	private Integer id;

	@Column(name = "car_tx_marca")
	private String marca;

	@Column(name = "car_tx_modelo")
	private String modelo;

	@Column(name = "car_int_ano")
	private Long ano;

	@ManyToOne
	@JoinColumn(name = "fk_concessionaria")
	private Concessionaria fkConcessionaria;

	public Carro() {
	}

	public Carro(Integer id, String marca, String modelo, Long ano, Concessionaria fkConcessionaria) {
		this.id = id;
		this.marca = marca;
		this.modelo = modelo;
		this.ano = ano;
		this.fkConcessionaria = fkConcessionaria;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public Long getAno() {
		return ano;
	}

	public void setAno(Long ano) {
		this.ano = ano;
	}

	public Concessionaria getFkConcessionaria() {
		return fkConcessionaria;
	}

	public void setFkConcessionaria(Concessionaria fkConcessionaria) {
		this.fkConcessionaria = fkConcessionaria;
	}

	@Override
	public String toString() {
		return "Carro [id=" + id + ", marca=" + marca + ", modelo=" + modelo + ", ano=" + ano + ", fkConcessionaria="
				+ fkConcessionaria + "]";
	}

}
