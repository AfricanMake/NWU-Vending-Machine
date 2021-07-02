package com.nwu;

public abstract class Items {

    //instance variables
    private String name;
    private String quantity;
    private String price;

    //parameterized constructor
    public Items(String name, String price) {
        this.name = name;
        this.quantity = "5";
        this.price = price;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public void decreaseQuantity() {

        if(quantity.equalsIgnoreCase("Sold out")) {
            //nothing happens
            //setQuantity("Sold out");
        }
        else if (!(quantity.equalsIgnoreCase("Sold out"))) {
            int numberOfItems = Integer.parseInt(getQuantity());
            if(numberOfItems > 1) {
                numberOfItems--;
                String newQuantity = String.valueOf(numberOfItems);
                setQuantity(newQuantity);
            } else if(numberOfItems == 1) {
                setQuantity("Sold out");
            }
        }
    }

    // Abstract Methods
    public abstract void dispose();
}
