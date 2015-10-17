package com.wurmonline.mapgenerator.ui.ui;

import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

import org.piccolo2d.extras.pswing.PSwingCanvas;
import org.piccolo2d.nodes.PImage;

import com.wurmonline.mapgenerator.core.util.ImageUtils;
import com.wurmonline.mapgenerator.core.util.MathUtils;
import com.wurmonline.mapgenerator.ui.Interpreter;
import com.wurmonline.mapgenerator.ui.Map;

public class Window {

	private JFrame frame;
	private CommandOutputArea output;
	private CommandInputArea input;
	private Interpreter interpreter;
	private JSplitPane splitPane;
	private PSwingCanvas canvas;
	
	private ImageUtils utils = new ImageUtils();
	private MathUtils mutils = new MathUtils();
	
	public Window(Interpreter interpreter) {
		this.interpreter = interpreter;
		initialize();
	}

	public JFrame getFrame()
	{
		return frame;
	}
	
	private void initialize() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(1, 1, 0, 0));
		
		JMenuBar menuBar = new JMenuBar();
		
		frame.setJMenuBar(menuBar);
		
		JMenu help = new JMenu("Help");
		
		JMenuItem about = new JMenuItem("About");
		help.add(about);
		
		JMenuItem onlineResources = new JMenuItem("Online resources");
		help.add(onlineResources);
		
		JMenu file = new JMenu("File");
		menuBar.add(file);
		menuBar.add(help);
		
		JMenuItem exit = new JMenuItem("Exit");
		file.add(exit);
		
		splitPane = new JSplitPane();
		splitPane.setResizeWeight(0.65f);
		frame.getContentPane().add(splitPane);
		
		JPanel interaction = new JPanel();
		splitPane.setRightComponent(interaction);
		interaction.setLayout(new GridLayout(0, 1, 0, 0));
		
		JSplitPane interactionSplitPane = new JSplitPane();
		interactionSplitPane.setResizeWeight(0.8f);
		
		interactionSplitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		interaction.add(interactionSplitPane);
		
		JPanel inputPanel = new JPanel();
		interactionSplitPane.setRightComponent(inputPanel);
		inputPanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JSplitPane inputSplitPane = new JSplitPane();
		inputPanel.add(inputSplitPane);
		
		JScrollPane scrollPane = new JScrollPane();
		inputSplitPane.setLeftComponent(scrollPane);
		
		output = new CommandOutputArea();
		input = new CommandInputArea(interpreter,output);
		
		scrollPane.setViewportView(input);
		
		JLabel inputLabel = new JLabel("Command Input");
		scrollPane.setColumnHeaderView(inputLabel);
		
		JButton btnEval = new EvalButton("Eval",input,output,interpreter);
		inputSplitPane.setRightComponent(btnEval);
		inputSplitPane.setResizeWeight(0.95f);
		
		JScrollPane outputScrollPane = new JScrollPane();
		interactionSplitPane.setLeftComponent(outputScrollPane);
		
		output.setEditable(false);
		outputScrollPane.setViewportView(output);
		
		JLabel outputLabel = new JLabel("Command output");
		outputScrollPane.setColumnHeaderView(outputLabel);
		
		canvas = new PSwingCanvas();
		splitPane.setLeftComponent(canvas);
	}
	
	public void load(String path)
	{
        PImage imageNode = new PImage(path);
        loadImpl(imageNode);
	}
	
	public void load(Image image)
	{
        PImage imageNode = new PImage(image);
        loadImpl(imageNode);
	}
	
	public void load(float[][] map)
	{
		float[][] output = mutils.normalize(map);
		load(utils.grayscale(output));
	}
	
	public void load(Map map)
	{
		load(map.dump());
	}
	
	private void loadImpl(PImage imageNode)
	{
		canvas.getLayer().removeAllChildren();
        canvas.getLayer().addChild(imageNode);
	    canvas.repaint();
	}
	
}
