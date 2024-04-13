package com.game.main;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Window extends Canvas {

	private static final long serialVersionUID = 1L;

	public Window(int width, int height, String title, Game game) {
		
		JFrame frame = new JFrame(title); // создаём окно с заголовком		
		
		frame.setPreferredSize(new Dimension(width, height));     // устанавливаем предпочтительный размер окна
		frame.setMaximumSize(new Dimension(width, height));	     // устанавливаем максимальный размер окна
		frame.setMinimumSize(new Dimension(width, height));     // устанавливаем минимальный размер окна		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //что будет после закрытия окна (константа EXIT_ON_CLOSE отвечает за полное закрытие окна)
		frame.setResizable(false);                            // пользователь не может изменять размер окна
		frame.setLocationRelativeTo(null);                   // размещение окна по середине
		frame.add(game);                                    // добавляем игру в это окно
		frame.setVisible(true);                            // регулировка видимости окна
		game.start();                                     //запускаем игру		
	}
	
	
}
