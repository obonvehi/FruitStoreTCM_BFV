package com.tcm.Fruites.domain;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

import com.tcm.Fruites.application.dto.BoxDTO;
import com.tcm.Fruites.application.dto.FruitDTO;
import com.tcm.Fruites.utilities.exceptions.ExceededLimitException;
import com.tcm.Fruites.utilities.exceptions.InvalidParamException;
import com.tcm.Fruites.utilities.exceptions.NotFoundException;

public class Box {

	public static final int BASKETSIZE = 8;
	private String boxID;
	private Calendar purchaseDate;
	private Set<Fruit> basketFruits;
	
	public Box() {
		
	}
	
	public Box(Set<Fruit> initialFruits) {
		this.basketFruits = new HashSet<Fruit>();
		this.basketFruits.addAll(initialFruits);
		this.purchaseDate = Calendar.getInstance();
		this.createID();
	}
	
	public Box(BoxDTO boxDTO) throws InvalidParamException {
		this.basketFruits = new HashSet<Fruit>();
		for(FruitDTO fruitDTO: boxDTO.getAllFruits()) {
			this.basketFruits.add(new Fruit(fruitDTO));
		}
		this.purchaseDate = Calendar.getInstance();
		this.createID();
	}

	public void addFruit(Fruit fruitToAdd) throws Exception {
		if(fruitToAdd==null) 
			throw new InvalidParamException("The fruit to add is not vallid");
		if (isFull())
			throw new ExceededLimitException("The box it's already full");
		try {
		if (isFruitInBox(fruitToAdd)||! this.basketFruits.add(fruitToAdd))
			throw new InvalidParamException("Fruit could not be added");
		}catch(Exception e) {};
		}

	public  boolean isFruitInBox(Fruit testFruit) {
		for (Fruit fruit : this.basketFruits) {
			if (fruit.getType().equals(testFruit.getType())) {
				return true;
			}
		}
		return false;
	}
	public boolean isFull() {
		return this.basketFruits.size()==BASKETSIZE;
	}

	public String getId() {
		return this.boxID;
	}

	public Calendar getPurchaseDate() {
		return this.purchaseDate;
	}

	private void createID() {
		this.boxID = "FruitBox-"+UUID.randomUUID().toString();
	}
	
	public Set<Fruit> getAllFruits(){
		return this.basketFruits;
	}
	
	public Fruit getFruit(String fruitId) throws Exception {

		for(Fruit result: basketFruits) {
			if(result.getId().equals(fruitId))
				return result;
		}
		throw new NotFoundException("FruitType not found in the box");
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(boxID);
	}

	@Override
	public boolean equals(Object obj) {
		
		if (getClass() != obj.getClass())
			return false;
		Box other = (Box) obj;
		if (boxID == null) {
			if (other.boxID != null)
				return false;
		} else if (!boxID.equals(other.boxID))
			return false;
		return true;
	}
}
