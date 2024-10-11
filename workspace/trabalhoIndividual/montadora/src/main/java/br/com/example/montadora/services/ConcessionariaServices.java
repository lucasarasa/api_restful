package br.com.example.montadora.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.example.montadora.repositories.ConcessionariaRepository;

@Service
public class ConcessionariaServices {
	@Autowired
	private ConcessionariaRepository concessionariaRepository;
}
