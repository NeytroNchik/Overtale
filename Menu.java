package com.game.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import com.game.main.Game.STATE;

public class Menu extends MouseAdapter {
	
	private Game game;
	private Handler handler;
	private Random r = new Random();
	private HUD hud;
	
	public Menu(Game game, Handler handler, HUD hud) {
		
		this.game = game;
		this.handler = handler;
		this.hud = hud;
	}
	
	public void mousePressed(MouseEvent e) {
		
		int mx = e.getX();
		int my = e.getY();
		
		if (game.gameState == STATE.Menu) {
		
		if (mouseOver (mx, my, 210, 150, 200, 64)) {
			
			/*game.gameState = STATE.Game;
			
			handler.addObject(new Player(Game.WIDTH/2-32, Game.HEIGHT/2-32, ID.Player, handler));    		
			
			handler.clearEnemys();
			
			handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler)); */
			
			game.gameState = STATE.Select;
			return;
		}
		
		if (mouseOver(mx, my, 210, 250, 200, 64)) game.gameState = STATE.Help;
		
		if (mouseOver (mx, my, 210, 350, 200, 64)) System.exit(1);		
		}
		
		if (game.gameState == STATE.Select) {
			
			if (mouseOver (mx, my, 210, 150, 200, 64)) {
				
				handler.clearAll();
				
				game.gameState = STATE.Game;			
								
				handler.addObject(new Player(Game.WIDTH/2-32, Game.HEIGHT/2-32, ID.Player, handler));    						
								
				handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler)); 
				
				game.difficulty = 0;				
			}
			
