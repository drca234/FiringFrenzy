package com.Cathacks.game;

import java.awt.Graphics;
import java.util.concurrent.ConcurrentSkipListSet;

public class Handler {
	ConcurrentSkipListSet<GameObject> objectList = new ConcurrentSkipListSet< GameObject >();
	
	public void tick(){
		for(GameObject object : objectList){
			object.tick();
		}
	}
	
	public void render(Graphics g){
		for(GameObject object : objectList){
			object.render(g);
		}
	}
	
	public void addObject(GameObject object){
		this.objectList.add(object);
	}
	
	public void removeObject(GameObject object){
		this.objectList.remove(object);
	}
	
}
