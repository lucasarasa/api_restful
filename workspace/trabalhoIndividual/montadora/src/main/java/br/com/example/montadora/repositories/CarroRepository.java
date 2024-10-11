package br.com.example.montadora.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.example.montadora.entities.Carro;

@Repository
public interface CarroRepository extends JpaRepository<Carro, Integer> {

}
