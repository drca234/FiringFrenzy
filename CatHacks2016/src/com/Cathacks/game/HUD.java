package com.Cathacks.game;

import java.awt.Color;
import java.awt.Graphics;

public class HUD {
	
	private static int MAX_HEALTH = 100;
	public static int HEALTH = MAX_HEALTH;
	private int healthbarColor = HEALTH*2;
	
	public void tick(){
		
		HEALTH = Game.clamp(HEALTH, 0, MAX_HEALTH);
		healthbarColor = (int) (HEALTH*2.5);
	}
	
	public void render(Graphics g){
		g.setColor(new Color(128, 200, 200, 128));
		g.fillRect(15, 15, MAX_HEALTH*2, 32);
		g.setColor(new Color(255-healthbarColor, healthbarColor, 0, 128));
		g.fillRect(16, 16, HEALTH*2-2, 30);
		
		g.drawString("Score: " + Game.spawner.getScore(), 15, 70);
		g.drawString("Level: " + Game.spawner.getLevel(), 15, 90);
	}
}
