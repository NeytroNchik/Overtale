package com.game.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.game.main.Game.STATE;
	
public class KeyInput extends KeyAdapter {			// класс, нужный для распознавания введённых пользователем клавиш
	
	private Handler handler;					// создаём новый распределитель
	
	private Game game;
	
	private boolean[] keyDown = new boolean[4];
	
	public KeyInput(Handler handler, Game game) {			// создаём конструктор
		
		this.handler = handler;
		this.game = game;
		
		keyDown[0] = false;		//w
		keyDown[1] = false;		//s
		keyDown[2] = false;		//d
		keyDown[3] = false;		//a
	}
	
	public void keyPressed(KeyEvent e) {		// если клавишу нгажали
		
		int key = e.getKeyCode();				// храним код введённой клавиши в этой переменной
		
		for (int i = 0; i<handler.object.size(); i++) {		// проходим по всему списку с игровыми объектами
			
			GameObject tempObject = handler.object.get(i);   // создаём временный объект, в котором хранится один из всех игровых объектов
			
			if (tempObject.getId() == ID.Player ) {				// если игровой объект - это игрок, то он реагирует на клавиши WASD				
				
				if (key == KeyEvent.VK_W) { tempObject.setVelY(-5); keyDown[0] = true; }
				if (key == KeyEvent.VK_S) { tempObject.setVelY(5); keyDown[1] = true;  }
				if (key == KeyEvent.VK_D) { tempObject.setVelX(5); keyDown[2] = true;  }
				if (key == KeyEvent.VK_A) { tempObject.setVelX(-5); keyDown[3] = true; }				
			}					
	}
		
		if (key == KeyEvent.VK_SPACE) {
			
			if (game.gameState == STATE.Game) {
			
			if (Game.paused) Game.paused = false;
			else Game.paused = true;
			}
		}
	}

	public void keyReleased(KeyEvent e) { 			//	если клавиша отпущена
		
		int key = e.getKeyCode();								 	   //
																	  //
		for (int i = 0; i<handler.object.size(); i++) {				 // аналогично предыдущему методу
																	//
			GameObject tempObject = handler.object.get(i);		   //
																  //
			if (tempObject.getId() == ID.Player) {				 // если игровой объект - это игрок, то он перестаёт двигаться 
				
				if (key == KeyEvent.VK_W) keyDown[0] = false;	 //tempObject.setVelY(0);
				if (key == KeyEvent.VK_S) keyDown[1] = false;	 //tempObject.setVelY(0);
				if (key == KeyEvent.VK_D) keyDown[2] = false;	 //tempObject.setVelX(0);	
				if (key == KeyEvent.VK_A) keyDown[3] = false;	 //tempObject.setVelX(0);
				
				
				if (!keyDown[0] && !keyDown[1])		tempObject.setVelY(0);
				if (!keyDown[2] && !keyDown[3])		tempObject.setVelX(0);
			}					
		}
	}	
}
