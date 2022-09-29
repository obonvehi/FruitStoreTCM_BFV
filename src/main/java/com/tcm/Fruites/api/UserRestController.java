package com.tcm.Fruites.api;

import java.util.List;
import java.util.Set;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tcm.Fruites.application.UserController;
import com.tcm.Fruites.application.dto.UserDTO;
import com.tcm.Fruites.domain.users.User;

@RestController
public class UserRestController {

	@PostMapping("/user")
	public UserDTO createUser(@RequestBody UserDTO userDTO) throws Exception {
		return new UserController().addUser(userDTO);
	}
	
	@GetMapping("/user")
	public Set<UserDTO> getAllUsers() throws Exception {
		return new UserController().getAllUsers();
	}
	@GetMapping("/user/{userId}")
	public UserDTO getUser(@PathVariable String userId) throws Exception {
		return new UserController().getUser(userId);
	}
	
	@DeleteMapping("/user/{userId}")
	public UserDTO deleteUser(@PathVariable String userId) throws Exception {
		return new UserController().removeUser(userId);
	}

	@DeleteMapping("/user")
	public Set<UserDTO> deleteAllUsers() throws Exception {
		return new UserController().removeAllUsers();
	}
	
	@PutMapping("/user/{userId}")
	public UserDTO updateUser(@PathVariable String userId, @RequestBody UserDTO userDTO) throws Exception {
		return new UserController().updateUser(userDTO,userId);
	}
}
