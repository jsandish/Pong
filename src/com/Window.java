package com;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Window extends JPanel {

	JFrame f;
	ArrayList<GameObject> objects;
	HUD hud;
	
	int width, height;
	
	public Window(JFrame f, ArrayList<GameObject> objects, HUD hud, int width, int height) {
		this.f = f;
		this.objects = objects;
		this.hud = hud;
		
		this.height = height;
		this.width = width;
		
		this.setSize(width, height);
	}
	
	public void paintComponent(Graphics g) {
	    int width = getWidth();
	    int height = getHeight();
	    
		g.setColor(Color.black);
	    
	    g.fillRect(0, 0, f.getWidth() + f.getInsets().right, f.getHeight() + f.getInsets().bottom + f.getInsets().top);
	    
	    
	    
	    
	    hud.draw(g);
	    
	    for(GameObject o:objects) {
	    	o.tickDraw(g);
	    }
	     
	    
	  }

	
}