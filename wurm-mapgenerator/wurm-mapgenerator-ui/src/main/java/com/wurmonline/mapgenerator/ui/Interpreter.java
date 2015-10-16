package com.wurmonline.mapgenerator.ui;

import java.io.StringWriter;
import java.util.Properties;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.python.util.PythonInterpreter;

public class Interpreter {

	private PythonInterpreter interp;
	private StringWriter out;
	
	public Interpreter()
	{
		PythonInterpreter.initialize(System.getProperties(),new Properties(), new String[0]);
		out = new StringWriter();
		interp = new PythonInterpreter();
		interp.setOut(out);
		interp.setErr(out);
	}
	
	public synchronized String exec(String command)
	{
		out.getBuffer().setLength(0);
		try
		{
			interp.exec(command);
		}
		catch(Exception e)
		{
			out.write(ExceptionUtils.getStackTrace(e));
		}
		return out.toString();
	}
	
	public void init()
	{
		exec("from com.wurmonline.mapgenerator.ui import Application;");
		exec("ui = Application.window;");
		exec("from com.wurmonline.mesh.Tiles import Tile");
	}
	
}
