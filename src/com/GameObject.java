package com;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

public abstract class GameObject {
	
	int x = 0, y = 0, velX = 0, velY = 0;
	
	public abstract void tickDraw(Graphics g);
	
	public abstract void tickAction(ArrayList<GameObject> objects, int[] scores);
	
	
}