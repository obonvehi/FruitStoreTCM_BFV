package com.tcm.Fruites.application.dto;


import com.tcm.Fruites.domain.Fruit;
import com.tcm.Fruites.utilities.Type;
import com.tcm.Fruites.utilities.exceptions.InvalidParamException;

public class FruitDTO {
	
	private String fruitID;
	private String type;
	
	public FruitDTO() {
		
	}
 
	public FruitDTO(Fruit fruit) throws Exception {
		if(fruit==null) throw new InvalidParamException();
		
		this.fruitID=fruit.getId();
		this.type=fruit.getType().toString();
	}

	public String getId() throws InvalidParamException {
		if(fruitID==null||fruitID.equals("")) throw new InvalidParamException();
		return fruitID;
	}

	public String getType() throws InvalidParamException {
		if(this.type==null||this.type.equals("")) throw new InvalidParamException();
		return this.type;
	}

	
}
