package com;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.JFrame;

public class Paddle extends GameObject{

	final static boolean LEFT = false;
	final static boolean RIGHT = true;
	final int PADDLE_WIDTH = 12, PADDLE_HEIGHT = 80;
	
	int height, width;
	
	public Paddle(boolean side, JFrame f, int width, int height) {
		this.width = width;
		this.height = height;
		
		if(side == LEFT) {
			x = 0;
			y = (height / 2) - (PADDLE_HEIGHT / 2);
		}
		else {
			x = 700 - PADDLE_WIDTH * 2;
			y = (height / 2) - (PADDLE_HEIGHT / 2);
		}
	}
	
	public void setVelY(int velY) {
		this.velY = velY;
	}
	
	@Override
	public void tickDraw(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(x, y, PADDLE_WIDTH, PADDLE_HEIGHT);
	}

	@Override
	public void tickAction(ArrayList<GameObject> objects, int[] scores) {
		x += velX;
		
		y += velY;
		if(y < 0) y = 0;
		else if(y + PADDLE_HEIGHT > height) y = height - PADDLE_HEIGHT;
		
	}
	
}