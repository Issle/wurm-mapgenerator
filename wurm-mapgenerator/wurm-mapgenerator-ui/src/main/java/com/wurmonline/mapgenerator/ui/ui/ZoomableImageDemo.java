package com.wurmonline.mapgenerator.ui.ui;

import javax.swing.SwingUtilities;

import org.piccolo2d.extras.PFrame;
import org.piccolo2d.nodes.PImage;

public class ZoomableImageDemo extends PFrame {
	 
    public ZoomableImageDemo() {
        super("Zoomable Image Demo", false, null);
        setSize(480, 320);
        setLocationRelativeTo(null);       
    }
     
    public void initialize() {
        String imageFilePath = "/home/ipapaste/Downloads/nayt001.jpg";
        PImage imageNode = new PImage(imageFilePath);
         
        getCanvas().getLayer().addChild(imageNode);
 
    }
 
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
 
            @Override
            public void run() {
                new ZoomableImageDemo().setVisible(true);
            }
        });
    }
}