package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class Map {

	private final int width = 40; // map width
	private final int height = 40; // map height
	private final int tileSize = 20; // tile size
	private String mapFile; // map file to be loaded
	private int[][] map; // 2d array for map
	private LinkedList<Node> nodes;

	public Map(String mapFile) {
		nodes = new LinkedList<Node>();
		map = new int[width][height];
		this.mapFile = mapFile;
		System.out.println(new File(mapFile).getAbsolutePath());
		try {
			Scanner sc = new Scanner(new File(mapFile));
			while (sc.hasNextInt()) {
				for (int y = 0; y < this.height; y++) {
					for (int x = 0; x < this.width; x++) {
						map[y][x] = sc.nextInt();
						
						
						/**
						 * Waypoints are marked as number 3 in map file, so
						 * let's look out for them and add them to nodes list
						 */
						
						if (map[y][x] == 3) {
							this.nodes.add(new Node(x*tileSize, y*tileSize));
							System.out.println("node");
						}
					}
				}
			}

		} catch (Exception x) {
			System.out.println(x);
			
		}
		
		
	}

	
	
	public int getWidth() {
		return width;
	}



	public int getHeight() {
		return height;
	}



	public void drawMap(Graphics2D g) {

		for (int y = 0; y < this.height; y++) {
			for (int x = 0; x < this.width; x++) {
				if (map[y][x] == 0) {
					g.setColor(Color.gray);
					g.fillRect(x * tileSize, y * tileSize, tileSize, tileSize);

				}

				if (map[y][x] == 1) {
					g.setColor(Color.green.darker().darker().darker());
					g.fillRect(x * tileSize, y * tileSize, tileSize, tileSize);
				}
			}
		}
	}
	
	public LinkedList<Node> getNodes() {
		return this.nodes;
	}
	
	public boolean getBlocked(float x, float y) {

		return this.map[(int) y][(int) x] == 1 ? true : false; 
	}
}
