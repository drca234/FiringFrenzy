package com.Cathacks.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Message extends GameObject{
	
	private String message;
	
	public Message(int x, int y, ID id, String message) {
		super(x, y, id);
		this.message = message;
	}


	public void tick() {}

	public void render(Graphics g) {
		g.setColor(Color.cyan);
		g.drawString(message, x, y);
		
	}

	@Override
	public Rectangle getBounds() {
		return null;
	}

}
