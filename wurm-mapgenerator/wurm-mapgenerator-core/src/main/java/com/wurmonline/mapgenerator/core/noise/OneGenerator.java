package com.wurmonline.mapgenerator.core.noise;

import com.wurmonline.mapgenerator.core.context.AppModule;

@AppModule("ones")
public class OneGenerator extends NoiseGenerator{
	
	private ValueGenerator generator = new ValueGenerator();
	
	public OneGenerator()
	{
		generator.value =1;
	}

	@Override
	public float[][] generateImpl(int size) {
		return generator.generate(size);
	}

}
