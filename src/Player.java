import java.util.ArrayList;

import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.*;

public class Player extends Entity {
    private int health;
    private int vy, ay;
    public boolean canJump;
    private KeyCode[] controls;
    private Rectangle healthBar;
    private int deaths;
    private boolean finishedLevel;
    
    /**
     * Constructor for Player
     * @param x x
     * @param y y
     * @param controls up left right
     */
    public Player(int x, int y, KeyCode[] controls) {
        super(x, y, 50, 50);
        health = 300;
        vy = 0;
        ay = 1;//gravity
        canJump = false;
        this.controls = controls;
        healthBar = new Rectangle(0, 0, 60, 6);
	        healthBar.setFill(Color.color(0, 1, 0));
	        healthBar.setArcWidth(3);
	        healthBar.setArcHeight(3);
	        healthBar.setStroke(Color.GRAY);
        deaths = 0;
        finishedLevel = false;
    }
    
    /**
     * Constructs a new Player at (0, 0)
     * @param controls up left right
     */
    public Player(KeyCode[] controls) {
    	this(0, 0, controls);
    }
    
    /**
     * Returns the controls for up left right
     * @return The controls
     */
    public KeyCode[] getControls() {
    	return controls;
    }
    
    /**
     * Returns how many times the player has died in this run
     * @return Deaths
     */
    public int getDeaths() {
    	return deaths;
    }
    
    /**
     * Resets deaths to zero
     */
    public void resetDeaths() {
    	deaths = 0;
    }
    
    /**
     * Starts a level
     * @param level Level to start
     */
    public void startLevel(Level level) {
    	finishedLevel = false;
    	level.toStartPos(this);
    	refillHealth();
    	vy = 0;
    }
    
    /**
     * Returns whether the player has finished a level
     * @return finishedLevel Whether the player has finished a level
     */
    public boolean finishedLevel() {
    	return finishedLevel;
    }
    
    /**
     * Gets the amount of health(100 = full, 0 = dead)
     * @return health as an int from 0 to 100
     */
    public int getHealth() {
        return health;
    }
    
    /**
     * Refills health
     */
    public void refillHealth() {
        health = 300;
    }
    
    /**
     * Updates the player's health bar
     */
    public void updateHealthBar() {
    	healthBar.setFill(Color.hsb(health * 0.4, 0.9, 1));
    	healthBar.setWidth(0.2 * health);
    	healthBar.setX(this.getX() - 5);
    	healthBar.setY(this.getY() - 7);
    }
    
    /**
     * Returns the player's health bar
     * @return The health bar (a rectangle)
     */
    public Rectangle getHealthBar() {
    	updateHealthBar();
    	return healthBar;
    }
    
    /**
     * Health is reduced by a given amount
     * @param damageAmt how much the player is damaged
     */
    public void takeDamage(int damageAmt) {
        health -= damageAmt;
    }
    
    /**
     * Sets the player's position
     * @param x x
     * @param y y
     */
    public void setPos(int x, int y) {
    	setX(x);
    	setY(y);
    }
    
    /**
     * Sets the player's y acceleration
     * @param ay Acceleration in the y direction
     */
    public void setAcc(int ay) {
    	this.ay = ay;
    }
    
    /**
     * Moves the player side to side
     * @param dx Change in x
     * @param blocks Blocks to collide with
     */
    public void moveX(int dx, ArrayList<Block> blocks) {
    	boolean movingRight = dx > 0;
    	for(int i = 0; i < Math.abs(dx); i++) {
    		translate(movingRight? 1: -1, 0);
    		for(Block block: blocks) {
    			if(intersects(block)) {
    				translate(movingRight? -1: 1, 0);
    				return;
    			}
    		}
    	}
    }
    
    /**
     * Moves the player up and down
     * @param dy Change in y
     * @param blocks Blocks to collide with
     */
    public void moveY(int dy, ArrayList<Block> blocks) {
    	boolean movingDown = dy > 0;
    	for(int i = 0; i < Math.abs(dy); i++) {
    		translate(0, movingDown? 1: -1);
    		for(Block block: blocks) {
    			if(intersects(block)) {
    				translate(0, movingDown? -1: 1);
    				canJump = movingDown;
    				vy = 0;
    				return;
    			}
    		}
    	}
    }
    
    /**
     * Moves the player
     * @param dx Change in x
     * @param dy Change in y
     * @param blocks Blocks to collide with
     */
    public void move(int dx, int dy, ArrayList<Block> blocks) {
    	moveX(dx, blocks);
    	moveY(dy, blocks);
    }
    
    /**
     * Restarts a level
     * @param level Level to restart
     */
    public void restartLevel(Level level) {
    	level.toStartPos(this);
		refillHealth();
		deaths++;
		vy = 0;
		if(deaths%5 == 0) {
			SoundPlayer.playBruh();
		}else {
			SoundPlayer.playOof();
		}
    }
    
    /**
     * Controls the player using the keyboard
     * @param blocks blocks to collide with
     */
    public void control(ArrayList<Block> blocks) {
    	if(Main.isPressed(controls[0])) {
			jump();
		}
		if(Main.isPressed(controls[1])) {
			move(-7, 0, blocks);
		}
		if(Main.isPressed(controls[2])) {
			move(7, 0, blocks);
		}
    }
    
    /**
     * Updates the player given the current level
     * @param level The current level
     */
	public void update(Level level) {
		ArrayList<Block> blocks = level.getBlocks();
    	control(blocks);
    	vy += ay;
    	move(0, vy, blocks);
    	if(vy > 0) canJump = false;
    	if(getY() > 500) restartLevel(level);
    	for(Entity entity: level.getEntities()) {
    		if(this.intersects(entity)) {
	    		if(entity instanceof Obstacle) {
	    			((Obstacle) entity).dealDamage(this);
	    			if(this.health <= 0) {
	    				restartLevel(level);
	    			}
	    		} else
	    		if(entity instanceof BouncePad) {
	    			this.vy = -((BouncePad) entity).getBounceForce();
	    			canJump = false;
	    			SoundPlayer.playBoing();
	    		} else
	    		if(entity instanceof Portal) {
	    			finishedLevel = true;
	    			SoundPlayer.playPortal();
	    		}
    		}
    	}
    	updateImgView();
    	updateHealthBar();
    }
    
	/**
	 * Jumps the player
	 * @param force How high the player is "jumped"
	 */
    public void jump(int force) {
    	if(canJump) {
    		vy = -force;
    		canJump = false;
    	}
    }
    
    /**
     * Jumps the player by a default force of 17
     */
    public void jump() {
    	jump(17);
    }
    
	@Override
	protected Image getImg() {
		return new Image("file:images/player.png", 50, 50, false, true);
	}
}