package com.Cathacks.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class PlayerProjectileLaser extends GameObject{
	PlayerProjectileLaser(int x, int y, ID id, int velX, int velY){
		super(x,y,id);
		this.velX = velX;
		this.velY = velY + 5;
	}

	private String projectileFile = "C:\\Users\\Daniel\\Pictures\\Ship2.gif";
	private ImageIcon projectile = new ImageIcon(projectileFile);
	
	public void tick() {
		x += velX;
		y += velY;
		
		if(x >= Game.WIDTH || x <= 0) Game.removeObject(this);
		if(y >= Game.HEIGHT || y <= 0) Game.removeObject(this);
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.cyan);
		g.drawImage(projectile.getImage(), x, y, null);
		
	}

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return null;
	}
}
