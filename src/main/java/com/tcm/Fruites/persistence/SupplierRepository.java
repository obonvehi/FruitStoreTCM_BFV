package com.tcm.Fruites.persistence;

import java.util.HashSet;
import java.util.Set;

import com.tcm.Fruites.application.dto.OrderDTO;
import com.tcm.Fruites.application.dto.SupplierDTO;
import com.tcm.Fruites.domain.Order;
import com.tcm.Fruites.domain.users.Supplier;
import com.tcm.Fruites.utilities.exceptions.InvalidParamException;
import com.tcm.Fruites.utilities.exceptions.NotFoundException;

public class SupplierRepository {

	private static Set<Supplier> suppliers= new HashSet<Supplier>();
	
	public static Supplier getSupplier(String id) throws Exception {
		for(Supplier supplier: suppliers)
			if(supplier.getID().equals(id)) return supplier;
		throw new NotFoundException();
	}
	
	public static Set<Supplier> getAllSuppliers() {
		return suppliers;
	}
	
	public static SupplierDTO deleteSuppliers(String id) throws Exception {
		SupplierDTO sDTO = new SupplierDTO(getSupplier(id));
		suppliers.remove(getSupplier(id));
		return sDTO;
	}
	
	public static Set<SupplierDTO> deleteAllSuppliers()  {
		Set<SupplierDTO> supp = new HashSet<SupplierDTO>();
		try {
		for(Supplier supplier: suppliers) 
			supp.add(new SupplierDTO(supplier));
		} catch(Exception e){}
		suppliers= new HashSet<Supplier>();
		return supp;
	}
	
	public static SupplierDTO addSupplier(Supplier supplier) throws Exception {
		if (!suppliers.add(supplier)) 
			throw new InvalidParamException("The username is in the database");
		return new SupplierDTO(supplier);
	}
	
	public static Order getOrder(String SupplierID, String OrderID) throws Exception {
		return getSupplier(SupplierID).getOrder(OrderID);
	}
	
	public static Set<OrderDTO> getAllOrders(String SupplierID) throws Exception{
		Set<OrderDTO> ordersDTO = new HashSet<OrderDTO>();
		for(Order order: getSupplier(SupplierID).getAllOrders())
			ordersDTO.add(new OrderDTO(order));
		return ordersDTO;
	}	
	
	public static Set<SupplierDTO> getStoreSuppliers(String storeID) throws Exception {
		HashSet<SupplierDTO> supp= new HashSet<SupplierDTO>();
		for(Supplier supplier: suppliers)
			if(supplier.getStores().contains(StoreRepository.getStore(storeID))) 
				supp.add(new SupplierDTO(supplier));
		return supp;		
	}
}