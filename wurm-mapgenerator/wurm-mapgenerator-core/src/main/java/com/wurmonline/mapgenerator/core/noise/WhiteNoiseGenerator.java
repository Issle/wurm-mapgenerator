package com.wurmonline.mapgenerator.core.noise;

import java.util.Random;

public class WhiteNoiseGenerator extends NoiseGenerator{

	private Random random = new Random();
	
	public float[][] generateImpl(int width, int height) {
		float[][] noise = new float[width][height];
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {	
				noise[x][y] = random.nextFloat();
			}
		}
		return noise;
	}
}
