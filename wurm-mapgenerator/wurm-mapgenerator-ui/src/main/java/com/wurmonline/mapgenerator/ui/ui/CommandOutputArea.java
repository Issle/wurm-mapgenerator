package com.wurmonline.mapgenerator.ui.ui;

import javax.swing.JTextArea;

public class CommandOutputArea extends JTextArea{

	private static final long serialVersionUID = 1L;

	public CommandOutputArea()
	{
		
	}
	
	public void append(String input, String output)
	{
		appendInput(input);
		appendOutput(output);
	}
	
	public void appendInput(String input)
	{
		append("Input:\n");
		append("> "+input+"\n");
	}
	
	public void appendOutput(String output)
	{
		append("Output:\n");
		append("> "+output+"\n");
	}
}
