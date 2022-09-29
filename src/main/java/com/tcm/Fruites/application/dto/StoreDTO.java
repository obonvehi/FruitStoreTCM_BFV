package com.tcm.Fruites.application.dto;

import java.util.LinkedList;

import com.tcm.Fruites.domain.Fruit;
import com.tcm.Fruites.domain.Store;
import com.tcm.Fruites.utilities.exceptions.InvalidParamException;

public class StoreDTO {

	private String storeID;
	private String name;
	private LinkedList<FruitDTO> fruits;
	
	public StoreDTO() {
	}
	
	public StoreDTO(Store store) throws Exception {
		if(store==null) throw new InvalidParamException();
		this.storeID=store.getID();
		this.name=store.getName();
		this.setFruits(store.getAllFruits());
	}

	public String getID() throws InvalidParamException {
		if(this.storeID==null||this.storeID.equals("")) throw new InvalidParamException();
		return storeID;
	}
	public String getName() throws InvalidParamException {
		if(this.name==null||this.name.equals("")) throw new InvalidParamException();
		return this.name;
	}
	private void setFruits(LinkedList<Fruit> fruits) throws Exception {
		this.fruits = new LinkedList<FruitDTO>();
		for(Fruit fruit: fruits) {
			this.fruits.add(new FruitDTO(fruit));
		}
	}
}
