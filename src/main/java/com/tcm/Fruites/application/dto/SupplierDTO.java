package com.tcm.Fruites.application.dto;

import com.tcm.Fruites.domain.users.Supplier;
import com.tcm.Fruites.utilities.exceptions.InvalidParamException;

public class SupplierDTO {

	private String supplierID;
	private String name;
	
	public SupplierDTO() {
		
	}
	
	public SupplierDTO(Supplier supplier) throws InvalidParamException {
		if(supplier==null) throw new InvalidParamException();
		this.supplierID=supplier.getID();
		this.name=supplier.getName();
	}
	
	public String getID() throws InvalidParamException {
		if(this.supplierID==null||this.supplierID.equals("")) throw new InvalidParamException();
		return this.supplierID;
	}
 
	public String getName() throws InvalidParamException {
		if(this.name==null||this.name.equals("")) throw new InvalidParamException();
		return this.name;
	}
}
