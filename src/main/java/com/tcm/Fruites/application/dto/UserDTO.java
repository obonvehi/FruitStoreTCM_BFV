package com.tcm.Fruites.application.dto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.tcm.Fruites.domain.Box;
import com.tcm.Fruites.domain.users.User;
import com.tcm.Fruites.utilities.exceptions.InvalidParamException;

public class UserDTO {

	private String userID;
	private String username;
	private String password;
	private Set<BoxDTO> history;
	
	public UserDTO() {
		
	}
	public UserDTO(User user) throws Exception {
		if(user==null) throw new InvalidParamException();
		this.userID = user.getID();
		this.username=user.getUsername();
		this.password=user.getPassword();		
		setHistory(user.getHistory());
	}

	public String getUsername() throws InvalidParamException {
		User.validUsername(username);
		return this.username;
	}

	public String getPassword() throws InvalidParamException {
		User.validPassword(password);
		return this.password;
	}

	public String getID() throws InvalidParamException {
		if(this.userID==null||this.userID.equals("")) throw new InvalidParamException();
		return this.userID;
	}

	public Set<BoxDTO> getHistory() {
		return this.history;
	}
	
	private void setHistory(List<Box> history) throws Exception {
		this.history= new HashSet<BoxDTO>();
		for(Box box: history) {
			this.history.add(new BoxDTO(box));
		}
	}
}