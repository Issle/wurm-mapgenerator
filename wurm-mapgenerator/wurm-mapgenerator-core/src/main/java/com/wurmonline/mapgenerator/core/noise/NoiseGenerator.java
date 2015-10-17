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
	public float[][] generate(int size)
	{
		assertPreconditions(size);
		return generateImpl(size);
	}
	
	/**
	 * Generates a square noise matrix with size that's
	 * a power of two.
	 * @param size
	 * @return
	 */
	public float[][] generateSquarePowTwo(int size)
	{
		assertPreconditions(size);
		assertIsPowTwo(size);
		return generateImpl(size);
	}
	
	/**
	 * The actual implementation of the noise generator.
	 * @param width
	 * @param height
	 * @return
	 */
	public abstract float[][] generateImpl(int size);
	
	/**
	 * General precondition checks.
	 * @param width
	 * @param height
	 */
	public void assertPreconditions(int size)
	{
		if(size<0)
			throw new RuntimeException("A negative size matrix is not allowed. Size:"+size);
	}
	
	public void assertIsPowTwo(int value)
	{
		if((value & -value) != value)
			throw new RuntimeException("The input value is not a power of two. Value: "+ value);
	}
}
