
import javafx.scene.shape.*;
import javafx.scene.image.*;

public abstract class Entity extends Rectangle {
	protected ImageView imgView;
	
    /**
     * Constructor for Entity
     * @param x x
     * @param y y
     * @param w width
     * @param h height
     */
    public Entity(int x, int y, int w, int h) {
        super(x, y, w, h);
        imgView = new ImageView(getImg());
    }

	/**
     * intersects method
     * @param that Other entity to check intersection with
     * @return true if it collides with another entity, false otherwise
     */
    public boolean intersects(Entity that) {
        return Math.abs(this.getX() - that.getX()) < Math.max(this.getWidth(), that.getWidth()) &&
        	   Math.abs(this.getY() - that.getY()) < Math.max(this.getHeight(), that.getHeight());
    }
    
    /**
     * Returns the png image of this entity
     * @return Image of the entity
     */
    protected abstract Image getImg();
    
    /**
     * Update the ImageView based on where the entity is
     * @param offsetX offset X
     * @param offsetY offset Y
     */
    public void updateImgView(int offsetX, int offsetY) {
    	imgView.setX(getX() + offsetX);
    	imgView.setY(getY() + offsetY);
    }
    
    /**
     * Updates the ImageView with no offset
     */
    public void updateImgView() {
    	updateImgView(0, 0);
    }
    
    /**
     * Gets the ImageView of this entity
     * @param offsetX offset X
     * @param offsetY offset Y
     * @return The ImageView
     */
    public ImageView getImgView(int offsetX, int offsetY) {
    	updateImgView(offsetX, offsetY);
    	return imgView;
    };
    
    /**
     * Gets the ImageView with no offset
     * @return The ImageView
     */
    public ImageView getImgView() {
    	return getImgView(0, 0);
    }
    
    /**
     * Translates the entity
     * @param dx Change in x
     * @param dy Change in y
     */
    public void translate(int dx, int dy) {
    	setX(getX() + dx);
    	setY(getY() + dy);
    }
}