package com.tcm.Fruites.application.dto;

import java.util.HashSet;
import java.util.Set;

import com.tcm.Fruites.domain.Box;
import com.tcm.Fruites.domain.Fruit;
import com.tcm.Fruites.utilities.exceptions.InvalidParamException;


public class BoxDTO {

	private String boxID;
	private String purchaseDate;
	private Set<FruitDTO> basketFruits;
	
	public BoxDTO() {
		
	}
	
	public BoxDTO(Box box) throws Exception {
		if(box==null) {
			throw new InvalidParamException();
		}
		
		this.boxID=box.getId();
		this.purchaseDate=box.getPurchaseDate().toString();
		this.setBasketFruits(box.getAllFruits());
		
	} 


	public String getId() throws InvalidParamException {
		if(this.boxID==null||this.boxID.equals("")) throw new InvalidParamException();
		return this.boxID;
	}

	public String getPurchaseDate() throws InvalidParamException {
		if(this.purchaseDate==null||this.purchaseDate.equals("")) throw new InvalidParamException();
		return this.purchaseDate;
	}


	public Set<FruitDTO> getAllFruits() {
		return basketFruits;
	}
	private void setBasketFruits(Set<Fruit> basketFruits) throws Exception {
		this.basketFruits= new HashSet<FruitDTO>();
		for(Fruit fruit: basketFruits) {
			this.basketFruits.add(new FruitDTO(fruit));
		}
	}
}
