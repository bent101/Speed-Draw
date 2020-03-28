import java.util.ArrayList;
import javafx.scene.Group;

public class Level {
    private ArrayList<Entity> entities;
    private ArrayList<Block> blocks;
    private int startPosX, startPosY;
    
    /**
     * Constructor for Level
     */
    public Level() {
    	entities = new ArrayList<>();
    	blocks = new ArrayList<>();
    }
    
    /**
     * Adds an entity to the entities
     * @param entity the entity to be added
     */
    public void addEntity(Entity entity) {
    	if(entity instanceof Player) {
    		startPosX = (int) entity.getX();
    		startPosY = (int) entity.getY();
    	}else {
	    	entities.add(entity);
	    	if(entity instanceof Block) {
	    		blocks.add((Block) entity);
	    	}
    	}
    }
    
    /**
     * Sets the starting point of this level
     * @param x x
     * @param y y
     */
    public void setStartPos(int x, int y) {
    	startPosX = x;
    	startPosY = y;
    }
    
    /**
     * Sends a given player to the starting point
     * @param player The player
     */
    public void toStartPos(Player player) {
    	player.setPos(startPosX, startPosY);
    }
    
    /**
     * Draws the level
     * @return The group full of entities to be drawn
     */
    public Group getGroup() {
    	Group group = new Group();
        for(int i = 0; i < entities.size(); i++) {
        	Entity entity = entities.get(i);
        	int offsetX = entity instanceof Spring? -5:
        				  entity instanceof Block? -2: 0;
        	int offsetY = entity instanceof Spring? -10:
        				  entity instanceof Trampoline? -20:
        				  entity instanceof Block? -2:
        				  entity instanceof SmallSpike? -4: 0;
            group.getChildren().add(entity.getImgView(offsetX, offsetY));
        }
        return group;
    }
    
    /**
     * @return The blocks in this level
     */
	public ArrayList<Block> getBlocks() {
		return blocks;
	}
	
	/**
	 * @return The entities in this level
	 */
	public ArrayList<Entity> getEntities() {
		return entities;
	}
}