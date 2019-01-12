package model;

public class Bullet extends Entity {

	private int damage;
	public Bullet(float x, float y, int width, int height) {
		super(x, y, width, height);
		this.setvX(3f);
		this.setVy(3f);
	}
	
	public void setDamage(int damage) {
		this.damage = damage;
	}
	
	public int getDamage() {
		return this.damage;
	}

	public void move() {
		this.setX((float) (this.getX() + this.getVx()
				* Math.sin(this.getAngle())));
		this.setY((float) (this.getY() + this.getVy()
				* Math.cos(this.getAngle())));
	}

	public void setDirection(float dx, float dy) {
		float nx = dx - this.getX();
		float ny = dy - this.getY();
		this.setAngle((float) Math.atan2(nx, ny));

	}
	
	

}
