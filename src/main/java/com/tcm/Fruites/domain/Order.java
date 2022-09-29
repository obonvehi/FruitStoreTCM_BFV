package com.tcm.Fruites.domain;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import com.tcm.Fruites.utilities.Type;

public class Order {

	private List<Fruit> fruits;
	private Calendar date;
	private String id;

	public Order() {
		this.fruits = new ArrayList<Fruit>();
		this.date = Calendar.getInstance();
		this.createID();
	}

	public void fillUpFruitOrder(ArrayList<Type> fruitName) throws Exception {
		for (Type fruit : fruitName) {
			this.fruits.add(new Fruit(fruit));
		}
	}
	

	public Calendar getDate() {
		return this.date;
	}
	
	public List<Fruit> getFruits() {
		return this.fruits;
	}

	public String getId() {
		return this.id;
	}

	private void createID() {
		this.id = "Order-"+UUID.randomUUID().toString();
	}
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
