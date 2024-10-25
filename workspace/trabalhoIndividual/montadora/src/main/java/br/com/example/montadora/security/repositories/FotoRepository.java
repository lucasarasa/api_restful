package br.com.example.montadora.security.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.example.montadora.security.entities.Foto;

@Repository
public interface FotoRepository extends JpaRepository<Foto, Integer>{

}
