package com.nwu;

public class Chips extends Items{

    //instance variables
    protected final String SOUND_OF_DISPENSER;

    //parameterized constructor
    public Chips(String name, String price) {
        super(name, price);
        this.SOUND_OF_DISPENSER = "Munch Munch, Yum!";
    }

    //methods
    @Override
    public void dispose() {
        System.out.println("\n" + "You have bought: " + getName() + " for R" + getPrice() + "\n" + SOUND_OF_DISPENSER);
    }

}