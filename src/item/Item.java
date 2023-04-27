package item;

import data.BaseObject;
import data.Point;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
public abstract class Item extends BaseObject {

    private int amount = 0, index;
    private String Name;
    private WritableImage image;

    public Item(Point position, double width, double height) {
        super(position, width, height);
    }

    public Item(Point position, double width, double height, int z) {
        super(position, width, height, z);
    }

    @Override
    public void draw(GraphicsContext gc) {
    	Point pos = this.getPosition();
		gc.drawImage(image, pos.getX() + 5, pos.getY() + 5, image.getWidth()*1.5 , image.getHeight()*1.5);

        gc.setFont(new Font("Arial", 24));
        gc.setFill(Color.BLACK);
        gc.setStroke(Color.WHITE);
        gc.setLineWidth(5);

        String itemAmount = Integer.toString(getAmount());

        gc.strokeText(itemAmount, pos.getX() + this.getWidth() - itemAmount.length() * 10 , pos.getY() + this.getHeight(), 100);
        gc.fillText(itemAmount, pos.getX() + this.getWidth() - itemAmount.length() * 10, pos.getY() + this.getHeight(), 100);
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

    public WritableImage getImage() {
        return image;
    }

    public void setImage(WritableImage image) {
        this.image = image;
    }    
}
