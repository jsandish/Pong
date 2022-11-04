package com;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.JFrame;

public class Ball extends GameObject{

	final int BALL_DIAMETER = 8;
	
	int height, width;
	
	ArrayList<GameObject> objects;
	
	public Ball(JFrame f, int width, int height) {
		this.height = height;
		this.width = width;
		
		resetBall();
		
		
	}
	
	
	@Override
	public void tickDraw(Graphics g) {
		g.setColor(Color.white);
		g.fillOval(x, y, BALL_DIAMETER * 2, BALL_DIAMETER * 2);
		
	}

	@Override
	public void tickAction(ArrayList<GameObject> objects, int[] scores) {
		x += velX;
		y += velY;
		
		
		
		int leftPaddleX = objects.get(0).x, leftPaddleY = objects.get(0).y;
		int rightPaddleX = objects.get(1).x, rightPaddleY = objects.get(1).y;
		
		
		if(x <= 12) {
			if(leftPaddleY <= y && leftPaddleY + 80 >= y) {
				velX *= -1;
				System.out.println("hit");
				if(Math.random() > 0.5) {
					speedUpBall(1);
				}
			}
			else {
				scores[1]++;
				resetBall();
			}
		}
		else if(x >= 664) {
			if(rightPaddleY <= y && rightPaddleY + 80 >= y) {
				velX *= -1;
				System.out.println("hit");
				if(Math.random() > 0.5) {
					speedUpBall(1);
				}
			}
			else {
				scores[0]++;
				resetBall();
			}
		}
		
		if(y <= 0 || y + BALL_DIAMETER >= height) velY *= -1;
		
	}

	public void speedUpBall(int val) {
		if(velX < 0) velX -= val;
		else velX += val;
		System.out.println(velX);
		
	}
	
	public void resetBall() {
		stopBall();
		x = (width / 2) - (BALL_DIAMETER / 2);
		y = (height / 2) - (BALL_DIAMETER / 2);
	}
	
	public void stopBall() {
		velX = 0;
		velY = 0;
	}
}