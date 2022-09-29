package com.tcm.Fruites.utilities;

public enum Category {

	ACID(20), SEMIACID(10), SWEET(15), NEUTRAL(40);

	private final int EXPIRATION_DAY;

	Category(int expirationday) {
		this.EXPIRATION_DAY = expirationday;
	}

	public int getExpirationDay() {
		return EXPIRATION_DAY;
	}
}
