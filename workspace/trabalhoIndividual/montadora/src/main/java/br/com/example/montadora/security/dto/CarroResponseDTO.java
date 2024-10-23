package br.com.example.montadora.security.dto;

import br.com.example.montadora.security.entities.Carro;

public class CarroResponseDTO {

	private String marca;
	private String modelo;
	private String ano;
	private String nomeConcessionaria;

	public CarroResponseDTO() {
	}

	public CarroResponseDTO(String marca, String modelo, String ano, String nomeConcessionaria) {
		this.marca = marca;
		this.modelo = modelo;
		this.ano = ano;
		this.nomeConcessionaria = nomeConcessionaria;
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

	public String getAno() {
		return ano;
	}

	public void setAno(String ano) {
		this.ano = ano;
	}

//	public String getNomeConcessionaria() {
//		return nomeConcessionaria;
//	}
//
//	public void setNomeConcessionaria(String nomeConcessionaria) {
//		this.nomeConcessionaria = nomeConcessionaria;
//	}
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
