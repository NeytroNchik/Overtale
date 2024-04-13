package com.game.main;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Trail extends GameObject {

	private int height, width;
	private float life;
	private float alpha = 1;
	private Handler handler;
	private Color color;
	
	public Trail(float x, float y,  ID id,  Color color,int height, int width, float life, Handler handler) {
		
		super((int)x, (int)y, id);
		this.color = color;
		this.height = height;
		this.width = width;
		this.life = life;
		this.handler = handler;
	}

	public void tick() {
		
		if (alpha > life) {
			alpha -= (life - 0.0001f);
		} else handler.removeObject(this);
	}

	public void render(Graphics g) {
		
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.setComposite(makeTransparent(alpha));
		
		g.setColor(color);
		g.fillRect((int)x,  (int)y,  height,  width);
		
		g2d.setComposite(makeTransparent(1));
	}
	
	private AlphaComposite makeTransparent (float alpha) {
		
		int type = AlphaComposite.SRC_OVER;
		
		return (AlphaComposite.getInstance(type, alpha));
	}

	public Rectangle getBounds() {

		return null;
	}

}
