package com.tcm.Fruites.application;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import com.tcm.Fruites.application.dto.FruitDTO;
import com.tcm.Fruites.application.dto.StoreDTO;
import com.tcm.Fruites.application.dto.SupplierDTO;
import com.tcm.Fruites.domain.Fruit;
import com.tcm.Fruites.domain.Store;
import com.tcm.Fruites.persistence.StoreRepository;
import com.tcm.Fruites.persistence.SupplierRepository;
import com.tcm.Fruites.persistence.UserRepository;
import com.tcm.Fruites.utilities.Type;
import com.tcm.Fruites.utilities.exceptions.NotFoundException;

public class StoreController {

	public StoreDTO getStore (String StoreID) throws Exception {
		return  new StoreDTO(StoreRepository.getStore(StoreID));
	}
	
	public Set<StoreDTO> getAllStores() throws Exception{
		Set<StoreDTO> result= new HashSet<StoreDTO>();
		for(Store store: StoreRepository.getAllStores()) {
			result.add(new StoreDTO(store));
		}
		return result;
	}
	
	public StoreDTO deleteStore(String storeID) throws Exception {
		return StoreRepository.deleteStore(storeID);
	}
	
	public void deleteAllStore() {
		StoreRepository.deleteAllStores();
	}
	
	public StoreDTO addStore(StoreDTO storeDTO, String supplierID) throws Exception {
		Store store = new Store(storeDTO);
		StoreRepository.addStore(store);
		store.addOrder(SupplierRepository.getSupplier(supplierID).newOrder(store.getFruitToFill()));
		return new StoreDTO(store);
	}
	
	public LinkedList<FruitDTO> getAllStock(String storeID) throws Exception {
		Store store = StoreRepository.getStore(storeID);
		LinkedList<FruitDTO> fruits = new LinkedList<FruitDTO>();
		for(Fruit fruit: store.getAllFruits()) {
			fruits.add(new FruitDTO(fruit));
		}
		return fruits;
	}
	
	public HashSet<Type> getAllTypesAvailable(String storeID) throws NotFoundException {
		Store store = StoreRepository.getStore(storeID);
		return store.getStock();
	}

	public StoreDTO updateStore(String storeId, StoreDTO storeDTO) throws Exception {
		Store store = StoreRepository.getStore(storeId);
		store.setName(storeDTO.getName());
		return new StoreDTO(store);
	}
}

