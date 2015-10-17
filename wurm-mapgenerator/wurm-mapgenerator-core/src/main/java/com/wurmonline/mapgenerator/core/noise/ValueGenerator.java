package com.wurmonline.mapgenerator.core.noise;

import com.wurmonline.mapgenerator.core.context.AppModule;

@AppModule("value")
public class ValueGenerator extends NoiseGenerator{

	public float value = 0.5f;
	
	@Override
	public float[][] generateImpl(int size) {
		float[][] noise = new float[size][size];
		for (int y = 0; y < size; y++) {
			for (int x = 0; x < size; x++) {	
				noise[x][y] = value;
			}
		}
		return noise;
	}

}
