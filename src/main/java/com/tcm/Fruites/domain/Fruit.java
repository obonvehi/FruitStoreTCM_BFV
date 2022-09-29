package com.tcm.Fruites.domain;

import java.util.Calendar;
import java.util.UUID;

import com.tcm.Fruites.application.dto.FruitDTO;
import com.tcm.Fruites.utilities.Type;
import com.tcm.Fruites.utilities.exceptions.ExpiredException;
import com.tcm.Fruites.utilities.exceptions.InvalidParamException;

public class Fruit {

	private String fruitID;
	private Type type;
	private Calendar arrivalDate;
	private static final int DAYS_FOR_SOON_EXPIRATION = 5;
	
	public Fruit() {
		
	}
	
	public Fruit(Type type) throws InvalidParamException {
		if (type == null) {
			throw new InvalidParamException("Not able to create the fruit");
		}
		this.type = type;
		this.arrivalDate = Calendar.getInstance();
		this.createID();
	}

	public Fruit(FruitDTO fruitDTO) throws InvalidParamException {
		if (type == null) {
			throw new InvalidParamException("Not able to create the fruit");
		}
		this.type = Type.valueOf(fruitDTO.getType()); 
	}

	public Calendar getExpirationDate() {
		Calendar expirationDate = arrivalDate;
		expirationDate.add(Calendar.DATE, this.type.getCategory().getExpirationDay());
		return expirationDate;
	}

	public boolean isExpired() {
		return (Calendar.getInstance().after(getExpirationDate()));
	}

	public boolean isNearExpiration() throws ExpiredException {
		if (isExpired()) {
			throw new ExpiredException("The fruit is already expired");
		}
		Calendar nearExpirationDate = getExpirationDate();
		nearExpirationDate.add(Calendar.DATE, -DAYS_FOR_SOON_EXPIRATION);
		return nearExpirationDate.after(getExpirationDate());
	}

	public String getId() {
		return this.fruitID;
	}
	public Type getType() {
		return this.type;
	}

	public String toString() {
		return this.type.toString();
	}
	
	public int compareTo(Fruit fruit) {
		return this.fruitID.compareTo(fruit.fruitID);
	}
	
	@Override
	public int hashCode() {
		return type.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Fruit))
			return false;
		Fruit other = (Fruit) obj;
		return (this.compareTo(other) == 0);
	}

	private void createID() {
		this.fruitID = this.type.toString()+"-"+UUID.randomUUID().toString();
	}
}
