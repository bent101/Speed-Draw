public abstract class Obstacle extends Entity {
	private int damageAmt;
    /**
     * Constructor for Obstacle
     * @param x x
     * @param y y
     * @param w width
     * @param h height
     */
    public Obstacle(int x, int y, int w, int h, int damageAmt) {
        super(x, y, w, h);
        this.damageAmt = damageAmt;
    }
    
    /**
     * Deals damage to a given player
     * @param player the player receiving the damage
     * @param damageAmt how much damage is dealt
     */
    public void dealDamage(Player player) {
        player.takeDamage(damageAmt);
    }
}