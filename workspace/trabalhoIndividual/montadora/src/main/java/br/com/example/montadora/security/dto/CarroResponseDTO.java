package br.com.example.montadora.security.dto;

import br.com.example.montadora.security.entities.Carro;

public class CarroResponseDTO {

	private String marca;
	private String modelo;
	private Long ano;
	private String nomeConcessionaria;

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

	public String getNomeConcessionaria() {
		return nomeConcessionaria;
	}

	public void setNomeConcessionaria(String nomeConcessionaria) {
		this.nomeConcessionaria = nomeConcessionaria;
	}

	public Carro toCarro() {
		return new Carro(this.marca, this.modelo, this.ano);
	}
}
