package com.game.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;
	
	public static final int WIDTH = 640, HEIGHT = WIDTH / 12 * 9;    // ������ ���� (16:9)	
	
	private Thread thread;  // ������ ����� ����� ��� ����
	
	private boolean running = false; 	// ����������, ���������� �� ������ ������
	
	public static boolean paused = false;
	
	public int difficulty = 0;
	
	private Handler handler;  // ������ ������ Handler
	
	private HUD hud;
	
	private Spawn spawner;
	
	private Menu menu;
	
	private Random r;        // ���������� ��� ��������� ��������
	
	public enum STATE {
		
		Menu,
		Select,
		Help,
		Game,
		End,
		Win
	}; 
	
	public STATE gameState = STATE.Menu;
	
	public Game() {		// ������ 
		
		handler = new Handler();      // �������������� ���������� ���� Handler
		
		hud = new HUD();
		
		menu = new Menu(this, handler, hud);
		
		this.addKeyListener(new KeyInput(handler, this));  
		this.addMouseListener(menu);		
		
		spawner = new Spawn(handler, hud, this);
		
		r = new Random();
		
		new Window(WIDTH, HEIGHT, "Overtale", this);    // ������ ���� � ������ �������, ������ � ���������
		
		if (gameState == STATE.Game) {
			
			handler.addObject(new Player(WIDTH/2-32, HEIGHT/2-32, ID.Player, handler));    			// ��������� � ������� ������
		
			handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler)); 
		}	//else {
			
			//for (int i = 0; i<20; i++) handler.addObject(new MenuParticle(r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.FastEnemy, handler));
		//}	
	}
	
	public synchronized void start() {   // �����, ���������� �����. � ���� ������� ����� �������� ������ ���� ����� (�������)

		thread = new Thread(this); // ������������� ������
		thread.start();           // ����� ������
		running = true;          // ������ ����� �������
	}
	
	public synchronized void stop() {        // �����, ��������������� �����. � ���� ������� ����� �������� ������ ���� �����
		
		try {		 // ������������ ����������� ������ � ������� try catch
			
			thread.join();  		// ���������������� ���������� �������� ������ �� ��� ���, ���� �� ���������� ������ �����.
			running = false; 		
			
		} catch(Exception e) {			
			e.printStackTrace();   // ����� ���������� �����������
	}	
}	

	public void run() {           // ������� �����
		
		this.requestFocus();		
		long lastTime = System.nanoTime();		       // ����������, ���������� ����� � ������������ 
		double amountOfTicks = 60.0;                  // ���������� ����� � �������
		double ns = 1000000000 / amountOfTicks;      // ���������� ���������� � �����
		double delta = 0;                     
		long timer = System.currentTimeMillis();   // ����������, ���������� ����� � ������� ���������� �������
		int frames = 0;                 	      // ���������� ������ � �������
		
		while (running) {
			
			long now = System.nanoTime();	  // ����������, ���������� ����� ����� � ������������ 
			delta += (now - lastTime) / ns;  // �������� ��������� ������� � "�����"
			lastTime = now;                 // 
			
			while (delta >= 1) {   
				tick();            // �������� tick
				delta--;          // ��������� �������� delta, ���� ��� ������ 1
			}
			
			if (running) render();   // ���������� ���� ���� ���������� ����������
			
			frames++;        // ����������� ���������� ������		
			
			if (System.currentTimeMillis() - timer > 1000) {    //  ���������� ������� ������ � ������� ���� ��� � �������, ��������, 
			timer += 1000;                                     //   ��������� �� ������� ����� ����� ��� �� 1000 ����������� (1 �������) �������� "timer" 
			System.out.println("FPS: " + frames);             // ������� ���������� ������ � ������� � �������
			frames = 0;                                      // �������� ���������� ������ � ������� ��� ���������� �����
			}
		}
		stop();  				// ������������� ������� �����
	} 
	
	private void tick() {		
	
		if (gameState == STATE.Game) {
			
			if (!paused) {
				
			if (HUD.HEALTH <=0) {				
				
				HUD.HEALTH = 100;
				handler.clearAll();	
				gameState = STATE.End;
											
		}
			hud.tick();
			spawner.tick();
			handler.tick();						// �������� �� ����� ������ ������� ��������
	}
			}	else if (gameState == STATE.Menu || gameState == STATE.Help || gameState == STATE.End || gameState == STATE.Select || gameState == STATE.Win) { 
							
			HUD.HEALTH = 100;	
			menu.tick();
			handler.tick();	
		}	
}
	
	private void render() {						
		
		BufferStrategy bs = this.getBufferStrategy();   
		
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.black);				// ������������� ���� ����               
		g.fillRect(0, 0, WIDTH, HEIGHT);    // ��������� ���������� ���� ������ ������
		
		handler.render(g);
		
		if (paused) {
			
			g.setColor(Color.LIGHT_GRAY);
			g.drawString("PAUSED", 100, 100);			
		}
		
		if (gameState == STATE.Game) {
			
			hud.render(g);
			}	else if (gameState == STATE.Menu || gameState == STATE.Help || gameState == STATE.End || gameState == STATE.Select || gameState == STATE.Win)	menu.render(g);
		
		g.dispose(); // ������� ����������� ��������, ��� ������ �� ���� ������ �� ���������
		bs.show();		
	}
	
	public static float clamp (float var, float min, float max) {		// �������, ������������� ����������� �������� � ��������� ���������� var
		
		if (var>=max) return var = max;	
		
		else if (var<=min) return var = min;
		
		else return var;		
	}
	
	
	public static void main(String[] args) {
		
		new Game();  	// �������� ����
	}

}
