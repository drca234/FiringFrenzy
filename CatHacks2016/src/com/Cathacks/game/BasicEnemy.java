package com.Cathacks.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.util.Random;

import javax.swing.ImageIcon;

public class BasicEnemy extends GameObject{

	private Handler handler;
	public BasicEnemy(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		velX = 5;
		this.handler = handler;
	}

	private String enemyFile = "C:\\Users\\Daniel\\Pictures\\XenemyIdle1.gif";
	private ImageIcon enemy = new ImageIcon(enemyFile);
	private int projectileCount = 0;
	private int health = 20;

	public void tick() {
		x += velX;
		y += velY;
		int currentDecision = new Random().nextInt(10);

		if(x <=0 || x >= Game.WIDTH - 23) velX *= -1;
		if(y <=0 || y >= Game.WIDTH - 16) velY *= -1;
		if(currentDecision == 9 && projectileCount < 3){
			projectileCount++;
			fireProjectile();
		}
		for(GameObject object : handler.objectList){
			if(object instanceof PlayerLaser){
				if(object.getBounds().intersects(this.getBounds())){
					this.health -= 10;
					((PlayerLaser) object).getParent().decrementProjectileCount();
					handler.removeObject(object);
				}
			}
			else if(object instanceof Player){
				if(object.getBounds().intersects(this.getBounds())){
					this.health -= 2;
				}
			}
		}
		shipStatus();
	}

	public void shipStatus(){
		if(this.health <= 0){
			handler.removeObject(this);
			Game.spawner.incrementScore(100);
			Game.spawner.decrementEnemiesSpawned();
		}
	}

	public void fireProjectile(){
		Game.addObject(new BasicEnemyProjectile(x, y, ID.EnemyProjectile, velX, velY, this));
	}

	public void render(Graphics g) {
		if(Game.debug){
			Graphics2D g2d = (Graphics2D) g;
			g.setColor(Color.cyan);
			g2d.draw(getBounds());
		}
		g.drawImage(enemy.getImage(), x, y, null);
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, enemy.getIconWidth(), enemy.getIconHeight());
	}

	public void setCount(int count){
		projectileCount = count;
	}

	public int getCount(){
		return projectileCount;
	}

	public void decrementProjectileCount(){
		this.projectileCount--;
	}

}
