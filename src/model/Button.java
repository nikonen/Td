package model;

import java.awt.Color;
import java.awt.Graphics2D;

public class Button {

	private int x,y;
	private String text;
	
	public Button(int x, int y, String text) {
		this.x = x;
		this.y = y;
		this.text = text;
	}
	
	public void draw(Graphics2D g) {
		g.setColor(Color.white.darker());
		g.fillRect(this.x, this.y, 75,30);
		g.setColor(Color.black);
		g.drawString(this.text, this.x + 10, this.y + 10);
	}
}
