package entity;

import Data.DataEntity;
import Data.Point;
import drawing.GameScreen;
import input.InputUtility;
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

    // imgae ui
    private Image backUI = RenderableHolder.backUI, upUI = RenderableHolder.upUI, select = RenderableHolder.selectUI;
    private WritableImage backBtwUI = new WritableImage(backUI.getPixelReader(), 19, 0, 60, 96);

    private boolean isOffer = false;
    private double lastClickTime = 0, cooldownTime = 1000;
    private String Choose = "None";

    public Shopkeeper(String name, double width, double height, DataEntity data) {
        super(name, width, height, data);
        this.setWidth(image.getWidth());
		this.setHeight(image.getHeight());
    }

    public void openShopBuy(GraphicsContext gc){
        
    }

    public void openShopSell(GraphicsContext gc){

    }

    public void openChooseShop(GraphicsContext gc){
        //UI shop buy
        Point pos = this.getPosition();
        gc.drawImage(backUI, pos.getX() - backUI.getWidth() + 10, 
                        pos.getY() - backUI.getHeight() - 20, 
                        backUI.getWidth(), 
                        backUI.getHeight());

        // UI shop sell
        gc.drawImage(backUI, pos.getX() + 20, 
                        pos.getY() - backUI.getHeight() - 20, 
                        backUI.getWidth() , 
                        backUI.getHeight());
    }

    @Override
    public void draw(GraphicsContext gc) {

        // Draw Entity
        Point pos = this.getPosition();
		gc.drawImage(image, pos.getX(), pos.getY(), image.getWidth() , image.getHeight());

        updateInput();
        if(inDistant() && !isOffer){
            gc.setFont(new Font("Arial", 24));
            gc.setFill(Color.BLACK);
            gc.setStroke(Color.WHITE);
            gc.setLineWidth(5);


            gc.strokeText("E", pos.getX() + 20, pos.getY() - 20, 24);
            gc.fillText("E", pos.getX() + 20, pos.getY() - 20, 24);
        }

        if(!inDistant()){
            isOffer = false;
            Choose = "None";
        }

        // Draw Shop
        if(isOffer){
            if(Choose.equals("None")) openChooseShop(gc);
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
			isOffer = !isOffer;
            Choose = "None";
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
