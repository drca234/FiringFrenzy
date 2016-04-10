package com.Cathacks.game;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements Runnable{

	private static final long serialVersionUID = -1442798787354930462L;
	public static boolean debug = false;

	public static final int WIDTH = 1024, HEIGHT = WIDTH / 12*9; 
	public static int xRatio, yRatio, tickCount;
	
	private Thread thread;
	private boolean running = false;
	
	static Spawner spawner;
	private static Handler handler;
	private HUD hud;
	
	public Game(){
		handler = new Handler();
		this.addKeyListener(new KeyInput(handler));
		
		new Window(WIDTH, HEIGHT, "CatHacks2016", this);
	
		handler.addObject(new Player(WIDTH/2, HEIGHT*5/6, ID.Player, handler));
		spawner = new Spawner(handler);
		hud = new HUD();
	}
	
	public static void addObject(GameObject object){
		synchronized (handler) {
			handler.addObject(object);
		}
	}
	public static void removeObject(GameObject object){
		synchronized (handler) {
			handler.removeObject(object);
		}
	}
	
	public static void main(String[] args) {
		new Game();
	}

	public void run() {
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1_000_000_000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while(running){
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1){
				tick();
				tickCount++;
				delta--;
			}
			if(running)
				render();
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				System.out.println("FPS: " + frames);
				frames = 0;
			}
			if(HUD.HEALTH == 0){
				
				
			}
		}
		stop();
	}
	
	private void render(){
		BufferStrategy buffStrat = this.getBufferStrategy();
		if(buffStrat == null){
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = buffStrat.getDrawGraphics();
		
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		handler.render(g);
		if (hud != null) hud.render(g);
		
		g.dispose();
		buffStrat.show();
		
	}
	
	private void tick(){
		handler.tick();
		if(hud != null) hud.tick();
		if(tickCount % 300 == 0 && spawner != null) spawner.tick();
	}
	
	
	
	public synchronized void start(){
		thread = new Thread(this);
		thread.start();
		running = true;
	}

	public synchronized void stop(){
		try{
			thread.join();
			running = false;
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static int clamp(int toClamp, int min, int max){
		//checks input lower than max
		int clamped = min > toClamp ? min : toClamp;
		//checks input higher than max
		return max < clamped ? max : clamped;
	}
}
