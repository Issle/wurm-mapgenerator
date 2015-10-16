package com.wurmonline.mapgenerator.core.noise;

import com.wurmonline.mapgenerator.core.context.AppModule;

@AppModule("perlin")
public class PerlinNoiseGenerator extends NoiseGenerator{
	
	private NoiseGenerator whiteNoise = new WhiteNoiseGenerator();
	private float persistance;
	private int octaveCount;
	private int maxAltitude = 127;
	
	public PerlinNoiseGenerator()
	{
		persistance = 0.5f;
		octaveCount = 8;
	}
	
	public PerlinNoiseGenerator(float persistance, int octaveCount, int height)
	{
		this.persistance = persistance;
		this.octaveCount = octaveCount;
		this.maxAltitude = height;
	}

	public float interpolate (float x0, float x1, float alpha) {
		return x0 * (1 - alpha) + alpha * x1;
	}

	public float[][] generateSmoothNoise (float[][] baseNoise, int octave) {
		int width = baseNoise.length;
		int height = baseNoise[0].length;
		float[][] smoothNoise = new float[width][height];

		int samplePeriod = 1 << octave; // calculates 2 ^ k
		float sampleFrequency = 1.0f / samplePeriod;
		for (int i = 0; i < width; i++) {
			int sample_i0 = (i / samplePeriod) * samplePeriod;
			int sample_i1 = (sample_i0 + samplePeriod) % width; // wrap around
			float horizontal_blend = (i - sample_i0) * sampleFrequency;

			for (int j = 0; j < height; j++) {
				int sample_j0 = (j / samplePeriod) * samplePeriod;
				int sample_j1 = (sample_j0 + samplePeriod) % height; // wrap around
				float vertical_blend = (j - sample_j0) * sampleFrequency;
				float top = interpolate(baseNoise[sample_i0][sample_j0], baseNoise[sample_i1][sample_j0], horizontal_blend);
				float bottom = interpolate(baseNoise[sample_i0][sample_j1], baseNoise[sample_i1][sample_j1], horizontal_blend);
				smoothNoise[i][j] = interpolate(top, bottom, vertical_blend);
			}
		}

		return smoothNoise;
	}

	public float[][] generatePerlinNoise (float[][] baseNoise, int octaveCount) {
		int width = baseNoise.length;
		int height = baseNoise[0].length;
		float[][][] smoothNoise = new float[octaveCount][][]; // an array of 2D arrays containing

		for (int i = 0; i < octaveCount; i++) {
			smoothNoise[i] = generateSmoothNoise(baseNoise, i);
		}

		float[][] perlinNoise = new float[width][height]; // an array of floats initialised to 0

		float amplitude = 1.0f;
		float totalAmplitude = 0.0f;

		for (int octave = octaveCount - 1; octave >= 0; octave--) {
			amplitude *= persistance;
			totalAmplitude += amplitude;

			for (int i = 0; i < width; i++) {
				for (int j = 0; j < height; j++) {
					perlinNoise[i][j] += smoothNoise[octave][i][j] * amplitude;
				}
			}
		}

		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				perlinNoise[i][j] /= totalAmplitude;
			}
		}

		return perlinNoise;
	}

	public float[][] generateImpl(int width, int height) {
		float[][] baseNoise = whiteNoise.generate(width, height);
		return generatePerlinNoise(baseNoise, octaveCount);
	}
	
	public int[][] getCanonical(float[][] map)
	{
		int[][] intmap = new int[map.length][map[0].length];
		for(int i=0; i < intmap.length-1; i++)
			for(int j = 0; j< intmap[0].length-1; j++)
			{
				intmap[i][j] = (int)(maxAltitude*map[i][j]);
			}
		return intmap;
	}
}