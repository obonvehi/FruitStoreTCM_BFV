package com.tcm.Fruites.domain.users;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

import com.tcm.Fruites.application.dto.BoxDTO;
import com.tcm.Fruites.application.dto.UserDTO;
import com.tcm.Fruites.domain.Box;
import com.tcm.Fruites.utilities.exceptions.InvalidParamException;
import com.tcm.Fruites.utilities.exceptions.NotFoundException;

public class User {
	
	public static int MIN_PASSWORD_SIZE = 8;
	private String userID;
	private String username;
	private String password;
	private List<Box> history;
	
	
	public User(UserDTO userDTO) throws Exception {
		if(userDTO==null) throw new InvalidParamException();
		this.username=userDTO.getUsername();
		this.password=userDTO.getPassword();
		this.history=new ArrayList<Box>();
		this.createID();
	}
	
	public User (String username, String password) throws Exception {
		this.username=username;
		this.password=password;
		this.history=new ArrayList<Box>();
		this.createID();
	}
	
	public static void validUsername(String username) throws InvalidParamException {
		if(username==null ||!username.contains("@")) 
			throw new InvalidParamException("Invallid username");
	}

	public static void validPassword(String password) throws InvalidParamException {
		if(password==null ||password.length()< MIN_PASSWORD_SIZE) 
			throw new InvalidParamException("Invallid password");
		if(password.equals(password.toUpperCase())) throw new InvalidParamException("Invallid password");
	}

	public void addBox(Box fruitBox) throws NotFoundException {
		if(fruitBox==null) {
			throw new NotFoundException("There's no fruitBox");
		}
		this.history.add(fruitBox);
	}
	
	public void logIn (String username, String password) throws InvalidParamException {
		if(!(this.username.equals(username)&&this.password.equals(password))) throw new InvalidParamException("The password is incorrect");
	}
	
	public String getUsername() {
		return this.username;
	}

	public String getPassword() {
		return password;
	}

	public Box getBox(String BoxID) throws InvalidParamException {
		for(Box box: this.history) {
			if(box.getId().equals(BoxID))
				return box;
		}
		throw new InvalidParamException("There's no box with that purchase date");
	}
	
	public List<Box> getHistory() {
		return this.history;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	public void deleteBox(String BoxID) throws InvalidParamException {
		Box box=getBox(BoxID);
		this.history.remove(box);
	}

	public BoxDTO removeBox(String boxID) throws Exception {
		BoxDTO boxDTO = new BoxDTO(getBox(boxID));
		this.history.remove(getBox(boxID));
		return boxDTO;
		
	}
	private void createID() {
		this.userID = ("User-"+UUID.randomUUID().toString());
	}

	public String getID() {
		return this.userID;
	}

	@Override
	public int hashCode() {
		return Objects.hash(userID);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj==null&&!(obj instanceof User))
			return false;
		User other = (User) obj;
		return Objects.equals(userID, other.userID);
	}

	public List<BoxDTO> removeAllBox() throws Exception {
		List<BoxDTO> boxDTOs = new ArrayList<BoxDTO>();
		for(Box box: getHistory()) {
			boxDTOs.add(new BoxDTO(box));
		}
		this.history=new ArrayList<Box>();
		return boxDTOs;
	}

	public BoxDTO updateBox(BoxDTO boxDTO) throws InvalidParamException {
		for(Box box:this.history) {
			if(box.getId().equals(boxDTO)) {
				box = new Box(boxDTO);
			}
		}
		return null;
	}
}
