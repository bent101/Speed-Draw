public abstract class BouncePad extends Entity {
	private int bounceForce;
	
	/**
	 * Constructor for BouncePad
	 * @param x x
	 * @param y y
	 * @param w width
	 * @param h height
	 * @param bounceForce How forcefully this bounce pad jumps the player
	 */
    public BouncePad(int x, int y, int w, int h, int bounceForce) {
        super(x, y, w, h);
        this.bounceForce = bounceForce;
    }
    
    public int getBounceForce() {
    	return bounceForce;
    }
}