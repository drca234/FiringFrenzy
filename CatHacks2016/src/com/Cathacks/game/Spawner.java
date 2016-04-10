package com.Cathacks.game;

import java.util.Random;

public class Spawner {
	private int level = 1;
	private int score = 0;
	private Handler handler;
	private int enemiesSpawned = 0;
	
	Spawner(Handler handler){
		this.handler = handler;
	}
	
	public void tick(){
		if(enemiesSpawned < level){
		Game.addObject(new BasicEnemy(100 + new Random().nextInt(Game.WIDTH - 150), 100, ID.BasicEnemy, handler));	
		enemiesSpawned++;
		}
		if(score > level*level*100){
			level++;
		}
	}
	
	public void incrementScore(int score){
		this.score += score;
	}
	
	public int getLevel() {return level;}
	public int getScore() {return score;}

	public void decrementEnemiesSpawned() {
		enemiesSpawned--;		
	}
}
