package com.tcm.Fruites.api;

import java.util.Set;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tcm.Fruites.application.SupplierController;
import com.tcm.Fruites.application.dto.OrderDTO;
import com.tcm.Fruites.application.dto.SupplierDTO;
import com.tcm.Fruites.domain.Order;
import com.tcm.Fruites.domain.Store;
import com.tcm.Fruites.domain.users.Supplier;
import com.tcm.Fruites.persistence.SupplierRepository;
import com.tcm.Fruites.persistence.UserRepository;
import com.tcm.Fruites.utilities.exceptions.InvalidParamException;
import com.tcm.Fruites.utilities.exceptions.NotFoundException;

@RestController
public class SupplierRestController {	
	
	@PostMapping("/supplier")
	public SupplierDTO createSupplier(@RequestBody SupplierDTO supplierDTO) throws Exception{ 
		return new SupplierController().addSupplier(supplierDTO);
	}

	@GetMapping("/supplier/{supplierId}")
	public SupplierDTO getUser(@PathVariable String supplierId) throws Exception{ 
		return new SupplierController().getSupplier(supplierId);
	}
	
	@GetMapping("/supplier")
	public Set<SupplierDTO> getAllUsers() throws Exception { 
		return new SupplierController().getAllSuppliers();
	}
	
	@DeleteMapping("/supplier/{supplierId}")
	public SupplierDTO deleteSupplier(@PathVariable String supplierId) throws Exception {
		return new SupplierController().deleteSupplier(supplierId);
	}
	@DeleteMapping("/supplier")
	public Set<SupplierDTO> deleteAllUsers() {
		return new SupplierController().deleteAllSuppliers();
	}
	
	@PutMapping("/supplier/{supplierId}")
	public SupplierDTO updateUser(@PathVariable String supplierID, @RequestBody SupplierDTO supplierDTO) throws Exception {
		return new SupplierController().updateSupplier(supplierID, supplierDTO);
	}
	@GetMapping("/supplier/{supplierId}/order")
	public Set<OrderDTO> getOrders(@PathVariable String supplierID) throws Exception {
		return SupplierRepository.getAllOrders(supplierID);
	}
}
