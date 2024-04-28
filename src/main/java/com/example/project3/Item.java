package com.example.project3;

public class Item extends MainActivity{
    String itemName;
    int itemAmount;
    public Item( String name, int amount) {

        super();
        this.itemName = name;
        this.itemAmount = amount;
//constructor for items in item database
    }
    public String getItemName () {
        return itemName;
    }
    public int getItemAmount () {
        return itemAmount;
    }
    public void setItemName (String name) {
        this.itemName = name;
    }
    public void setItemAmount (int quantity) {
        this.itemAmount = quantity;
    }
    //getter and setter methods for accessing objects
}
