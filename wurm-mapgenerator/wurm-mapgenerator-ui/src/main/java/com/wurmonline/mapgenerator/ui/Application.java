package com.wurmonline.mapgenerator.ui;

import java.awt.EventQueue;

import com.wurmonline.mapgenerator.ui.ui.Window;

public class Application{
    
	public static Window window;
	
	public static void main(String[] args) {
		
		Interpreter interpreter = new Interpreter();
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new Window(interpreter);
					window.getFrame().setTitle("Wurm MapGenerator");
					window.getFrame().setVisible(true);
					interpreter.init();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
