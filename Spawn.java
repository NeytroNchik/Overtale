package com.game.main;

import java.util.Random;

import com.game.main.Game.STATE;

public class Spawn {

	private Handler handler;
	private HUD hud;
	private int scoreKeep = 0;
	private Random r = new Random();
	private Game game;
	
	public Spawn (Handler handler, HUD hud, Game game) {
		
		this.handler = handler;
		this.hud = hud;
		this.game = game;
	}
	
	public void tick() {
		
		scoreKeep++;
		
		if (scoreKeep >= 500) {
			
			scoreKeep = 0;
			
			hud.setLevel(hud.getLevel() + 1);
			
			if (game.difficulty == 0) {
			
			if (hud.getLevel() == 2) handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
			
			else if (hud.getLevel() == 3) handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.FastEnemy, handler));
			
			else if (hud.getLevel() == 4) handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.FastEnemy, handler));			
			
			else if (hud.getLevel() == 5) handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.SmartEnemy, handler));
			
			else if (hud.getLevel() == 6) handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.SmartEnemy, handler));			
			
			if (hud.getLevel() == 8) {
				
				game.gameState = STATE.Win;
				handler.clearAll();				
			}
		}
			
			  else if (game.difficulty == 1) {
				
				if (hud.getLevel() == 2) handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
				
				else if (hud.getLevel() == 3) handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.FastEnemy, handler));
				
				else if (hud.getLevel() == 4) handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.FastEnemy, handler));
				
				else if (hud.getLevel() == 5) handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.FastEnemy, handler)); 
								
				else if (hud.getLevel() == 7) {
					
					handler.clearEnemys();
					handler.addObject(new BossEnemy((Game.WIDTH / 2) - 48, -96 , ID.BossEnemy, handler)); 
					handler.addObject(new CheetBoss(0, 0, ID.BasicEnemy, handler)); 
					}
				if (hud.getLevel() == 9) {
					
					game.gameState = STATE.Win;
					handler.clearAll();				
				}
				
				} 
			}			
		}
	}	

