package drawing;

import java.util.ArrayList;

import Data.Point;
import Dungeon.GenerateDungeon;
import Dungeon.Room;
import entity.Player;
import input.InputUtility;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import logic.IRenderable;
import logic.RenderableHolder;
import ore.BaseOre;

public class GameScreen extends Canvas {

	private Point posCamera;

	public GameScreen(double width, double height) {
		super(width, height);
		this.setVisible(true);
		addListerner();
		
		GraphicsContext gc = this.getGraphicsContext2D();
		Player player = Player.getPlayer();
		
		// first to set camera
		player.setResolutionPosition(new Point(width / 2, height / 2));
		gc.translate(width / 2, height / 2);
		posCamera = new Point(-width / 2, -height / 2);
	}
	
	//insert event input
	public void addListerner() {
		this.setOnKeyPressed((KeyEvent event) -> {
			InputUtility.setKeyPressed(event.getCode(), true);
		});

		this.setOnKeyReleased((KeyEvent event) -> {
			InputUtility.setKeyPressed(event.getCode(), false);
		});

		this.setOnMousePressed((MouseEvent event) -> {
			if (event.getButton() == MouseButton.PRIMARY)
				InputUtility.mouseLeftDown();
		});

		this.setOnMouseReleased((MouseEvent event) -> {
			if (event.getButton() == MouseButton.PRIMARY)
				InputUtility.mouseLeftRelease();
		});

		this.setOnMouseEntered((MouseEvent event) -> {
			InputUtility.mouseOnScreen = true;
		});

		this.setOnMouseExited((MouseEvent event) -> {
			InputUtility.mouseOnScreen = false;
		});

		this.setOnMouseMoved((MouseEvent event) -> {
			if (InputUtility.mouseOnScreen) {
				InputUtility.mouseX = event.getX();
				InputUtility.mouseY = event.getY();
			}
		});

		this.setOnMouseDragged((MouseEvent event) -> {
			if (InputUtility.mouseOnScreen) {
				InputUtility.mouseX = event.getX();
				InputUtility.mouseY = event.getY();
			}
		});
	}
	
	public void updatePlayer() {
		GraphicsContext gc = this.getGraphicsContext2D();
		Player player = Player.getPlayer();
		Point pos = player.getPosition();

		// Prepar for new draw
		gc.clearRect(pos.getX() - this.getWidth() / 2, 
					pos.getY() - this.getHeight() / 2, 
					this.getWidth() * 1.2, 
					this.getHeight() * 1.2);
		// update player input
		player.update();
		// Track Camera follow player
		this.trackCamera();
	}

	// TrackCamera follow Player with offset
	public void trackCamera(){
		GraphicsContext gc = this.getGraphicsContext2D();
		Point pos = Player.getPlayer().getPosition();

		Point posToTrack = new Point(pos.getX() - this.getWidth() / 2, pos.getY() - this.getHeight() / 2);
		double posCameraX = posCamera.getX(), posCameraY = posCamera.getY();

		// Track
		gc.translate(posCameraX - posToTrack.getX(), posCameraY - posToTrack.getY());
		posCamera.setX(posCameraX - (posCameraX - posToTrack.getX()));
		posCamera.setY(posCameraY - (posCameraY - posToTrack.getY()));
	}
	
	public void paintComponent() {
		GraphicsContext gc = this.getGraphicsContext2D();
		gc.setFill(Color.BLACK);
		
		for (IRenderable object : RenderableHolder.getInstance().getObjects()) {
			if (object.isVisible() && !object.isDestroyed()) {
				// if(object instanceof BaseOre)
				// 	System.out.println("drawing");
				object.draw(gc);
			}
		}

	}
	
	public void paintLevel() {
		int currLevel = GenerateDungeon.getCurrLevel();
		ArrayList<Room> level = GenerateDungeon.getContainer().get(currLevel);
		GraphicsContext gc = this.getGraphicsContext2D();
		for (IRenderable room : level) {
			room.draw(gc);
		}
		
	}

}
