package com.tcm.Fruites.api;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tcm.Fruites.application.StoreController;
import com.tcm.Fruites.application.dto.FruitDTO;
import com.tcm.Fruites.application.dto.StoreDTO;
import com.tcm.Fruites.application.dto.SupplierDTO;
import com.tcm.Fruites.domain.Fruit;
import com.tcm.Fruites.domain.Store;
import com.tcm.Fruites.domain.users.Supplier;
import com.tcm.Fruites.persistence.StoreRepository;
import com.tcm.Fruites.persistence.SupplierRepository;
import com.tcm.Fruites.persistence.UserRepository;
import com.tcm.Fruites.utilities.Type;
import com.tcm.Fruites.utilities.exceptions.NotFoundException;

@RestController
public class StoreRestController {
	
	@PostMapping("{supplierID}/store")
	public StoreDTO createStore(@RequestBody StoreDTO storeDTO,@PathVariable String supplierID) throws Exception{ 
		return new StoreController().addStore(storeDTO,supplierID);
	}
	
	@GetMapping("/store/{storeId}")
	public StoreDTO getStore(@PathVariable String storeId) throws Exception{ 
		return new StoreController().getStore(storeId);
	}
	
	@GetMapping("/store")
	public Set<StoreDTO> getAllStores() throws Exception{ 
		return new StoreController().getAllStores();
	}
	
	@DeleteMapping("/store/{storeId}")
	public StoreDTO deleteStore(@PathVariable String storeId) throws Exception {
		return new StoreController().deleteStore(storeId);
	}
	
	@DeleteMapping("/store")
	public Set<StoreDTO> deleteAllStores() throws Exception {
		return new StoreController().getAllStores();
	}
	
	@PutMapping("/store/{storeId}")
	public StoreDTO updateStore(@PathVariable String storeId, @RequestBody StoreDTO storeDTO) throws Exception {
		return new StoreController().updateStore(storeId, storeDTO);
	}
	
	@GetMapping("/store/{storeId}/fruits")
	public Set<Type> getStock(@PathVariable String storeId) throws Exception {
		Store store = StoreRepository.getStore(storeId);
		return store.getStock();
	}
	
	@GetMapping("/store/{storeId}/allFruits")
	public List<Fruit> getFruits(@PathVariable String storeId) throws Exception {
		Store store = StoreRepository.getStore(storeId);
		return store.getAllFruits();
	}
	
	@GetMapping("/store/{storeId}/supplier")
	public Set<SupplierDTO> getSuppliers(@PathVariable String storeId) throws Exception {
		return SupplierRepository.getStoreSuppliers(storeId);
	}	
}
