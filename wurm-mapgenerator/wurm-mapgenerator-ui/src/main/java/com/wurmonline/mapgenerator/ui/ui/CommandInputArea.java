package com.wurmonline.mapgenerator.ui.ui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextArea;

import com.wurmonline.mapgenerator.ui.Interpreter;

public class CommandInputArea extends JTextArea{

	private static final long serialVersionUID = 1L;

	private Interpreter interpreter;
	private CommandOutputArea output;
	
	public CommandInputArea(Interpreter interpreter,CommandOutputArea output)
	{
		this.interpreter = interpreter;
		this.output = output;
		
		addKeyListener(new KeyListener(){

			@Override
			public void keyTyped(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER)
					setText("");	
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER)
				{
					String input = getText();
					output.appendInput(input);
					String result = interpreter.exec(input);
					output.appendOutput(result);
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER)
					setText("");
			}
		});
	}
	
	

}
