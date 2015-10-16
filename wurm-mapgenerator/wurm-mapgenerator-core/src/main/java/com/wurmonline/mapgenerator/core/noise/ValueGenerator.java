package com.wurmonline.mapgenerator.core.noise;

import com.wurmonline.mapgenerator.core.context.AppModule;

@AppModule("value")
public class ValueGenerator extends NoiseGenerator{

	public float value;
	
	@Override
	public float[][] generateImpl(int width, int height) {
		float[][] noise = new float[width][height];
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {	
				noise[x][y] = value;
			}
		}
		return noise;
	}

}
