package com.wurmonline.mapgenerator.core.util;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.wurmonline.mapgenerator.core.context.AppModule;

@AppModule("image")
public class ImageUtils {

	public BufferedImage grayscale(float[][] input)
	{
		BufferedImage result = new BufferedImage(
	            input.length,
	            input.length,
	            BufferedImage.TYPE_BYTE_GRAY);
	  //Graphics g = result.getGraphics();
	  for(int i=0; i< input.length; i++)
		  for(int j=0; j< input.length; j++)
			  result.setRGB(i, j, grayscale((int)(input[i][j]*255)).getRGB());
	  //g.dispose();
	  return result;
	}
	
	public Color grayscale(int value)
	{
		Color c = new Color(value,value,value);
		return c;
	}
}
