package entity;

import data.DataEntity;
import data.Point;
import drawing.GameScreen;
import input.InputUtility;
import inventory.BaseUI;
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

    private boolean isOffer = false;
    private String choose = "None";
    private double lastClickTime = 0, cooldownTime = 200;
    private int select = 0;

    public Shopkeeper(String name, double width, double height, DataEntity data) {
        super(name, width, height, data);
        this.setWidth(image.getWidth());
		this.setHeight(image.getHeight());

        chooseUI = new BaseUI(new Point(-100, -100), 0, 0, 2, 20, this);
        chooseUI.setSelectIndex(0);

        buyUI = new BaseUI(new Point(-200, -100), 0, 0, 4, 10, this);
        buyUI.setSelectIndex(0);

        sellUI = new BaseUI(new Point(-200, -100), 0, 0, 5, 10, this);
        sellUI.setSelectIndex(0);
    }

    public void shopBuy(int Index){
        
    }

    public void shopSell(int Index){

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
                currentUI = chooseUI;
                choose = "Choose";
            }
            else if(choose.equals("Choose")){
                int currSelect = currentUI.getSelectIndex();
                if(currSelect == 0){
                    currentUI = buyUI;
                    choose = "Buy";
                }else{
                    currentUI = sellUI;
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
            }
            else if(choose.equals("Buy") || choose.equals("Sell")){
                currentUI = chooseUI;
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

    public boolean isOffer() {
        return isOffer;
    }

    public void setOffer(boolean isOffer) {
        this.isOffer = isOffer;
    }

    public int getSelect() {
        return select;
    }

    public void setSelect(int select) {
        this.select = select;
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
