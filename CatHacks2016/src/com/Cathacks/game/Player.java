package com.Cathacks.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Player extends GameObject{

	public Player(int x, int y, ID id, Handler handler){
		super(x, y, id);
		this.handler = handler;

	}
	private Handler handler;
	private String shipFile = "./ship1.gif";
	private ImageIcon ship = new ImageIcon(shipFile);
	private int deathCountdown = 160;
	private int projectileCount = 0;

	public void tick() {
		x += velX;
		y += velY;
		x = Game.clamp(x, 0, Game.WIDTH-71);
		y = Game.clamp(y, 0, Game.HEIGHT-100);
		for(GameObject object : handler.objectList){
			if(object instanceof BasicEnemyProjectile){
				if(object.getBounds().intersects(this.getBounds())){
					HUD.HEALTH -= 10;
					((BasicEnemyProjectile) object).getParent().decrementProjectileCount();
					handler.removeObject(object);
				}
			}
			else if(object instanceof BasicEnemy){
				if(object.getBounds().intersects(this.getBounds())){
				HUD.HEALTH -= 2;
				}
			}
		}
		shipStatus();
	}

	public void render(Graphics g) {
		if(Game.debug){
			Graphics2D g2d = (Graphics2D) g;
			g.setColor(Color.cyan);
			g2d.draw(getBounds());
		}
		g.drawImage(ship.getImage(), x, y, null);
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, ship.getIconWidth(), ship.getIconHeight());
	}

	public void shipStatus(){
		if(HUD.HEALTH == 0){
			ship = new ImageIcon(new String ("./ship1Exploding.gif"));
			deathCountdown--;
			if(deathCountdown == 0){
				handler.removeObject(this);
				handler.addObject(new Message(Game.HEIGHT/2+100, Game.WIDTH/2-100, ID.Message, "GAME OVER"));
			}
		}
	}
	
	public void fireProjectile(){
		if(projectileCount < 5){
			Game.addObject(new PlayerLaser(x, y, ID.EnemyProjectile, velX, velY, this));
			projectileCount++;
		}
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
