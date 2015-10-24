package com.wurmonline.mapgenerator.core.noise;

import java.util.Random;

import com.oddlabs.procedurality.Midpoint;
import com.wurmonline.mapgenerator.core.context.AppModule;

@AppModule("midpoint")
public class MidpointGenerator extends NoiseGenerator{

	public int baseFreq = 1;
	public float pers = 0.5f;
	private Random random = new Random();
	
	@Override
	public float[][] generateImpl(int size) {
		Midpoint midpoint = new Midpoint(size, baseFreq,pers, random.nextLong());
		return midpoint.toChannel().getPixels();
		
	}

}
