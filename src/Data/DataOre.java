package Data;

import java.util.ArrayList;

import item.Item;

public class DataOre {
    private double durability, value;
    private ArrayList<Item> toDrop;
    private int amount;

    public double getDurability() {
        return durability;
    }
    public void setDurability(double durability) {
        this.durability = durability;
    }
    public double getValue() {
        return value;
    }
    public void setValue(double value) {
        this.value = value;
    }
    public ArrayList<Item> getToDrop() {
        return toDrop;
    }
    public void setToDrop(ArrayList<Item> toDrop) {
        this.toDrop = toDrop;
    }
    public int getAmount() {
        return amount;
    }
    public void setAmount(int amount) {
        this.amount = amount;
    }
    
}
