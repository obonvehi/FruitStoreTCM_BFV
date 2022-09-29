package com.tcm.Fruites.application;

import java.util.HashSet;
import java.util.Set;

import com.tcm.Fruites.application.dto.UserDTO;
import com.tcm.Fruites.domain.users.User;
import com.tcm.Fruites.persistence.UserRepository;
import com.tcm.Fruites.utilities.exceptions.InvalidParamException;

public class UserController {

	
	public  UserDTO getUser(String userID) throws Exception {
		return new UserDTO(UserRepository.getUser(userID));
	}
	
	public Set<UserDTO> getAllUsers() throws Exception{
		Set<UserDTO> result= new HashSet<UserDTO>();
		for(User user: UserRepository.getAllUsers()) {
			result.add(new UserDTO(user));
		}
		return result;
	}
	
	public UserDTO removeUser(String userID) throws Exception {
		return UserRepository.removeUser(userID);
	}
	
	public Set<UserDTO> removeAllUsers() throws Exception {
		Set<UserDTO> usersDTO = new HashSet<UserDTO>();
		for(User user: UserRepository.getAllUsers()) 
			usersDTO.add(new UserDTO(user));
		UserRepository.removeAllUsers();
		return usersDTO;
	}
		
	public UserDTO addUser(UserDTO userDTO) throws Exception {
		return UserRepository.addUser(new User(userDTO));
	}

	public UserDTO updateUser(UserDTO userDTO, String userId) throws Exception {
		User user = UserRepository.getUser(userId);
		user.setUsername(userDTO.getUsername());
		user.setPassword(userDTO.getPassword());
		return new UserDTO(user);
	}
}
