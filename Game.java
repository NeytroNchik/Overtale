package com.game.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;
	
	public static final int WIDTH = 640, HEIGHT = WIDTH / 12 * 9;    // размер окна (16:9)	
	
	private Thread thread;  // создаём новый поток для игры
	
	private boolean running = false; 	// переменная, отвечающая за работу потока
	
	public static boolean paused = false;
	
	public int difficulty = 0;
	
	private Handler handler;  // объект класса Handler
	
	private HUD hud;
	
	private Spawn spawner;
	
	private Menu menu;
	
	private Random r;        // переменная для случайных значений
	
	public enum STATE {
		
		Menu,
		Select,
		Help,
		Game,
		End,
		Win
	}; 
	
	public STATE gameState = STATE.Menu;
	
	public Game() {		// создаём 
		
		handler = new Handler();      // инициализируем переменную типа Handler
		
		hud = new HUD();
		
		menu = new Menu(this, handler, hud);
		
		this.addKeyListener(new KeyInput(handler, this));  
		this.addMouseListener(menu);		
		
		spawner = new Spawn(handler, hud, this);
		
		r = new Random();
		
		new Window(WIDTH, HEIGHT, "Overtale", this);    // создаём окно с нужной высотой, длиной и названием
		
		if (gameState == STATE.Game) {
			
			handler.addObject(new Player(WIDTH/2-32, HEIGHT/2-32, ID.Player, handler));    			// добавляем в объекты игрока
		
			handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler)); 
		}	//else {
			
			//for (int i = 0; i<20; i++) handler.addObject(new MenuParticle(r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.FastEnemy, handler));
		//}	
	}
	
	public synchronized void start() {   // метод, стартующий поток. С этим методом может работать только один поток (мьютекс)

		thread = new Thread(this); // инициализация потока
		thread.start();           // старт потока
		running = true;          // теперь поток запущен
	}
	
	public synchronized void stop() {        // метод, останавливающий поток. С этим методом может работать только один поток
		
		try {		 // обрабатываем приостановк потока с помощью try catch
			
			thread.join();  		// приостанавливаем выполнение текущего потока до тех пор, пока не завершится другой поток.
			running = false; 		
			
		} catch(Exception e) {			
			e.printStackTrace();   // какое исключение выбросилось
	}	
}	

	public void run() {           // игровой поток
		
		this.requestFocus();		
		long lastTime = System.nanoTime();		       // переменная, замеряющая время в наносекундах 
		double amountOfTicks = 60.0;                  // количество тиков в секунду
		double ns = 1000000000 / amountOfTicks;      // количество наносекунд в цикле
		double delta = 0;                     
		long timer = System.currentTimeMillis();   // переменная, замеряющая время с помощью системного времени
		int frames = 0;                 	      // количество кадров в секунду
		
		while (running) {
			
			long now = System.nanoTime();	  // переменная, замеряющая время цикла в наносекундах 
			delta += (now - lastTime) / ns;  // замеряем изменение времени в "тиках"
			lastTime = now;                 // 
			
			while (delta >= 1) {   
				tick();            // вызываем tick
				delta--;          // уменьшаем значение delta, если оно больше 1
			}
			
			if (running) render();   // обновление окна путём повторного рендеринга
			
			frames++;        // увеличиваем количество кадров		
			
			if (System.currentTimeMillis() - timer > 1000) {    //  записывает частоту кадров в секунду один раз в секунду, проверяя, 
			timer += 1000;                                     //   превышает ли текущее время более чем на 1000 миллисекунд (1 секунда) значение "timer" 
			System.out.println("FPS: " + frames);             // выводим количество кадров в секунду в консоль
			frames = 0;                                      // обнуляем количество кадров в секунду для следующего цикла
			}
		}
		stop();  				// останавливаем игровой поток
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
			handler.tick();						// проходим по всему списку игровых объектов
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
		
		g.setColor(Color.black);				// устанавливаем цвет окна               
		g.fillRect(0, 0, WIDTH, HEIGHT);    // заполняем содержимое окна чёрным цветом
		
		handler.render(g);
		
		if (paused) {
			
			g.setColor(Color.LIGHT_GRAY);
			g.drawString("PAUSED", 100, 100);			
		}
		
		if (gameState == STATE.Game) {
			
			hud.render(g);
			}	else if (gameState == STATE.Menu || gameState == STATE.Help || gameState == STATE.End || gameState == STATE.Select || gameState == STATE.Win)	menu.render(g);
		
		g.dispose(); // удаляем графический контекст, как только на него больше не ссылаются
		bs.show();		
	}
	
	public static float clamp (float var, float min, float max) {		// функция, недопускающая пересечения минимума и максимума переменной var
		
		if (var>=max) return var = max;	
		
		else if (var<=min) return var = min;
		
		else return var;		
	}
	
	
	public static void main(String[] args) {
		
		new Game();  	// стартуем игру
	}

}
