package com.Cathacks.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

import javax.swing.ImageIcon;

public class BasicEnemyProjectile extends GameObject{
	BasicEnemyProjectile(int x, int y, ID id, int velX, int velY, BasicEnemy parent){
		super(x,y,id);
		this.velX = velX + new Random().nextInt(4)-2;
		this.velY = velY + 7;
		this.parent = parent;
	}

	private BasicEnemy parent;
	private String projectileFile = "C:\\Users\\Daniel\\Pictures\\ProjectileOrb.gif";
	private ImageIcon projectile = new ImageIcon(projectileFile);

	public void tick() {
		x += velX;
		y += velY;

		outOfBounds();
	}

	private void outOfBounds() {
		if(x >= Game.WIDTH + 50 || x <= -50) {
			Game.removeObject(this);
			parent.setCount(parent.getCount() - 1);
		}
		if(y >= Game.HEIGHT || y <= 0){
			parent.setCount(parent.getCount() - 1);
			Game.removeObject(this);
		}
	}
	
	public BasicEnemy getParent(){
		return parent;
	}
	public void render(Graphics g) {
		if(Game.debug){
			Graphics2D g2d = (Graphics2D) g;
			g.setColor(Color.cyan);
			g2d.draw(getBounds());
		}
		g.drawImage(projectile.getImage(), x, y, null);

	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, projectile.getIconWidth(), projectile.getIconHeight());
	}
}
