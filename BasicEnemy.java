package com.game.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class BasicEnemy extends GameObject {

	private Handler handler;
	
	public BasicEnemy(int x, int y, ID id, Handler handler) {
		
		super(x, y, id);		
		
		this.handler = handler;
		
		velX = 5;
		velY = 5;
	}
	
	public Rectangle getBounds() {
		
		return new Rectangle((int)x, (int)y, 16, 16);
	}

	public void tick() {
		
		x+=velX;
		y+=velY;
		
		if (y <= 0 || y >= Game.HEIGHT-48) velY*=-1;	// если враг находится вне окна или имеет отрицательную ординату, то его направление меняется на противоположное		
		if (x <= 0 || x >= Game.WIDTH-16) velX*=-1;	   // если враг находится вне окна или имеет отрицательную абсциссу, то его направление меняется на противоположное
		
		handler.addObject(new Trail((int)x, (int)y, ID.Trail, Color.red, 16, 16, 0.02f, handler));
	}

	public void render(Graphics g) {
		
		g.setColor(Color.red);
		g.fillRect((int)x, (int)y, 16, 16);
	}

}
