import javafx.scene.image.Image;

public class Block extends Entity {
	/**
	 * Constructor for Block
	 * @param x x
	 * @param y y
	 */
    public Block(int x, int y) {
        super(x, y, 50, 50);
    }

	@Override
	protected Image getImg() {
		return new Image("file:images/block.png", 54, 54, false, true);
	} 
}