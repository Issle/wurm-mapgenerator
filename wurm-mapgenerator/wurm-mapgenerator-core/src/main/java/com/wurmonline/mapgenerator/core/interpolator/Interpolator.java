package com.wurmonline.mapgenerator.core.interpolator;

public abstract class Interpolator {

	private int scaleFactor;

	public Interpolator(int scaleFactor) {
		this.scaleFactor = scaleFactor;
	}

	public float[][] interpolate(float[][] input, int scale) {
		int size = input.length;
		int newSize = input.length * scale;
		float[][] output = new float[newSize][newSize];

		float factor = (newSize - 1f) / (size - 1f);
		for (int i = 0; i < newSize; i++)
			for (int j = 0; j < newSize; j++) {
				float initialX = i / factor;
				float initialY = j / factor;
				int x0 = getInteger(initialX);
				int y0 = getInteger(initialY);
				float xFrac = getDecimalFrac(initialX);
				float yFrac = getDecimalFrac(initialY);
				int x1 = x0 + 1 == size ? x0 : x0 + 1;
				int y1 = y0 + 1 == size ? y0 : y0 + 1;

				float s00 = input[x0][y0];
				float s10 = input[x1][y0];
				float s11 = input[x1][y1];
				float s01 = input[x0][y1];
				//System.out.println(i + " " + j + " " + x0 + " " + x1 + " " + xFrac);
				output[i][j] = pointInterpolator(s00, s01, s10, s11, xFrac, yFrac);
			}

		return output;
	}

	public static int getInteger(float input) {
		return (int) input;
	}

	public static float getDecimalFrac(float input) {
		float output = input - (int) input;
		return output;
	}

	private float map(int element, int scale) {
		if (element == 0)
			return 0;
		return (element - 1) / (float) scale;
	}

	private int bound(int input, int size) {
		if (input < 0)
			return 0;
		if (input >= size)
			return size - 1;
		else
			return input;
	}

	public float getDecimalPart(float input) {
		int integer = getIntegerPart(input);
		return (10 * input - 10 * integer) / 10;
	}

	public int getIntegerPart(float input) {
		return (int) input;
	}

	public abstract float pointInterpolator(float s00, float s10, float s01, float s11, float xfrac, float yfrac);
}
