package com.tcm.Fruites.application;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import com.tcm.Fruites.application.dto.BoxDTO;
import com.tcm.Fruites.application.dto.FruitDTO;
import com.tcm.Fruites.domain.Box;
import com.tcm.Fruites.domain.Fruit;
import com.tcm.Fruites.domain.Store;
import com.tcm.Fruites.domain.users.User;
import com.tcm.Fruites.persistence.StoreRepository;
import com.tcm.Fruites.persistence.UserRepository;
import com.tcm.Fruites.utilities.Category;
import com.tcm.Fruites.utilities.Type;

public class BoxController {
	
	
	public BoxDTO getBox(String userID, String boxID) throws Exception {
		
		User user=UserRepository.getUser(userID);
		BoxDTO result=new BoxDTO(user.getBox(boxID));
		return result;
	}
	
	public Set<BoxDTO> getAllBoxes(String userID) throws Exception{
		
		HashSet<BoxDTO> allBox = new HashSet<BoxDTO>();
		for(Box box: UserRepository.getUser(userID).getHistory()) {
			allBox.add(new BoxDTO(box));
		}
		return allBox;
	}
	
	public BoxDTO createBox(String storeID, String userID, BoxDTO boxDTO) throws Exception {
		
		Set<Type> chosenFruit = new HashSet<Type>() ;
		for(FruitDTO fruitDTO: boxDTO.getAllFruits()) {
			chosenFruit.add(Type.valueOf(fruitDTO.getType()));
		}
		
		Store store = StoreRepository.getStore(storeID);
		User user = UserRepository.getUser(userID);
		Box box = new Box(store.getAllFruits(chosenFruit));
		
		try {addFruitsToBox(box, store.getRipeFruits());}
		catch(Exception e) {}
		try {
		if(chosenFruit.size()!=0) 
			addFruitsToBox(box, store.getCategoryFruits(preferredCategory(chosenFruit)));
		}catch(Exception e) {
			System.out.println(e.toString()+" "+store.isEmpty());
		}
		addFruitsToBox(box, store.getRandomFruits());
		store.removeAllFruit(new LinkedList<Fruit>(box.getAllFruits()));
		user.addBox(box);
		
		return new BoxDTO(box);
	}
	private void addFruitsToBox(Box box, LinkedList<Fruit> fruitsToAdd) throws Exception {
		if(!box.isFull()) {
		for(Fruit fruit: fruitsToAdd) {
			box.addFruit(fruit);	
			if(box.isFull())
				break;
			}
		}
	}
	
	private Category preferredCategory(Set<Type> chosenFruit) {
		Category prefCategory = null;
			int total = -1, count = 0;
			for(Category Category: Category.values()) {
				for(Type type: chosenFruit) 
					if(type.getCategory().equals(Category))
						count++;
				if (count > total) {
					prefCategory = Category;
					total = count;
				}
				count = 0;
			}
		return prefCategory;
	}
	
	public BoxDTO deleteBox(String userId, String boxId) throws Exception {	
		User user = UserRepository.getUser(userId);
		return user.removeBox(boxId);
	}
	
	public List<BoxDTO> deleteAllBoxs(String userId) throws Exception {	
		return UserRepository.getUser(userId).removeAllBox();
	}

	public BoxDTO updateBox(String userId, BoxDTO boxDTO) throws Exception {
		User user = UserRepository.getUser(userId);
		return user.updateBox(boxDTO);
	}
}