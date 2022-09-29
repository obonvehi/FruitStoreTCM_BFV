package com.tcm.Fruites.application;



import java.util.HashSet;

import com.tcm.Fruites.application.dto.StoreDTO;
import com.tcm.Fruites.application.dto.SupplierDTO;
import com.tcm.Fruites.application.dto.UserDTO;
import com.tcm.Fruites.domain.Box;
import com.tcm.Fruites.domain.Fruit;
import com.tcm.Fruites.domain.Store;
import com.tcm.Fruites.domain.users.Supplier;
import com.tcm.Fruites.domain.users.User;
import com.tcm.Fruites.persistence.StoreRepository;
import com.tcm.Fruites.persistence.SupplierRepository;
import com.tcm.Fruites.persistence.UserRepository;
import com.tcm.Fruites.utilities.Type;

public class Proves {
	
	public static void main(String[] args) {
		/*
		//Store//
		try {
			
			BoxController boxController = new BoxController();
			UserController userController = new UserController();
			StoreController storeController = new StoreController();
			SupplierController supplierController = new SupplierController();
			
			SupplierDTO sDTO = new SupplierDTO(new Supplier("fruitaReus"));
			supplierController.addSupplier(sDTO);
			for(Supplier supplier: SupplierRepository.getAllSuppliers()) {
				storeController.addStore(new StoreDTO(new Store("casaAtmetller")),new SupplierDTO(supplier));
				userController.addUser(new UserDTO(new User("oriac@gmail.com","labPart2")));
			}
			
			Store storeTest=null;
			for(Store store: StoreRepository.getAllStores()) {
				storeTest=store;
				for(Supplier supplier: SupplierRepository.getAllSuppliers()) {
					supplier.addStore(store.getID());
				}
			}
			User userTest = null;
			for(User user: UserRepository.getAllUsers()) {
				userTest = user;
			}
			boxController.createBox(storeTest.getID(), userTest.getID(), new HashSet<Type>());
			
			for (Box box: userTest.getHistory()) {
				System.out.println("NOOOOU BOX");
				for (Fruit fruit:box.getAllFruits()) {
					System.out.println(fruit.toString());
				}
			}
			new WeeklyReportService();
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		*/
	}
	
	
}