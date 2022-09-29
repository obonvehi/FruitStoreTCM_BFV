package com.tcm.Fruites.domain;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import com.tcm.Fruites.application.dto.StoreDTO;
import com.tcm.Fruites.utilities.Category;
import com.tcm.Fruites.utilities.Type;
import com.tcm.Fruites.utilities.exceptions.InvalidParamException;
import com.tcm.Fruites.utilities.exceptions.NotFoundException;

public class Store {
	
	private String id;
	private String name;

	private final static int MAX_CAPACITY = 15;  
	private HashMap<Category, LinkedList<Fruit>> storedFruit;

	public Store(String name) {
		this.name=name;
		createId();
		initializeMap();
	}
	
	public Store(StoreDTO storeDTO) throws InvalidParamException {
		this.name = storeDTO.getName();
		createID();
		initializeMap();
	}

	public HashSet<Type> getStock() throws NotFoundException {
		removeExpiredFruit();
		if (this.isEmpty())
			throw new NotFoundException("Don't have fruits in stock");
		HashSet<Type> fruitsInStock = new HashSet<Type>();
		for (LinkedList<Fruit> fruitList : this.storedFruit.values()) {
			for (Fruit fruit : fruitList) {
				fruitsInStock.add(fruit.getType());
			}
		}
		return fruitsInStock;
	}
	
	public LinkedList<Fruit> getRipeFruits() throws Exception {
		removeExpiredFruit();
		LinkedList<Fruit> ripeFruits = new LinkedList<Fruit>();
		for (Category category : Category.values()) {
			for (Fruit fruit : this.storedFruit.get(category)) {
				if(fruit.isNearExpiration()) 
					ripeFruits.add(fruit);
			}
		}
		if (ripeFruits.size()==0)
			throw new NotFoundException("No ripe fruits in stock");
		return sortFruits(ripeFruits);
	}
	
	public LinkedList<Fruit> getCategoryFruits(Category category) throws Exception {
		removeExpiredFruit();
		if (this.isEmpty(category))
			throw new NotFoundException("That category isn't in stock");
		return this.storedFruit.get(category);		
	}
	
	public LinkedList<Fruit> getRandomFruits() throws Exception {
		removeExpiredFruit();
		if (this.isEmpty())
			throw new NotFoundException("Don't have fruits in stock");
		LinkedList<Fruit> randomFruits = new LinkedList<Fruit>();
		for(LinkedList<Fruit> FruitList: this.storedFruit.values()) {
			randomFruits.addAll(FruitList);
		}
		return sortFruits(randomFruits);		
	}
	
	public Fruit getFruit(Fruit fruit) throws Exception {
		removeExpiredFruit();
		for (Fruit f : this.storedFruit.get(fruit.getType().getCategory()))
			if (f.equals(fruit))
				return f;
		throw new NotFoundException("Don't have this fruit in stock");
		
	}
	
	public LinkedList<Fruit> getAllFruits(){
		removeExpiredFruit();
		LinkedList<Fruit> result= new LinkedList<Fruit>();
		for(Category category:this.storedFruit.keySet()) {
			result.addAll(this.storedFruit.get(category));
		}
		return result;
	}
	
	public Fruit getFruitById(String id) throws Exception {
		removeExpiredFruit();
		for(Category key: this.storedFruit.keySet())
		for (Fruit f : this.storedFruit.get(key))
			if (f.getId().equals(id))
				return f;
		throw new NotFoundException("Don't have this fruit in stock");
	}
	
	public Set<Fruit> getAllFruits(Set<Type> fruitTypeLlist) throws Exception {
		HashSet<Fruit> fruits = new HashSet<Fruit>();
		for(Type fruitType: fruitTypeLlist) 
			fruits.add(getFruitByType(fruitType));
		return fruits;
	}
	public Fruit getFruitByType(Type type) throws Exception {
		removeExpiredFruit();
		for (Fruit f : this.storedFruit.get(type.getCategory()))
			if (f.getType().equals(type))
				return f;
		throw new NotFoundException("Don't have this fruit in stock");
		
	}
	
