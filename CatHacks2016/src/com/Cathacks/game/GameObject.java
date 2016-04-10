package com.Cathacks.game;

import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public abstract class GameObject implements Comparable<GameObject>{

	protected int x, y;
	protected ID id;
	protected int velX, velY;
	protected static int globalID = 0;
	protected int objectID;

	public GameObject(int x, int y, ID id){
		this.x = x;
		this.y = y;
		this.id = id;
		this.objectID = ++globalID;
	}

	public abstract void tick();
	public abstract void render(Graphics g);
	public abstract Rectangle getBounds();

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public ID getId() {
		return id;
	}

	public void setId(ID id) {
		this.id = id;
	}

	public int getVelX() {
		return velX;
	}

	public void setVelX(int velX) {
		this.velX = velX;
	}

	public int getVelY() {
		return velY;
	}

	public void setVelY(int velY) {
		this.velY = velY;
	}

	public int compareTo(GameObject object){
		if(this.objectID < object.objectID) return -1;
		if(this.objectID == object.objectID) return 0;
		return 1;
	}
}

