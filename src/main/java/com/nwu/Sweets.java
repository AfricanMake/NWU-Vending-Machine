package com.nwu;

public class Sweets extends Items{

    // instance variables
    private final String SOUND_OF_DISPENSER;

    //parameterized constructor
    public Sweets(String name, String price) {
        super(name, price);
        this.SOUND_OF_DISPENSER = "Yum Yum!";
    }

    //methods
    @Override
    public void dispose() {
        System.out.println("\n" + "You have bought: " + getName() + " for R" + getPrice() + "\n" + SOUND_OF_DISPENSER);
    }

}
