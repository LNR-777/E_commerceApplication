package com.example.demo.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.demo.dto.UserDTO;
import com.example.demo.model.Category;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public User save(UserDTO userDTO)
	{
		User user=new User(userDTO.getEmail(), passwordEncoder.encode(userDTO.getPassword()),userDTO.getFullName(),userDTO.getRole());
		/*User user=new User
			(userDTO.getEmail(),userDTO.getPassword(),userDTO.getFullName(),userDTO.getRole());*/
		
		return userRepository.save(user);
	}
	
	public List<User>dispUsers()
	{
		return userRepository.findAll();
	 
	}
	public void deleteUserById(int id)
	{
		userRepository.deleteById(id);
	}
}