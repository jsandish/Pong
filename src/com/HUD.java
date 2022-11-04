package com;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

public class HUD{

	int[] scores;
	int height, width;
	
	public HUD(int width, int height, int[] scores) {
		this.scores = scores;
		
		this.width = width;
		this.height = height;
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.white);
		g.setFont(new Font("Courier", Font.BOLD, 24));
		
		g.drawString(String.valueOf(scores[0]), width/3, 25);
		g.drawString(String.valueOf(scores[1]), (2 * (width / 3)) - 10, 25);
		
		int rectHeight = 25, rectWidth = 6;
		for(int y = 0; y < height; y += rectHeight * 2) {
			g.fillRect((width / 2) - (rectWidth / 2), y, rectWidth, rectHeight);
		}
		
	}


}