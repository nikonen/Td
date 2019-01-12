package control;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.LinkedList;

import model.BasicTower;
import model.Bullet;
import model.Button;
import model.Enemy;
import model.EnemyLvl1;
import model.Map;
import model.Message;
import model.Node;
import model.Tower;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Game extends Canvas implements MouseMotionListener, MouseListener {

	private Graphics2D g;
	private BufferStrategy buffer;
	private Map map;
	private Point2D mousePoint;
	private ArrayList<Message> messages = new ArrayList<Message>();
	private LinkedList<Enemy> enemies = new LinkedList<Enemy>();
	private ArrayList<Bullet> bullets = new ArrayList<Bullet>();
	private Tower[][] towerLayer;
	private String text = "";
	private int money;
	private boolean waveComing;
	private Button wave = new Button(70, 420, "Next wave");

	public Game() {

		// Window

		JFrame frame = new JFrame();
		frame.setTitle("BoxTD");
		frame.setSize(new Dimension(800, 600));
		JPanel panel = (JPanel) frame.getContentPane();
		panel.add(this);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		this.setBackground(Color.gray.darker());
		createBufferStrategy(2);
		buffer = getBufferStrategy();
		addMouseMotionListener(this);
		addMouseListener(this);
		mousePoint = new Point(0, 0);
		money = 20;
		// Entities

		map = new Map("map1.txt");
		towerLayer = new Tower[map.getWidth()][map.getHeight()];

		for (int i = 0; i < 11; i++) {

			LinkedList<Node> nodes = new LinkedList<Node>(map.getNodes());
			/*
			 * nodes.add(new Node(40, 100)); nodes.add(new Node(40, 220));
			 * nodes.add(new Node(200, 220)); nodes.add(new Node(320, 220));
			 */

			Enemy e = new EnemyLvl1(40, i - i * 40, 16, 16);
			e.setNodes(nodes);
			e.setvX(0.5f);
			e.setVy(0.5f);
			enemies.add(e);
		}

		// GO GO GO
		waveComing = true;
		run();
	}

	public void run() {

		Thread run = new Thread();
		boolean running = true;
		int frames = 0;
		float fps = 0;

		while (running) {
			long startTime = System.currentTimeMillis();
			if (!buffer.contentsLost()) {
				g = (Graphics2D) buffer.getDrawGraphics();
			}
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, this.getWidth(), this.getHeight());

			// Drawing methods
			map.drawMap(g);

			drawTowers(g);
			drawEnemies(g);
			drawBullets(g);
			drawMessages(g);
			g.setColor(Color.WHITE);
			g.drawString("fps:" + fps, 10, 10);
			g
					.drawString(text, (int) mousePoint.getX(), (int) mousePoint
							.getY());
			g.setColor(Color.yellow);
			g.drawString("Money: " + money, 10, 420);
			wave.draw(g);
			logic(g);
			// Drawing methods ends

			g.dispose();
			buffer.show();

			try {
				run.sleep(10);
			} catch (Exception ex) {

			}

			if (System.currentTimeMillis() - startTime <= 1000) {
				frames++;
			}
			long nyt = System.currentTimeMillis() - startTime;
			fps = frames * 1000f / nyt;
			frames = 0;

		}
	}

	public void logic(Graphics2D g) {

		if (enemies.isEmpty()) {
			waveComing = false;
			// System.out.println("wave ended");
			g.setColor(Color.RED);
			g.drawString("Press next wave button to continue", 100, 100);
			return;
		}
		if (!waveComing) {
			return;
		}
		checkCollisions();
	}

	public static void main(String[] args) {

		new Game();

	}

	public void mouseClicked(MouseEvent arg0) {

		/**
		 * Check if tower can be build, atm only one tower type can be built
		 */

		if (map.getBlocked((int) mousePoint.getX() / 20, (int) mousePoint
				.getY() / 20)) {

			Tower t = new BasicTower((int) mousePoint.getX() / 20,
					(int) mousePoint.getY() / 20, 20, 20, 100, 250, 5);

			if (towerLayer[(int) mousePoint.getX() / 20][(int) mousePoint
					.getY() / 20] == null
					&& money >= t.getCost()) {
				towerLayer[(int) mousePoint.getX() / 20][(int) mousePoint
						.getY() / 20] = t;
				money -= t.getCost();
			}

			/**
			 * Else, lets notify player, that tower can't be build here
			 */
		} else {
			text = "Tower can't be build here";
		}

	}

	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void mouseMoved(MouseEvent arg0) {
		mousePoint.setLocation(arg0.getX(), arg0.getY());

		
		  if (towerLayer[(int)mousePoint.getX() / 20][(int)mousePoint.getY() /
		                                             20] != null) { text = towerLayer[(int)mousePoint.getX() /
		  20][(int)mousePoint.getY() / 20].toString(); } else { text = ""; }
		 

	}

	public void drawTowers(Graphics2D g) {
		Tower t;
		for (int y = 0; y < map.getHeight(); y++) {
			for (int x = 0; x < map.getWidth(); x++) {
				if (towerLayer[y][x] != null) {
					t = towerLayer[y][x];
					t.draw(g);
					t.setTarget(enemies);

					if (t.tryToShoot() && t.getTarget() != null) {
						Bullet b = new Bullet(t.getXX(), t.getYY(), 2, 2);
						b.setDamage(t.getDamage());
						b.setDirection(t.getTarget().getXX(), t.getTarget()
								.getYY());
						bullets.add(b);

					}

				}
			}

		}
	}

	public void drawEnemies(Graphics2D g) {
		for (int i = 0; i < enemies.size(); i++) {
			if (enemies.get(i).isAlive()) {
				Enemy e = enemies.get(i);
				e.checkCollisionWithNode();
				e.draw(g);
				e.move();
			} else if (enemies.get(i).isAlive() == false) {
				Message m = new Message("$$ " + enemies.get(i).getBounty(),
						(int) enemies.get(i).getX(), (int) enemies.get(i)
								.getY());
				messages.add(m);
				money += enemies.get(i).getBounty();
				enemies.remove(i);

			}

		}
	}

	public void drawBullets(Graphics2D g) {
		for (Bullet b : bullets) {
			if (b.isAlive()) {
				b.draw(g);
				b.move();
			}

		}
	}

	public void drawMessages(Graphics2D g) {
		for (Message m : messages) {
			if (m.isAlive()) {
				m.draw(g);
			}
		}
	}

	public void checkCollisions() {

		/**
		 * Bullet vs enemy collision
		 */

		for (int i = 0; i < bullets.size(); i++) {
			for (int j = 0; j < enemies.size(); j++) {

				if (bullets.get(i) != null && enemies.get(j) != null) {
					Bullet b = bullets.get(i);
					Enemy e = enemies.get(j);

					if (e.getRect().contains(b.getRect()) && b.isAlive()) {
						e.collidesWith(b);
						b.setAlive(false);
					}
				}
			}
		}

	}

}
