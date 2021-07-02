package com.nwu;

public class BubbleGum extends Items{

	//instance variables
	private final String SOUND_OF_DISPENSER;

	//parameterized constructor
	public BubbleGum(String name, String price) {
		super(name, price);
		this.SOUND_OF_DISPENSER = "Chew Chew, Yum!";
	}

	//methods
	@Override
	public void dispose() {
		System.out.println("\n" + "You have bought: " + getName() + " for R" + getPrice() + "\n" + SOUND_OF_DISPENSER);
	}

}
