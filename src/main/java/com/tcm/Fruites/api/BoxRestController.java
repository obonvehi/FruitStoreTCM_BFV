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

import com.tcm.Fruites.application.BoxController;
import com.tcm.Fruites.application.StoreController;
import com.tcm.Fruites.application.SupplierController;
import com.tcm.Fruites.application.UserController;
import com.tcm.Fruites.application.dto.BoxDTO;
import com.tcm.Fruites.application.dto.FruitDTO;
import com.tcm.Fruites.application.dto.StoreDTO;
import com.tcm.Fruites.application.dto.SupplierDTO;
import com.tcm.Fruites.application.dto.UserDTO;
import com.tcm.Fruites.domain.Box;
import com.tcm.Fruites.domain.Fruit;
import com.tcm.Fruites.domain.Order;
import com.tcm.Fruites.domain.Store;
import com.tcm.Fruites.domain.users.Supplier;
import com.tcm.Fruites.domain.users.User;
import com.tcm.Fruites.persistence.StoreRepository;
import com.tcm.Fruites.persistence.SupplierRepository;
import com.tcm.Fruites.persistence.UserRepository;
import com.tcm.Fruites.utilities.Category;
import com.tcm.Fruites.utilities.Type;

@RestController
public class BoxRestController {
	
	@GetMapping("/user/{userId}/box")
	public Set<BoxDTO> getAllBox(@PathVariable String userId) throws Exception {
		return  new BoxController().getAllBoxes(userId);
	}
	@GetMapping("/user/{userId}/box/{boxId}")//probar
	public BoxDTO getBox(@PathVariable String userId,@PathVariable String boxId) throws Exception {
		return new BoxController().getBox(userId, boxId);
	}
	
	@DeleteMapping("/user/{userId}/box")//probar
	public List<BoxDTO> deleteAllBox(@PathVariable String userId) throws Exception {
		return new BoxController().deleteAllBoxs(userId);
	}

	@DeleteMapping("/user/{userId}/box/{boxId}")
	public BoxDTO deleteBox(@PathVariable String userId,@PathVariable String boxId) throws Exception {
		return new BoxController().deleteBox(userId,boxId);
	}
	
	@PutMapping("/user{userId}/box/{boxId}")
	public BoxDTO updateBox(@PathVariable String userId, @RequestBody BoxDTO boxDTO) throws Exception {
		return new BoxController().updateBox(userId,boxDTO);
	}
	@PostMapping("/user/{userId}/store/{storeId}/box")
	public BoxDTO fillBox(@PathVariable String userId,@PathVariable String storeId, @RequestBody BoxDTO boxDTO) throws Exception {
		return new BoxController().createBox(storeId, userId, boxDTO);
	}
}
