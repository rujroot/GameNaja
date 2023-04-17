package item;

import Data.BaseObject;
import Data.Point;
public abstract class Item extends BaseObject {

    private int amount = 0, index;
    private String Name;

    public Item(Point position, double width, double height) {
        super(position, width, height);
    }

    public Item(Point position, double width, double height, int z) {
        super(position, width, height, z);
    }

    public boolean equals(Item item){
        return this.getName().equals(item.getName());
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void addAmount(int amount){
        this.amount += amount;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    
    
}
