package com.game.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Player extends GameObject {		// класс нашего игрока

	private Handler handler;
	
	public Player(int x, int y, ID id, Handler handler) {		// создаём конструктор
		
		super(x, y, id);		
		this.handler = handler;
	}
	
	public Rectangle getBounds() {
		
		return new Rectangle((int)x, (int)y, 32, 32);
	}

	public void tick() {		// передвигаем нашего игрока
		
		x += velX;
		y += velY;
		
		x = Game.clamp((int)x, 0, Game.WIDTH-48);
		y = Game.clamp((int)y, 0, Game.HEIGHT-72);
		
		handler.addObject(new Trail(x, y, ID.Trail, Color.white, 32, 32, 0.08f, handler));
		
		collision();
	}
	
	public void collision() {
		
		for (int i = 0; i< handler.object.size(); i++) {
			
			GameObject tempObject = handler.object.get(i);
			
			if (tempObject.getId() == ID.BasicEnemy || tempObject.getId() == ID.FastEnemy || tempObject.getId() == ID.SmartEnemy || tempObject.getId() == ID.BossEnemy)  {
				
				if (getBounds().intersects(tempObject.getBounds())) {
					
					HUD.HEALTH -= 2;										
				}
			}			
		}		
	}
		
	public void render(Graphics g) {		// закрашиваем нашего игрока
				
		g.setColor(Color.white);
		g.fillRect((int)x,(int)y, 32, 32);
		
	}
}
