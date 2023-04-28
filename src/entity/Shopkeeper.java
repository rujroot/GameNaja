package entity;

import java.lang.reflect.Array;

import data.DataEntity;
import data.Point;
import drawing.GameScreen;
import input.InputUtility;
import inventory.BaseUI;
import inventory.Inventory;
import inventory.SlotUI;
import item.Coal;
import item.Diamond;
import item.GoldIngot;
import item.IronIngot;
import item.Item;
import item.Stone;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import logic.Cooldownable;
import logic.RenderableHolder;

public class Shopkeeper extends Entity implements Cooldownable{

    // image entity
    private WritableImage image = new WritableImage(RenderableHolder.Tileset.getPixelReader(), 392, 960, 51, 63);

    private BaseUI currentUI, chooseUI, sellUI, buyUI;

    private String choose = "None";
    private double lastClickTime = 0, cooldownTime = 200;
    private int select = 0;

    private Item[] sellItem = new Item[5];

    public Shopkeeper(String name, double width, double height, DataEntity data) {
        super(name, width, height, data);
        this.setWidth(image.getWidth());
		this.setHeight(image.getHeight());

        chooseUI = new BaseUI(new Point(-100, -150), 0, 0, 2, 20, this);
        chooseUI.setSelectIndex(0);
        

        buyUI = new BaseUI(new Point(-150, -150), 0, 0, 4, 10, this);
        buyUI.setSelectIndex(0);

        initShopSell();
    }

    public void initShopSell(){
        sellUI = new BaseUI(new Point(-250, -150), 0, 0, 5, 10, this);
        sellUI.setSelectIndex(0);

        sellItem[0] = new Stone(0, 0);
        sellItem[1] = new Coal(0, 0);
        sellItem[2] = new IronIngot(0, 0);
        sellItem[3] = new GoldIngot(0, 0);
        sellItem[4] = new Diamond(0, 0);

        for(int i = 0; i < sellItem.length; ++i){
            sellUI.addItem(sellItem[i]);
        }
        SlotUI[] slot = sellUI.getPosIndex();
        slot[0].setDescription("0$");
        slot[1].setDescription("0$");
        slot[2].setDescription("0$");
        slot[3].setDescription("0$");
        slot[4].setDescription("0$");
    }

    public void shopBuy(int Index){
        
    }

    public void shopSell(int Index){
        Item itemToSell = sellItem[Index];
        if(itemToSell.getAmount() <= 0) return;

        Player player = Player.player;
        Inventory inventory = Player.inventory;
        SlotUI[] slotInventory = inventory.getUI().getPosIndex();

        for(int i = 0; i < slotInventory.length; ++i){
            Item itemInv = slotInventory[i].getItem();
            if(itemInv != null && itemInv.equals(itemToSell)){ 
                player.addMoney(itemInv.getValue());
                itemInv.setAmount(0);
                itemInv.setValue(0);
                inventory.removeItem(itemInv);
                updateShopSell();
                return;
            }  
        }
    }

    public void updateShopSell(){
        Inventory inventory = Player.inventory;
        SlotUI[] slotInventory = inventory.getUI().getPosIndex();
        SlotUI[] slotSell = sellUI.getPosIndex();

        for(int i = 0; i < slotSell.length; ++i){
            Item itemSell = slotSell[i].getItem();
            boolean found = false;

            for(int j = 0; j < slotInventory.length; ++j){
                Item itemInv = slotInventory[j].getItem();

                if(itemInv != null && itemSell.equals(itemInv)){
                    itemSell.setAmount(itemInv.getAmount());
                    slotSell[i].setDescription(Double.toString(itemInv.getValue()) + "$");
                    found = true;
                    break;
                }
            }

            if(!found){
                itemSell.setAmount(0);
                slotSell[i].setDescription("0$");
            }
        }

    }

    @Override
    public void draw(GraphicsContext gc) {

        // Draw Entity
        Point pos = this.getPosition();
		gc.drawImage(image, pos.getX(), pos.getY(), image.getWidth() , image.getHeight());

        updateInput();
        if(inDistant() && currentUI == null){
            gc.setFont(new Font("Arial", 24));
            gc.setFill(Color.BLACK);
            gc.setStroke(Color.WHITE);
            gc.setLineWidth(5);

            gc.strokeText("E", pos.getX() + 20, pos.getY() - 20, 24);
            gc.fillText("E", pos.getX() + 20, pos.getY() - 20, 24);
        }

        if(!inDistant()){
            currentUI = null;
            choose = "None";
            Player.player.setDescription("");
        }

        // Draw Shop
        if(currentUI != null){
            currentUI.draw(gc);
        }
    }

    public boolean inDistant(){
        Point playerPos = Player.player.getPosition();
        Point pos = this.getPosition();

        return Math.sqrt( Math.pow(playerPos.getX() - pos.getX() , 2) + Math.pow( playerPos.getY() - pos.getY(), 2) ) <= 250;
    }

    public void updateInput(){
        // Player interact with this entity
        if (InputUtility.getKeyPressed(KeyCode.E) && inDistant() && !onCooldown()){
            if(choose.equals("None")){
                choose = "Choose";
                this.setCurrentUI(chooseUI);
                Player.player.setDescription("< : Left | > : Right | E : Select | Q : Back");
            }
            else if(choose.equals("Choose")){
                int currSelect = currentUI.getSelectIndex();
                if(currSelect == 0){
                    this.setCurrentUI(buyUI);
                    choose = "Buy";
                }else{
                    updateShopSell();
                    this.setCurrentUI(sellUI);
                    choose = "Sell";
                }
            }
            else if(choose.equals("Buy")){
                int currSelect = currentUI.getSelectIndex();
                shopBuy(currSelect);
            }
            else if(choose.equals("Sell")){
                int currSelect = currentUI.getSelectIndex();
                shopSell(currSelect);
            }
		}

        if (InputUtility.getKeyPressed(KeyCode.Q) && inDistant() && !onCooldown()){
            if(choose.equals("Choose")){
                currentUI = null;
                choose = "None";
                Player.player.setDescription("");
            }
            else if(choose.equals("Buy") || choose.equals("Sell")){
                this.setCurrentUI(chooseUI);
                choose = "Choose";
            }
		}

        if (InputUtility.getKeyPressed(KeyCode.LEFT) && currentUI != null && !onCooldown()){
			currentUI.setSelectIndex(currentUI.getSelectIndex() - 1);

		}
        if (InputUtility.getKeyPressed(KeyCode.RIGHT) && currentUI != null && !onCooldown()){
            currentUI.setSelectIndex(currentUI.getSelectIndex() + 1);

		}

    }
    
    @Override
    public void attack() {
       // This entity not attack 
    }

    public int getSelect() {
        return select;
    }

    public void setSelect(int select) {
        this.select = select;
    }

    public BaseUI getCurrentUI() {
        return currentUI;
    }

    public void setCurrentUI(BaseUI currentUI) {
        if(this.currentUI != null) this.currentUI.setVisible(false);
        currentUI.setVisible(true);
        this.currentUI = currentUI;
    }

    @Override
    public boolean onCooldown() {
        long currentTime = System.currentTimeMillis();
		if(currentTime - lastClickTime > cooldownTime) {
			lastClickTime = currentTime;
			return false;
		}else {
			return true;
		}
    }
}
