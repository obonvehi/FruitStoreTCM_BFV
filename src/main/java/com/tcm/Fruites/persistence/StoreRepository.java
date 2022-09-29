package com.tcm.Fruites.persistence;

import java.util.HashSet;
import java.util.Set;

import com.tcm.Fruites.application.dto.StoreDTO;
import com.tcm.Fruites.domain.Store;
import com.tcm.Fruites.utilities.exceptions.InvalidParamException;
import com.tcm.Fruites.utilities.exceptions.NotFoundException;

public class StoreRepository {
	
	private static Set<Store> stores = new HashSet<Store>();
	
	public static Store getStore(String storeID) throws NotFoundException {
		for(Store store: stores)
			if(store.getID().equals(storeID)) return store;
		throw new NotFoundException();
	}
	
	public static Set<Store> getAllStores() {
		return stores;
	}
	public static void addStore(Store storeID) throws InvalidParamException {
		if (!stores.add(storeID)) 
			throw new InvalidParamException("The store is in the database");
	}
	
	public static void deleteSuppliers(String storeID) throws NotFoundException {
		stores.remove(getStore(storeID));
	}
	
	public static void deleteAllStores() {
		stores= new HashSet<Store>();
	}
	
	public static StoreDTO deleteStore(String storeID) throws Exception {//mirar si hem de posar-ho així
		Store store= getStore(storeID);
		if(store!=null) {
			stores.remove(store);
			return new StoreDTO(store);
		}
		throw new InvalidParamException("The store isn't in the database");
	}
}
