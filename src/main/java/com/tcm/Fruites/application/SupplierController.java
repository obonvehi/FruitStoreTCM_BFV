package com.tcm.Fruites.application;

import java.util.HashSet;
import java.util.Set;

import com.tcm.Fruites.application.dto.OrderDTO;
import com.tcm.Fruites.application.dto.SupplierDTO;
import com.tcm.Fruites.domain.Order;
import com.tcm.Fruites.domain.Store;
import com.tcm.Fruites.domain.users.Supplier;
import com.tcm.Fruites.persistence.SupplierRepository;
import com.tcm.Fruites.persistence.UserRepository;
import com.tcm.Fruites.utilities.exceptions.NotFoundException;

public class SupplierController {	
	
	public static void supplyStore() throws Exception {
		
		Set<Supplier> suppliers = SupplierRepository.getAllSuppliers();
		
		for(Supplier supplier: suppliers) {
			for (Store store: supplier.getStores()) {
				store.addOrder(supplier.newOrder(store.getFruitToFill()));
			}
		}
	}
	
	public SupplierDTO getSupplier(String supplierID) throws Exception{
		return new SupplierDTO(SupplierRepository.getSupplier(supplierID));
	}
	
	public Set<SupplierDTO> getAllSuppliers() throws Exception{
		Set<SupplierDTO> result= new HashSet<SupplierDTO>();
		for(Supplier supplier: SupplierRepository.getAllSuppliers()) {
			result.add(new SupplierDTO(supplier));
		}
		return result;
	}
	public SupplierDTO deleteSupplier(String supplierID) throws Exception {
		return SupplierRepository.deleteSuppliers(supplierID);
	}
	
	public Set<SupplierDTO> deleteAllSuppliers() {
		return SupplierRepository.deleteAllSuppliers();
	}
	
	public SupplierDTO modifySupplier(SupplierDTO supplierDTO) throws Exception {	
		Supplier supplier = SupplierRepository.getSupplier(supplierDTO.getID());
		supplier.setName(supplierDTO.getName());
		return new SupplierDTO(supplier);
	}	
	
	public SupplierDTO addSupplier(SupplierDTO supplierDTO) throws Exception {
		return SupplierRepository.addSupplier(new Supplier(supplierDTO));
	}
	
	public OrderDTO getOrder(String SupplierID, String OrderID) throws Exception {
		return new OrderDTO (SupplierRepository.getOrder(SupplierID, OrderID));
	}
	
	public Set<OrderDTO> getAllOrder(String SupplierID) throws Exception{
		return  SupplierRepository.getAllOrders(SupplierID);
	}
	public void deleteOrder(String supplierID, String orderID) throws Exception {
		SupplierRepository.getSupplier(supplierID).remove(orderID);
	}
	
	public void deleteAllOrders(String supplierID) throws NotFoundException, Exception {
		SupplierRepository.getSupplier(supplierID).removeAll();
	}

	public SupplierDTO updateSupplier(String supplierID, SupplierDTO supplierDTO) throws Exception {
		Supplier s = SupplierRepository.getSupplier(supplierID);
		s.setName(supplierDTO.getName());
		return new SupplierDTO(s);
	}
}
