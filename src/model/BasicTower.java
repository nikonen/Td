package model;

import java.awt.Color;
import java.awt.Graphics2D;

public class BasicTower extends Tower{

	public BasicTower(float x, float y, int width, int height, int range,
			long rateOfFire, int damage) {
		super(x, y, width, height, range, rateOfFire, damage);
		
		this.setDamage(2);
		this.setRateOfFire(1000);
		this.setCost(5);
	}
	
	public void draw(Graphics2D g) {
		g.setColor(Color.green);
		
		g.drawRect((int) this.getX(), (int) this.getY(), this.getWidth(), this
				.getHeight());
		g.setColor(Color.yellow);
		g.draw(this.getRangeEllipse());
	}
	
	public String toString() {
		return "This basic tower has low damage, low speed "+
			   "but pretty good range";
	}

}