	private void isFull(Fruit fruit) throws Exception {
		int count=0;
		Category category = fruit.getType().getCategory();
		
		for(Fruit f: this.storedFruit.get(category)) {
			if(f.getType().equals(fruit.getType())) {
				count++;
			}
		}
		if(count>=MAX_CAPACITY) {
			throw new Exception ("The store can't have more "+ fruit.toString());
		}
	}
	
	
	public String getID() {
		return id;
	}
	
	public boolean isEmpty() {
		for (Category category : storedFruit.keySet()) {
			if (!isEmpty(category))
				return false;
		}
		return true;
	}
	
	public boolean isEmpty(Category category) {
			return (this.storedFruit.get(category).size() == 0);
	}

	public boolean removeFruit(Fruit fruit) throws InvalidParamException {
		if(fruit==null)
			throw new InvalidParamException("The fruit to remove is invallid");
		return this.storedFruit.get(fruit.getType().getCategory()).remove(fruit);
	}
	
	public void removeAllFruit(LinkedList<Fruit> fruitList) throws InvalidParamException {
		if(fruitList==null)
			throw new InvalidParamException("The list to remove is invallid");
		for(Fruit fruit: fruitList)
			removeFruit(fruit);
	}
	
	public boolean removeFruitById(String fruitId) throws Exception {
		Fruit fruit=this.getFruitById(fruitId);
		if(fruit==null)
			throw new InvalidParamException("The fruit to remove is invallid");
		return this.storedFruit.get(fruit.getType().getCategory()).remove(fruit);
	}
	public HashMap<Type,Integer> getFruitToFill() throws Exception {
		HashMap<Type,Integer> orderFruit = new HashMap<Type,Integer>();
		for (Type fruitType : Type.values()) {
			orderFruit.put(fruitType, MAX_CAPACITY);
			for (Fruit fruit : this.storedFruit.get(fruitType.getCategory())) {
				if (fruit.getType().equals(fruitType)) 
					orderFruit.put(fruitType,orderFruit.get(fruitType)-1);
			}
		}
		return  orderFruit;
	}

	public void addOrder(Order order) throws Exception  {
		for (Fruit fruit : order.getFruits()) {
			addFruit(fruit);
		}
	}
	private boolean addFruit(Fruit fruit) throws Exception {
		if(fruit==null) {
			throw new Exception ("No valid fruit");
		}
		this.isFull(fruit);
		return this.storedFruit.get(fruit.getType().getCategory()).add(fruit);
	}
	
	private void createId() {
		this.id = "Store-"+UUID.randomUUID().toString();
	}

	private void initializeMap() {
		this.storedFruit = new HashMap<Category, LinkedList<Fruit>>();
		for (Category category : Category.values()) {
			this.storedFruit.put(category, new LinkedList<Fruit>());
		}
	}
	
	private LinkedList<Fruit> sortFruits(LinkedList<Fruit> fruitList) {
		Collections.sort(fruitList, new Comparator<Fruit>() {
		    public int compare(Fruit fruit1, Fruit fruit2) {
		        return fruit1.getExpirationDate().compareTo(fruit2.getExpirationDate());
		    }
		});
		return  fruitList;
	}

	private void removeExpiredFruit() {
		for (Category fruitType : Category.values()) {
			Iterator<Fruit> it = this.storedFruit.get(fruitType).iterator();
			while (it.hasNext()) {
				if (it.next().isExpired()) {
					it.remove();
				} else
					break;
			}
		}
	}

	public void setName(String newName) {
		this.name = newName;
		
	}
	
	public String getName() {
		return this.name;
	}
	
	private void createID() {
		this.id = "Store-"+name+"-"+UUID.randomUUID().toString();
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Store))
			return false;
		Store other = (Store) obj;
		return Objects.equals(id, other.id);
	}
	
	
}
	
	
