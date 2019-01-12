package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Entity {

	private float x, y;
	private float vX, vY;
	private float angle;
	private int width, height;
	private boolean alive;
	
	public Entity(float x, float y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.angle = 0;
		this.alive = true;
	}

	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getVx() {
		return vX;
	}

	public void setvX(float vX) {
		this.vX = vX;
	}

	public float getVy() {
		return vY;
	}

	public void setVy(float vY) {
		this.vY = vY;
	}

	public float getAngle() {
		return angle;
	}

	public void setAngle(float angle) {
		this.angle = angle;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	public void draw(Graphics2D g) {
		g.setColor(Color.red);
		
		int xx = (int) (this.x + this.width  / 2);
		int yy = (int) (this.y + this.height / 2);
		
		g.drawRect((int)this.x, (int)this.y, this.width, this.height);

	}
	
	public Rectangle getRect() {
		return new Rectangle((int)this.x, (int)this.y, this.width, this.height);
	}
	
	
}
