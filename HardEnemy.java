package com.game.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class HardEnemy extends GameObject {

	private Handler handler;
	
	public HardEnemy(int x, int y, ID id, Handler handler) {
		
		super(x, y, id);		
		
		this.handler = handler;
		
		velX = 8;
		velY = 8;
	}
	
	public Rectangle getBounds() {
		
		return new Rectangle((int)x, (int)y, 16, 16);
	}

	public void tick() {
		
		x+=velX;
		y+=velY;
		
		if (y <= 0 || y >= Game.HEIGHT-48) velY*=-1;	// ���� ���� ��������� ��� ���� ��� ����� ������������� ��������, �� ��� ����������� �������� �� ���������������		
		if (x <= 0 || x >= Game.WIDTH-16) velX*=-1;	   // ���� ���� ��������� ��� ���� ��� ����� ������������� ��������, �� ��� ����������� �������� �� ���������������
		
		handler.addObject(new Trail((int)x, (int)y, ID.Trail, Color.magenta, 16, 16, 0.02f, handler));
	}

	public void render(Graphics g) {
		
		g.setColor(Color.magenta);
		g.fillRect((int)x, (int)y, 16, 16);
	}

}
