package com.wurmonline.mapgenerator.core.noise;

import java.util.Random;

import com.wurmonline.mapgenerator.core.context.AppModule;

@AppModule("white")
public class WhiteNoiseGenerator extends NoiseGenerator{

	private Random random = new Random();
	
	public float[][] generateImpl(int size) {
		float[][] noise = new float[size][size];
		for (int y = 0; y < size; y++) {
			for (int x = 0; x < size; x++) {	
				noise[x][y] = random.nextFloat();
			}
		}
		return noise;
	}
}
