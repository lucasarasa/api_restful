package br.com.example.montadora.security.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "endereco")
public class Endereco {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "end_cd_id")
	private Integer id;

	@Column(name = "end_tx_cep")
	private String cep;

	@Column(name = "end_tx_logradouro")
	private String logradouro;

	@Column(name = "end_tx_complemento")
	private String complemento;

	@Column(name = "end_tx_numero")
	private int numero;

	@Column(name = "end_tx_unidade")
	private String unidade;

	@Column(name = "end_tx_bairro")
	private String bairro;

	@Column(name = "end_tx_localidade")
	private String localidade;

	@Column(name = "end_tx_uf")
	private String uf;

	@Column(name = "end_tx_estado")
	private String estado;

	@Column(name = "end_tx_regiao")
	private String regiao;

	@Column(name = "end_tx_ibge")
	private String ibge;

	@Column(name = "end_tx_gia")
	private String gia;

	@Column(name = "end_tx_ddd")
	private String ddd;

	@Column(name = "end_tx_siafi")
	private String siafi;

	@OneToOne(mappedBy = "fkEndereco")
	private Concessionaria fkConcessionaria;
	
	public Endereco() {
	}

	public Endereco(Integer id, String cep, String logradouro, String complemento, int numero, String unidade,
			String bairro, String localidade, String uf, String estado, String regiao, String ibge, String gia,
			String ddd, String siafi) {
		this.id = id;
		this.cep = cep;
		this.logradouro = logradouro;
		this.complemento = complemento;
		this.numero = numero;
		this.unidade = unidade;
		this.bairro = bairro;
		this.localidade = localidade;
		this.uf = uf;
		this.estado = estado;
		this.regiao = regiao;
		this.ibge = ibge;
		this.gia = gia;
		this.ddd = ddd;
		this.siafi = siafi;
	}

	public Endereco(int numero, String bairro, String cep, String complemento, String ddd, String estado, String gia,
			String ibge, String localidade, String logradouro, String regiao, String siafi, String uf, String unidade) {
		this.cep = cep;
		this.logradouro = logradouro;
		this.complemento = complemento;
		this.numero = numero;
		this.unidade = unidade;
		this.bairro = bairro;
		this.localidade = localidade;
		this.uf = uf;
		this.estado = estado;
		this.regiao = regiao;
		this.ibge = ibge;
		this.gia = gia;
		this.ddd = ddd;
		this.siafi = siafi;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getUnidade() {
		return unidade;
	}

	public void setUnidade(String unidade) {
		this.unidade = unidade;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getLocalidade() {
		return localidade;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getRegiao() {
		return regiao;
	}

	public void setRegiao(String regiao) {
		this.regiao = regiao;
	}

	public String getIbge() {
		return ibge;
	}

	public void setIbge(String ibge) {
		this.ibge = ibge;
	}

	public String getGia() {
		return gia;
	}

	public void setGia(String gia) {
		this.gia = gia;
	}

	public String getDdd() {
		return ddd;
	}

	public void setDdd(String ddd) {
		this.ddd = ddd;
	}

	public String getSiafi() {
		return siafi;
	}

	public void setSiafi(String siafi) {
		this.siafi = siafi;
	}

	public Concessionaria getFkConcessionaria() {
		return fkConcessionaria;
	}

	public void setFkConcessionaria(Concessionaria fkConcessionaria) {
		this.fkConcessionaria = fkConcessionaria;
	}

	@Override
	public String toString() {
		return "Endereco [id=" + id + ", cep=" + cep + ", logradouro=" + logradouro + ", complemento=" + complemento
				+ ", numero=" + numero + ", unidade=" + unidade + ", bairro=" + bairro + ", localidade=" + localidade
				+ ", uf=" + uf + ", estado=" + estado + ", regiao=" + regiao + ", ibge=" + ibge + ", gia=" + gia
				+ ", ddd=" + ddd + ", siafi=" + siafi + ", concessionaria=" + fkConcessionaria + "]";
	}

}
