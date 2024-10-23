package br.com.example.montadora.security.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.example.montadora.security.dto.UserRequestDTO;
import br.com.example.montadora.security.dto.UserResponseDTO;
import br.com.example.montadora.security.entities.Concessionaria;
import br.com.example.montadora.security.entities.User;
import br.com.example.montadora.security.repositories.ConcessionariaRepository;
import br.com.example.montadora.security.repositories.UserRepository;

@Service
public class UserServices {

	@Autowired
	UserRepository userRepository;

	@Autowired
	ConcessionariaRepository concessionariaRepository;

	@Autowired
	PasswordEncoder encoder;

	public List<UserResponseDTO> listarUsuarios() {
		List<User> users = userRepository.findAll();
		return users.stream().map(user -> new UserResponseDTO(user.getUsername(), user.getEmail()))
				.collect(Collectors.toList());
	}

	public void cadastrarUsuario(String username, String email, String password) {
		User newUser = new User();
		newUser.setUsername(username);
		newUser.setEmail(email);
		newUser.setPassword(password);

		Concessionaria concessionaria = concessionariaRepository.buscarConceUnica();
		newUser.setFkConcessionaria(concessionaria);

		userRepository.save(newUser);
	}

	public boolean deletarUsuario(Integer id) {
		if (userRepository.existsById(id)) {
			userRepository.deleteById(id);
			return true;
		} else {
			return false;
		}
	}

	public User atualizarUsuario(Integer id, UserRequestDTO userRequestDTO) {
		User user = userRepository.findById(id).orElse(null);
		if (userRequestDTO.getUsername() != null) {
			user.setUsername(userRequestDTO.getUsername());
		}
		if (userRequestDTO.getEmail() != null) {
			user.setEmail(userRequestDTO.getEmail());
		}
		if (userRequestDTO.getPassword() != null) {
			user.setPassword(encoder.encode(userRequestDTO.getPassword()));
		}

		return userRepository.save(user);

	}
}
