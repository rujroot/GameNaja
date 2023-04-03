package drawing;

import java.util.ArrayList;

import Dungeon.GenerateDungeon;
import Dungeon.Room;
import Math.Point;
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

public class GameScreen extends Canvas {

	public GameScreen(double width, double height) {
		super(width, height);
		this.setVisible(true);
		addListerner();
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
		gc.clearRect(pos.getX() - 700, pos.getY() - 400, 1400, 800);
		player.update(gc);
		
	}
	
	public void paintComponent() {
		GraphicsContext gc = this.getGraphicsContext2D();
		gc.setFill(Color.BLACK);
		
		for (IRenderable entity : RenderableHolder.getInstance().getEntities()) {
			if (entity.isVisible() && !entity.isDestroyed()) {
				entity.draw(gc);
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
