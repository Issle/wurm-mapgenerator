package com.wurmonline.mapgenerator.core;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class MapRenderer {

	public static double[] getDeltas(int x, int y, float[][] noise) {

		double[] deltas = new double[2];
		if (x == 0)
			deltas[0] = 0;
		else {
			deltas[0] = (noise[x][y] - noise[x - 1][y]) /255d;
			// System.out.println(deltas[0] + " "+ noise[x][y] + " " +
			// noise[x-1][y]);
		}

		if (y == 0)
			deltas[1] = 0;
		else
			deltas[1] = (noise[x][y] - noise[x][y - 1])/255d;

		return deltas;
	}

	public static Color stain(Color color, float amount) {
		int red = (int) ((color.getRed() * (1 - amount*20) / 255) * 255);
		if(red > 254)
			red = 254;
		int green = (int) ((color.getGreen() * (1 - amount*20) / 255) * 255);
		if(green > 254)
			green = 254;
		int blue = (int) ((color.getBlue() * (1 - amount*20) / 255) * 255);
		if(blue > 254)
			blue = 254;
		return new Color(red, green, blue);
	}

}
