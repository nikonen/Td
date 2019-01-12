package model;

import java.util.LinkedList;

public class Enemy extends Entity {
	
	
	private LinkedList<Node> nodes;
	private int hp;
	private int bounty;
	public Enemy(float x, float y, int width, int height) {
		super(x, y, width, height);
		this.nodes = new LinkedList<Node>();
	}
	
	public LinkedList getNodes() {
		return nodes;
	}
	
	public int getBounty() {
		return bounty;
	}

	public void setBounty(int bounty) {
		this.bounty = bounty;
	}

	public void setNodes(LinkedList nodes) {
		this.nodes = nodes;
	}
	
	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public void move() {

		if (this.hp <= 0) {
			this.setAlive(false);
		}
		
		if (!nodes.isEmpty()) {
			Node node = this.nodes.getFirst();
			float nx = node.getX() - this.getX();
			float ny = node.getY() - this.getY();
			
			this.setAngle((float)Math.atan2(nx, ny));
			
			this.setX((float)(this.getX() + this.getVx() * Math.sin(this.getAngle())));
			this.setY((float)(this.getY() + this.getVy() * Math.cos(this.getAngle())));
		}

	}
	
	public void checkCollisionWithNode() {
		if (!nodes.isEmpty()) {
			Node node = this.nodes.getFirst();
			if (node.getRect().contains(this.getRect())) {
				this.nodes.removeFirst();
				System.out.println(this.getX() + " haki noden");
			}
		}

	}
	
	public int getXX() {
		return (int) (this.getX() + this.getWidth() / 2);
	}

	public int getYY() {
		return (int) (this.getY() + this.getHeight() / 2);
	}
	
	public void collidesWith(Object other) {
		if (other instanceof Bullet) {
			Bullet b = (Bullet)other;
			
			this.setHp(this.getHp() - b.getDamage());
			System.out.println(this.hp);
			return;
		}
	}

	
	
}
