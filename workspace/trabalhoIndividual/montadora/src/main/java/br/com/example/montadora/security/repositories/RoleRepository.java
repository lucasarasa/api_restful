package br.com.example.montadora.security.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.example.montadora.security.entities.Role;
import br.com.example.montadora.security.enums.RoleEnum;

@Repository("role")
public interface RoleRepository extends JpaRepository<Role, Integer> {
	//findByName(RoleEnum name)-> busca pelo nome, porém só aceita se existir no enum RoleEnum
	Optional<Role> findByName(RoleEnum name);
}
