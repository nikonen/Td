package model;
import java.awt.Color;
import java.awt.Graphics2D;


public class Message {

	private String text;
	private int x, y;
	private boolean alive;
	private int alpha;
	
	public Message(String text,int x,int y) {
		this.text = text;
		this.x = x;
		this.y = y;
		this.alive = true;
		this.alpha = 255;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text= text;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}
	
	public void draw(Graphics2D g) {
		
		if (this.alpha >= 0 && this.alive == true) {
			Color color = new Color(255,255,255, alpha);
			g.setColor(color);
			g.drawString(this.text, this.x, this.y);
			alpha -= 5;
			this.y -= 1;
			System.out.println("gmmm");
		} else {
			return;
		}
		
		if (alpha <= 0) {
			this.alive = false;
		}
		
	}
}
