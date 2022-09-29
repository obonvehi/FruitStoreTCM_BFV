package com.tcm.Fruites.utilities;

public enum Type {

	LEMON(Category.ACID), GRAPEFRUIT(Category.ACID), ORANGE(Category.ACID), PINEAPPLE(Category.ACID),
	STRAWBERRY(Category.SEMIACID), TANGERINE(Category.SEMIACID), MANGO(Category.SEMIACID), PEACH(Category.SEMIACID),
	APRICOT(Category.SWEET), CHERRY(Category.SWEET), APPLE(Category.SWEET), MELON(Category.SWEET),
	ALMOND(Category.NEUTRAL), HAZELNUT(Category.NEUTRAL), COCONUT(Category.NEUTRAL), WALNUT(Category.NEUTRAL);

	private final Category category;

	Type(Category category) {
		this.category = category;
	}

	public Category getCategory() {
		return this.category;
	}
}
