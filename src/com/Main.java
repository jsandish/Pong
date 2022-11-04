package com;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JFrame;

public class Main implements Runnable, KeyListener, MouseListener, MouseMotionListener{

	final int TICK_RATE = 75;
	final int TIME_BETWEEN_TICKS = 1000 / TICK_RATE;

	final int HEIGHT = 500, WIDTH = 700, HEIGHT_ERROR = 38, WIDTH_ERROR = 8;

	JFrame f;
	boolean[] keysPressed = {false, false, false, false, false};
	final int UP = 0, DOWN = 1, W = 2, S = 3, SPACE = 4;
	int[] mouseValues = {0, -1, -1};
	final int MOUSE_DOWN = 0;

	boolean gameActive = true;

	public Main() {
		intFrame();

		gameActive = true;
		this.run();

	}

	private void intFrame() {
		f = new JFrame("Pong");

		f.setSize(WIDTH + WIDTH_ERROR, HEIGHT + HEIGHT_ERROR);
		f.setLocationRelativeTo(null);
		f.setResizable(true);

		f.setFocusable(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.addKeyListener(this);
		f.addMouseListener(this);
		f.addMouseMotionListener(this);
		f.setVisible(true);
	}



	public static void main(String[] args) {
		new Main();
	}

	@Override
	public void run() {

		int[] scores = {0, 0};

		Window w;

		ArrayList<GameObject> objects = new ArrayList<GameObject>();

		Paddle leftPaddle = new Paddle(Paddle.LEFT, f, WIDTH, HEIGHT);
		objects.add(leftPaddle);
		Paddle rightPaddle = new Paddle(Paddle.RIGHT, f, WIDTH, HEIGHT);
		objects.add(rightPaddle);

		Ball ball = new Ball(f, WIDTH, HEIGHT);
		objects.add(ball);

		HUD hud = new HUD(WIDTH, HEIGHT, scores);

		long startTime = System.currentTimeMillis();
		int secondsPassed = 0;
		int tickCount = 0;
		int fps = 0;

		while(gameActive) {
			tickCount++;

			//Generate ticks per second
			if(System.currentTimeMillis() - startTime > (1000 * (secondsPassed + 1))) {
				secondsPassed++;
				fps = tickCount / secondsPassed;
			}
			f.setTitle("Pong (" + String.valueOf(fps) + " fps)");
			//System.out.println(f.getWidth());

			//Key actions
			if(keysPressed[SPACE] == true && ball.velX == 0) ball.velX = 3;
			if(mouseValues[MOUSE_DOWN] == 1 && ball.velX == 0) {
				ball.velX = 1;

				int val = (int) ((Math.random() - 0.5) * 5) + 1;
				if(val == 0) val += 1;
				ball.velY = val;
				System.out.println(val);
			}

			if(keysPressed[W]) leftPaddle.setVelY(-3);
			else if(keysPressed[S]) leftPaddle.setVelY(3);
			else leftPaddle.setVelY(0);

			leftPaddle.y = ball.y - 40;

			if(keysPressed[UP]) rightPaddle.setVelY(-3);
			else if(keysPressed[DOWN]) rightPaddle.setVelY(3);
			else rightPaddle.setVelY(0);

			//Human Player
			if(mouseValues[2] != -1) rightPaddle.y = mouseValues[2] - 60;
			
			//AI for both
			//rightPaddle.y = ball.y - 40;


			for(GameObject o:objects) {
				o.tickAction(objects, scores);
			}

			//Refresh graphics
			w = new Window(f, objects, hud, WIDTH, HEIGHT);

			f.add(w);
			f.revalidate();

			sleep(TIME_BETWEEN_TICKS);
			f.remove(w);
		}

		System.out.println("Game Stopped!");
		System.exit(0);

	}

	public void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		//System.out.println(e.getKeyCode());

		if(key == 38) keysPressed[UP] = true;
		else if(key == 40) keysPressed[DOWN] = true;
		else if(key == 87) keysPressed[W] = true;
		else if(key == 83) keysPressed[S] = true;
		else if(key == 32) keysPressed[SPACE] = true;

	}

	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		//System.out.println(e.getKeyCode());

		if(key == 38) keysPressed[UP] = false;
		else if(key == 40)keysPressed[DOWN] = false;
		else if(key == 87) keysPressed[W] = false;
		else if(key == 83) keysPressed[S] = false;
		else if(key == 32) keysPressed[SPACE] = false;
	}

	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		mouseValues[0] = 1;

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		mouseValues[0] = 0;
		mouseValues[1] = -1;
		mouseValues[2] = -1;
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mouseValues[1] = e.getX();
		mouseValues[2] = e.getY();
	}

}