package com.wurmonline.mapgenerator.core.noise;

import com.wurmonline.mapgenerator.core.context.AppModule;

@AppModule("zeros")
public class ZeroGenerator extends NoiseGenerator{

	private ValueGenerator generator = new ValueGenerator();
	
	public ZeroGenerator()
	{
		generator.value =0;
	}
	
	@Override
	public float[][] generateImpl(int size) {
		return generator.generate(size);
	}

}
