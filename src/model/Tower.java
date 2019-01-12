package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.util.LinkedList;

public class Tower extends Entity {

	private float rateOfFire;
	private Ellipse2D rangeEllipse;
	private Enemy target;
	private long lastShot;
	private int damage;
	private int cost;
	public Tower(float x, float y, int width, int height, int range,
			long rateOfFire, int damage) {
		super(x, y, width, height);
		this.setX(this.getX() * 20);
		this.setY(this.getY() * 20);
		this.rangeEllipse = new Ellipse2D.Float(this.getXX() - range / 2, this
				.getYY()
				- range / 2, range, range);
		this.target = null;
		this.lastShot = System.currentTimeMillis();
		this.rateOfFire = rateOfFire;
		this.damage = damage;
	}
	
	

	public int getCost() {
		return cost;
	}



	public void setCost(int cost) {
		this.cost = cost;
	}



	public float getRateOfFire() {
		return rateOfFire;
	}

	public void setRateOfFire(float rateOfFire) {
		this.rateOfFire = rateOfFire;
	}

	public Ellipse2D getRangeEllipse() {
		return rangeEllipse;
	}

	public void setRangeEllipse(Ellipse2D rangeEllipse) {
		this.rangeEllipse = rangeEllipse;
	}

	public long getLastShot() {
		return lastShot;
	}

	public void setLastShot(long lastShot) {
		this.lastShot = lastShot;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public void setTarget(Enemy target) {
		this.target = target;
	}

	public void setTarget(LinkedList<Enemy> enemies) {

		if (!enemies.isEmpty()) {
			if (this.rangeEllipse.intersects(enemies.getFirst().getRect()) && enemies.getFirst().isAlive()) {
				this.target = enemies.getFirst();
			} else {
				/**
				 * Do better enemy detection, something like
				 * if not first, then the other one etc
				 */
				for (int i = 0; i < enemies.size(); i++) {
					if (this.rangeEllipse.contains(enemies.get(i).getRect()) && enemies.get(i).isAlive()) {
						this.target = enemies.get(i);
					}
				}
			}
		} else {
			this.target = null;
		}

	}

	public Enemy getTarget() {
		return this.target;
	}

	public int getXX() {
		return (int) (this.getX() + this.getWidth() / 2);
	}

	public int getYY() {
		return (int) (this.getY() + this.getHeight() / 2);
	}

	public boolean tryToShoot() {

		if (System.currentTimeMillis() - this.lastShot < this.rateOfFire) {
			return false;
		}

		this.lastShot = System.currentTimeMillis();
		return true;
	}

	public void draw(Graphics2D g) {
		g.setColor(Color.red);

		g.drawRect((int) this.getX(), (int) this.getY(), this.getWidth(), this
				.getHeight());
		g.setColor(Color.yellow);
		g.draw(this.rangeEllipse);
	}

}
