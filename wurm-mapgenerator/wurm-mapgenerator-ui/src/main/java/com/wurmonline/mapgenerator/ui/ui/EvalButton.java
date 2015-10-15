package com.wurmonline.mapgenerator.ui.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import com.wurmonline.mapgenerator.ui.Interpreter;

public class EvalButton extends JButton{

	private static final long serialVersionUID = 1L;
	private CommandInputArea input;
	private CommandOutputArea output;
	private Interpreter interpreter;
	
	public EvalButton(String name, CommandInputArea input, CommandOutputArea output, Interpreter interpreter)
	{
		super(name);
		this.input = input;
		this.output = output;
		this.interpreter = interpreter;
		init();
	}
	
	private void init()
	{
		addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				String inputText = input.getText();
				String lines[] = inputText.split("\\r?\\n");
				for(String line: lines)
				{
					output.appendInput(line);
					String result = interpreter.exec(line);
					output.appendOutput(result);
				}
			}
			
		});
	}
	
	
}
