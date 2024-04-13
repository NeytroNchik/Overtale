package com.game.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class BossEnemy extends GameObject {

	private Handler handler;
	
	private boolean isCollision = false;
	
	Random r = new Random();
	
	private int timer = 80;
	private int timer2 = 100;
	
	public BossEnemy(int x, int y, ID id, Handler handler) {
		
		super(x, y, id);		
		
		this.handler = handler;
		
		velX = 0;
		velY = 2;
	}
	
	public Rectangle getBounds() {
		
		return new Rectangle((int)x, (int)y, 64, 64);
	}

	public void tick() {
		
		x+=velX;
		y+=velY;
		
		if (x <=0) { 
			
			velX *=-1;
			isCollision = false;
		}
		
		if (x >= Game.WIDTH - 80) {
			
			velX *=-1;
			isCollision = true;
		}
		
		if (timer <= 0) velY = 0;		
		else timer--;
		
		if (timer <= 0) timer2--;
		
		if (timer2 <= 0) {
			
			if (velX == 0) velX = 2;
			
			if (velX > 0)	velX += 0.005f;
			
			else if (velX < 0)	velX -= 0.005f;
			
			velX = Game.clamp(velX, -5, 5);
			
			int spawn = r.nextInt(10);
			
			if(isCollision == false) {
				
				if (spawn == 0) handler.object.add(new BossEnemyBullet((int)x + 64, (int)y + 64, ID.BasicEnemy, handler));
			}
			
			if(isCollision == true) {
				
				if (spawn == 0) handler.object.add(new BossEnemyBullet((int)x , (int)y + 64, ID.BasicEnemy, handler));
			}
			
		} 
		
		
		
	}

	public void render(Graphics g) {
		
		g.setColor(Color.orange);
		g.fillRect((int)x, (int)y, 64, 64);
	}

}