			if (mouseOver(mx, my, 210, 250, 200, 64)) { 
				
				handler.clearAll();
				
				game.gameState = STATE.Game;							
				
				handler.addObject(new Player(Game.WIDTH/2-32, Game.HEIGHT/2-32, ID.Player, handler)); 		
								
				handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler)); 
				
				game.difficulty = 1; 
				}
			
				if (mouseOver (mx, my, 210, 350, 200, 64)) { game.gameState = STATE.Menu; return; }		
			}
	
	if (game.gameState == STATE.Help && mouseOver (mx, my, 10, 350, 200, 64)) {
				
			game.gameState = STATE.Menu;
			return;
	}
	
	if (game.gameState == STATE.End && mouseOver (mx, my, 30, 350, 400, 64)) {
		
		//for (int i = 0; i<20; i++)	handler.addObject(new MenuParticle(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.FastEnemy, handler));
				
		hud.setScore(0);
		hud.setLevel(1);		
		game.gameState = STATE.Menu;
		return;
	}	
	
	if (game.gameState == STATE.Win && mouseOver (mx, my, 30, 350, 400, 64)) {
		
		//for (int i = 0; i<20; i++)	handler.addObject(new MenuParticle(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.FastEnemy, handler));
				
		hud.setScore(0);
		hud.setLevel(1);		
		game.gameState = STATE.Menu;
		return;
	}
}	
	public void mouseReleased(MouseEvent e) {}
	
	public boolean mouseOver (int mx, int my, int x, int y, int width, int height) {
		
		if (mx > x && mx < x+ width && my > y && my < y+ height) return true;
		else return false;		
	}
	
	public void tick() {}
	
	public void render(Graphics g) {
		
		if (game.gameState == STATE.Menu) {
		
		Font fnt = new Font ("Lucida Sans Typewriter", 1, 64);
		
		g.setFont(fnt);
		g.setColor(Color.red);
		g.drawString("Overtale", 160, 75);
		
		Font fnt2 = new Font ("Lucida Sans Typewriter", 2, 48);
		
		g.setFont(fnt2);
		g.setColor(Color.white);
		g.drawString("Menu", 250, 130);
		
		
		Font fnt3 = new Font ("Lucida Sans Typewriter", 0, 48);
		
		g.setFont(fnt3);
		g.setColor(Color.white);
		g.drawString("Play", 250, 200);
		
		g.setFont(fnt3);
		g.setColor(Color.white);
		g.drawString("Help", 250, 300);
		
		g.setFont(fnt3);
		g.setColor(Color.white);
		g.drawString("Exit", 250, 400);
		
		g.setColor(Color.white);
		g.drawRect(210, 150, 200, 64);
		
		g.setColor(Color.white);
		g.drawRect(210, 250, 200, 64);
		
		g.setColor(Color.white);
		g.drawRect(210, 350, 200, 64);
		
		}
		
		else if (game.gameState == STATE.Select) {
			
			Font fnt = new Font ("Lucida Sans Typewriter", 1, 64);
			
			g.setFont(fnt);
			g.setColor(Color.red);
			g.drawString("Overtale", 160, 75);
			
			Font fnt2 = new Font ("Lucida Sans Typewriter", 2, 48);
			
			g.setFont(fnt2);
			g.setColor(Color.white);
			g.drawString("Difficulty", 170, 130);
			
			
			Font fnt3 = new Font ("Lucida Sans Typewriter", 0, 48);
			
			g.setFont(fnt3);
			g.setColor(Color.white);
			g.drawString("Normal", 230, 200);
			
			g.setFont(fnt3);
			g.setColor(Color.white);
			g.drawString("Hard", 250, 300);
			
			g.setFont(fnt3);
			g.setColor(Color.white);
			g.drawString("Back", 250, 400);
			
			g.setColor(Color.white);
			g.drawRect(210, 150, 200, 64);
			
			g.setColor(Color.white);
			g.drawRect(210, 250, 200, 64);
			
			g.setColor(Color.white);
			g.drawRect(210, 350, 200, 64);
			
			}
		
		else if (game.gameState == STATE.Help) {
			
			Font font = new Font("arial", 0, 20);
			
			g.setFont(font);
			g.setColor(Color.white);			
			g.drawString("Добро пожаловать в Overtale! Вы играете за белый квадратик.", 30, 110);
			g.drawString("Чтобы выжить, вам нужно уварачиаться от врагов.", 30, 130);
			g.drawString("Делать это можно при помощи клавиш A, S, W, D.", 30, 150);
			g.drawString("Чтобы поставить игру на паузу, нажмите на пробел.", 30, 170);
			g.drawString("Следы врагов не отнимают урон.", 30, 190);
			g.drawString("В режиме сложности Hard доступна битва с боссом (в конце).", 30, 210);
			
			Font fnt = new Font ("Lucida Sans Typewriter", 1, 64);
			
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("Help", 230, 75);
			
			Font fnt2 = new Font ("Lucida Sans Typewriter", 0, 48);
			
			g.setFont(fnt2);
			g.setColor(Color.white);
			g.drawString("Back", 50, 400);
			g.drawRect(10, 350, 200, 64);
			
			
			
		}		
		
		else if (game.gameState == STATE.End) {	
			
			Font fnt = new Font ("Lucida Sans Typewriter", 1, 64);
			
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("Game Over :(", 80, 75);
			
			Font fnt2 = new Font ("Lucida Sans Typewriter", 0, 48);
			
			g.setFont(fnt2);
			g.setColor(Color.white);
			g.drawString("Back to Menu", 50, 400);
			
			Font fnt3 = new Font ("Lucida Sans Typewriter", 0, 32);
			
			g.setFont(fnt3);
			g.setColor(Color.white);
			g.drawString("Your score: " + hud.getScore(), 150, 170);
						
			g.setColor(Color.white);
			g.drawRect(30, 350, 400, 64);
		
		}
		
		else if (game.gameState == STATE.Win) {	
			
			Font fnt = new Font ("Lucida Sans Typewriter", 1, 64);
			
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("You win!", 150, 75);
			
			Font fnt2 = new Font ("Lucida Sans Typewriter", 0, 48);
			
			g.setFont(fnt2);
			g.setColor(Color.white);
			g.drawString("Back to Menu", 50, 400);
			
			Font fnt3 = new Font ("Lucida Sans Typewriter", 0, 32);
			
			g.setFont(fnt3);
			g.setColor(Color.white);
			g.drawString("Your score: " + hud.getScore(), 150, 170);
						
			g.setColor(Color.white);
			g.drawRect(30, 350, 400, 64);
		
		}
	}
}
