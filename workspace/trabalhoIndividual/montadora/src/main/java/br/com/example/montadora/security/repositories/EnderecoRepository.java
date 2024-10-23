package br.com.example.montadora.security.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.example.montadora.security.entities.Endereco;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Integer>{

	@Query(value ="select * from endereco where end_tx_bairro = :nome;", nativeQuery = true)
	public Endereco buscarEndereco(String nome);
	
	@Query(value="select * from Endereco limit 1", nativeQuery =true)
	public Endereco buscarEnderecoUnico();
}
