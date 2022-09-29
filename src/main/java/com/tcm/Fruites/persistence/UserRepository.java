package com.tcm.Fruites.persistence;

import java.util.HashSet;
import java.util.Set;

import com.tcm.Fruites.application.dto.UserDTO;
import com.tcm.Fruites.domain.users.User;
import com.tcm.Fruites.utilities.exceptions.InvalidParamException;

public class UserRepository {
	
	private static Set<User> allUsers= new HashSet<User>();
	
	public static User getUser(String userID) throws Exception {
		for(User user: allUsers) {
			if(user.getID().equals(userID)) return user;
		}
		throw new InvalidParamException("The username specified is not in the database");
	}
	
	public static Set<User> getAllUsers() throws Exception{
		return allUsers;
	}
	
	public static UserDTO addUser(User user) throws Exception {
		if (!allUsers.add(user)) 
			throw new InvalidParamException("The username is in the database");
		return new UserDTO(user);
	}
	
	public static UserDTO removeUser(String userID) throws Exception {
		UserDTO userDTO= new UserDTO(getUser(userID));
		allUsers.remove(getUser(userID));
		return userDTO;
	}
	
	public static void removeAllUsers() {
		allUsers = new HashSet<User>();
	}
}
