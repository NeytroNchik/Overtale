package com.game.main;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class GameObject {		 // ����������� �����, ������ ��� �������� ���������. �������� � ���� ������ ����������, ������, � ����� ������� � �������

	protected float x, y;   		  // ���������� ������� (���������� protected, ����� ����� ���� ���������������� �� � ������� �����������)
	protected ID id;			 // ������ ������ ���� ����� ���� ID
	protected float velX, velY;   // �������� �� ������������ y � x
	
	public GameObject(int x, int y, ID id) {
		
		this.x = x;
		this.y = y;
		this.id = id;
	}
	
	public abstract void tick();
	
	public abstract void render(Graphics g);
	
	public abstract Rectangle getBounds();
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public int getX() {
		return (int)x;
	}
	
	public int getY() {
		return (int)y;
	}
	
	public ID getId() {
		return id;		
	}
	
	public void setVelX(int velX) {
		this.velX = velX;
	}
	
	public void setVelY(int velY) {
		this.velY = velY;
	}
	
	public int getVelX(int velX) {
		return velX;
	}
	
	public int getVelY(int velY) {
		return velY;
	}
	
}
