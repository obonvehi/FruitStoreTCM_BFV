package com.tcm.Fruites.domain.users;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import com.tcm.Fruites.application.dto.SupplierDTO;
import com.tcm.Fruites.domain.Order;
import com.tcm.Fruites.domain.Store;
import com.tcm.Fruites.persistence.StoreRepository;
import com.tcm.Fruites.utilities.Type;
import com.tcm.Fruites.utilities.exceptions.InvalidParamException;
import com.tcm.Fruites.utilities.exceptions.NotFoundException;

public class Supplier {

	public final int MAX_SUPPLY = 3;
	private String supplierID;
	private String name;
	private Set<Order> orders;
	private Set<Store> stores;
	
	public Supplier(String name) throws InvalidParamException {
		if (name == null) {
			throw new InvalidParamException("Invalid supplier name");
		}
		this.supplierID = createID();
		this.name = name;
		this.orders = new HashSet<Order>();
		this.stores = new HashSet<Store>();
	}
	
	public Supplier(SupplierDTO supplierDTO) throws InvalidParamException {
		try {
		this.name=supplierDTO.getName();
		}catch(Exception e) {
			System.out.println(e.getMessage());
			this.name="lluis xulo";
		}
		this.supplierID=createID();
		this.orders = new HashSet<Order>();
		this.stores = new HashSet<Store>();
	}

	public Order newOrder(HashMap<Type,Integer> orderFruits) throws Exception {

		//crea
		if (orderFruits == null) {
			throw new InvalidParamException("The order is empty");
		}
		Order order = new Order();
		order.fillUpFruitOrder(makeOrder(orderFruits));
		this.orders.add(order);
		return order;
	}
		
	private ArrayList<Type> makeOrder(HashMap<Type,Integer> orderFruit)  {
		ArrayList<Type> orderList = new ArrayList<Type>();
		for (Type fruitType : Type.values()) {
			for(int i=0;i<orderFruit.get(fruitType)&&i<MAX_SUPPLY;i++) {
				orderList.add(fruitType);
			}
		}
		return orderList;
	}
	public void addOrder(Order order) {
		this.orders.add(order);
	}

	public Order getOrder(String id) throws NotFoundException {
		for (Order order : this.orders)
			if (order.getId().equals(id))
				return order;
		throw new NotFoundException();
	}

	public Set<Order> getAllOrders() {
		return this.orders;
	}

	public String getID() {
		return this.supplierID;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name=name;
	}
	
	private String createID() {
		return "Supplier-"+UUID.randomUUID().toString();
	}

	public void remove(String orderID) throws NotFoundException {
		this.orders.remove(this.getOrder(orderID));
	}
	public void removeAll() throws NotFoundException {
		this.orders = new HashSet<Order>();
	}

	public void addStore(String storeID) throws NotFoundException {
		 stores.add(StoreRepository.getStore(storeID));
		 
	}
	
	public void removeStore(String storeID) throws NotFoundException {
		 stores.remove(StoreRepository.getStore(storeID));
	}
	
	public Set<Store> getStores() {
		return stores;
	}

	public void setStores(Set<Store> stores) {
		this.stores = stores;
	}
}
