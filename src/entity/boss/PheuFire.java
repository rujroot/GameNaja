package entity.boss;

import animation.AnimationController;
import animation.CircleAttackAnimation;
import animation.ImageAnimation;
import data.DataEntity;
import data.Point;
import entity.Player;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import logic.Hitbox;
import logic.Main;
import logic.RenderableHolder;

public class PheuFire extends BossEntity{

    private ImageAnimation[] imageAnimation = new ImageAnimation[10];
    private String status = "Idel";
    private boolean actionDone = true;

    private double cooldownTime = 2000;
	private double lastClickTime = 0;
    private int currImage = 0;

    public PheuFire(String name, DataEntity data) {
        super(name, data, new Hitbox(new Point(310, 160), 260, 310));
        this.setWidth(260);
        this.setHeight(310);

        Image[][] demonSlime = RenderableHolder.imageAnimation.get("DemonSlime");

        imageAnimation[0] = new ImageAnimation(this.getPosition(), this.getWidth(), this.getHeight(), 
                            demonSlime[0], 10, 7);
        imageAnimation[1] = new ImageAnimation(this.getPosition(), this.getWidth(), this.getHeight(), 
                            demonSlime[1], 5, 16);
        AnimationController.animations.add(imageAnimation[0]);
        AnimationController.animations.add(imageAnimation[1]);

        imageAnimation[0].setStop(false);
    }

    @Override
    public void draw(GraphicsContext gc) {
        Image image = imageAnimation[currImage].getCurrImage();
        Point pos = this.getPosition();
        Hitbox hitbox = this.getBossHitbox();
        gc.drawImage(image, pos.getX() - hitbox.getPosition().getX(), pos.getY() - hitbox.getPosition().getY(), image.getWidth(), image.getHeight());
    }

    public void playAnimation(int index){
        this.setCurrImage(index);

    }

    @Override
    public void attack() {
        if(actionDone == false){
            playAction(status);
            return;
        }
        actionDone = false;
        // Random Action to play
        String[] action =  {"Attacking", "Summon"};
        status = "Attacking";
        
    }

    private void playAction(String status) {
        if(status.equals("Attacking")){
            playAttack();
        }

    }

    public void playAttack(){
        follow();

        cooldownTime = 3000;
        long currentTime = System.currentTimeMillis();
		if (currentTime - lastClickTime > cooldownTime) {
			lastClickTime = currentTime;

            this.playAnimation(1);
            
            Point pos = this.getPosition();
            CircleAttackAnimation circle = new CircleAttackAnimation(new Point(pos.getX() - 120, pos.getY() + 250), 0, 0);
            AnimationController.animations.add(circle);
            Main.getLogic().addObject(circle);
		}else{
            
        }
    }

    public void follow() {
		Point pp = Player.getPlayer().getPosition();

        Hitbox hitbox = this.getBossHitbox();
		double px = pp.getX() , py = pp.getY();

		Point p = new Point(this.getPosition().getX() - px - 100, this.getPosition().getY() - py + 100);
		double distance = Math.sqrt(p.getX() * p.getX() + p.getY() * p.getY());

		DataEntity data = this.getData();
        
		if (distance > 50) {
			this.move(-p.getX() / distance * data.getSpd(), 0);
			this.move(0, -p.getY() / distance * data.getSpd());
		}

	}

    public int getCurrImage() {
        return currImage;
    }

    public void setCurrImage(int currImage) {
        imageAnimation[this.currImage].setStop(true);
        this.currImage = currImage;
        imageAnimation[this.currImage].setStop(false);
    }
    
}
