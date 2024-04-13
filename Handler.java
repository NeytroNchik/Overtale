package com.game.main;

import java.awt.Graphics;
import java.util.LinkedList;

public class Handler {			//класс, нужный дл€ распределени€ игровых объекетов

	LinkedList<GameObject> object = new LinkedList<GameObject>();		// создаЄм список игровых объектов
	
	public void tick() {												// метод, проход€щий по всему списку
		
		for (int i = 0;i<object.size();i++) {
			
			GameObject tempObject = object.get(i);
			tempObject.tick();			
		}		
	}
	
	public void render(Graphics g) {					// метод, добавл€ющий каждому объекту из списка графику
		
		for (int i = 0; i<object.size(); i++) {
			
			GameObject tempObject = object.get(i);
			tempObject.render(g);			
		}				
	}
	
	public void addObject(GameObject object) {		// метод, нужный дл€ добавлени€ объекта в список
		
		this.object.add(object);
	}
	
	public void removeObject(GameObject object) {		// метод, нужный дл€ удалени€ объекта из списка
		
		this.object.remove(object);
	}

	public void clearEnemys() {
	
		for (int i = 0; i<object.size(); i++) {
			
			GameObject tempObject = object.get(i);
			
			if (tempObject.getId() == ID.Player) {
				
				object.clear();
				addObject(new Player((int) tempObject.getX(), (int) tempObject.getY(), ID.Player, this));
			}			
		}		
	}
	
	public void clearAll () {	object.clear();	}
}
