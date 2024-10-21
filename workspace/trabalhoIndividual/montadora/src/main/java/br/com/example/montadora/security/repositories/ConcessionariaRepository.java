package br.com.example.montadora.security.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.example.montadora.security.entities.Concessionaria;

@Repository
public interface ConcessionariaRepository extends JpaRepository<Concessionaria, Integer>{

	@Query(value ="select * from concessionaria where con_tx_nome = :nome;", nativeQuery=true)
	public Concessionaria buscarConce(String nome);
}
