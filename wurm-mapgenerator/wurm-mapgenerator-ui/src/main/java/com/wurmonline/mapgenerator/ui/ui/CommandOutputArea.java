package com.wurmonline.mapgenerator.ui.ui;

import javax.swing.JTextArea;
import javax.swing.text.DefaultCaret;

public class CommandOutputArea extends JTextArea{

	private static final long serialVersionUID = 1L;

	public CommandOutputArea()
	{
		DefaultCaret caret = (DefaultCaret) getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
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
