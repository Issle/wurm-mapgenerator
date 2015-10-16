package com.wurmonline.mapgenerator.core.noise;

import com.wurmonline.mapgenerator.core.context.AppModule;

@AppModule("none")
public abstract class NoiseGenerator {

	/**
	 * Generates a noise matrix of the given dimensions
	 * @param width
	 * @param height
	 * @return
	 */
	public float[][] generate(int width, int height)
	{
		assertPreconditions(width,height);
		return generateImpl(width,height);
	}
	
	/**
	 * Generates a square noise matrix with size that's
	 * a power of two.
	 * @param size
	 * @return
	 */
	public float[][] generateSquarePowTwo(int size)
	{
		assertPreconditions(size,size);
		assertIsPowTwo(size);
		return generateImpl(size,size);
	}
	
	/**
	 * The actual implementation of the noise generator.
	 * @param width
	 * @param height
	 * @return
	 */
	public abstract float[][] generateImpl(int width, int height);
	
	/**
	 * General precondition checks.
	 * @param width
	 * @param height
	 */
	public void assertPreconditions(int width, int height)
	{
		if(width <0 || height <0)
			throw new RuntimeException("A negative size matrix is not allowed. Width: "+width +" Height:"+height);
	}
	
	public void assertIsPowTwo(int value)
	{
		if((value & -value) != value)
			throw new RuntimeException("The input value is not a power of two. Value: "+ value);
	}
}
